package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Offer;
import ir.maktab.data.entity.Orders;
import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.enums.OfferStatus;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.repository.ExpertRepository;
import ir.maktab.data.repository.OfferRepository;
import ir.maktab.data.repository.OrderRepository;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrdersDto;
import ir.maktab.service.exception.LessAmount;
import ir.maktab.service.exception.NoOffer;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.interfaces.OfferService;
import ir.maktab.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final ModelMapper mapper;
    private final OfferRepository offerDao;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final ExpertRepository expertRepository;

    @Override
    public void update(OfferDto offerDto) {
        Optional<Offer> offer = offerDao.findOfferByCodeNumber(offerDto.getCodeNumber());
        if (offer.isPresent()) {
            offerDao.save(offer.get());
        } else throw new NotFoundDta("no offer found to update");
    }

    @Override
    public void saveOffer(OfferDto offerDto, String email, String codeNumber) {
        Orders orders = orderService.findByUUID(codeNumber);
        Expert expert = expertRepository.findByEmail(email).get();
        Offer offer = mapper.map(offerDto, Offer.class);

        List<String> subTitle = new ArrayList<>();
        expert.getSubCategoryList().forEach(subCategory -> subTitle.add(subCategory.getTitle()));

        if (subTitle.contains(orders.getSubCategory().getTitle())) {
            Double baseAmount = orders.getSubCategory().getBasePrice();
            Double offerPrice = offerDto.getProposedPrice();
            if (offerPrice > baseAmount) {
                List<Offer> list = offerDao.findAllOfferOfAnOrder(orders.getId());
                if (list.size() == 0) {
                    // orders.setState(OrderState.WAITING_FOR_SELECT_AN_EXPERT);
                    orderService.updateState(mapper.map(orders, OrdersDto.class), OrderState.WAITING_FOR_SELECT_AN_EXPERT);
                }
                offer.setStatus(OfferStatus.SUSPENDED);
                offer.setExpert(expert);
                offer.setOrders(orders);
                offerDao.save(offer);
            } else
                throw new LessAmount("offerPrice must not be lower than baseAmount of this subService");
        } else
            throw new RuntimeException("This field is not your specialty ");
    }

    @Override
    public void delete(OfferDto offerDto) {
        Offer offer = offerDao.findOfferByCodeNumber(offerDto.getCodeNumber()).get();
        offerDao.delete(offer);
    }

    @Override
    public List<OfferDto> getAll() {
        Iterable<Offer> allOffer = offerDao.findAll();
        List<Offer> all = new ArrayList<>();
        allOffer.forEach(all::add);
        if (all.size() != 0) {
            return all.stream()
                    .map(offer -> mapper.map(offer, OfferDto.class))
                    .collect(Collectors.toList());
        } else
            throw new NotFoundDta("no offer Exist yet");
    }

    @Override
    public OfferDto getById(Long theId) {
        Optional<Offer> found = offerDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), OfferDto.class);
        else
            throw new NotFoundDta("no offer found");
    }

    @Override
    public void acceptedOffer(String choiceOfferCode) {
        Offer choiceOffer = offerDao.findOfferByCodeNumber(choiceOfferCode).get();
        Expert expert = expertRepository.findByEmail(choiceOffer.getExpert().getEmail()).get();//expertbyId
        Orders order = orderRepository.findByCodeNumber(choiceOffer.getOrders().getCodeNumber()).get();//order by id

        order.setExpert(expert);
        order.setState(OrderState.WAITING_FOR_EXPERT_TO_COMING_TO_YOUR_PLACE);
        order.setAgreedPrice(choiceOffer.getProposedPrice());

        // OrdersDto dto = mapper.map(order, OrdersDto.class);
        orderRepository.save(order);

        //افرهای این ارد رو پیدا کنیم
        List<Offer> allOfferOfAnOrder = findAllOfferOfAnOrders(order);//todo اینو بپرس که چیکارش کنی الن تو دی تی او داری مجبوری یکی بگیری که افر برگردونه خیلی شلخته کاریه

        allOfferOfAnOrder.forEach(offer -> {
            if (offer.getCodeNumber().equals(choiceOffer.getCodeNumber())) {
                offer.setStatus(OfferStatus.ACCEPTED);
                offerDao.save(offer);
            } else {
                offer.setStatus(OfferStatus.REJECTED);
                offerDao.save(offer);
            }
        });
    }

    @Override
    public OfferDto findByUUID(String uuid) {
        Optional<Offer> offer = offerDao.findOfferByCodeNumber(uuid);
        if (offer.isPresent())
            return mapper.map(offer.get(), OfferDto.class);
        else throw new NotFoundDta("no offer found");
        // return offer.orElseThrow(() -> new NotFoundDta("no offer found"));
    }

    @Override
    public List<OfferDto> findAllOfferOfAnOrder(String ordersCode, String sort) {//proposedPrice  ,expert.score
        Orders order = orderService.findByUUID(ordersCode);
        List<OfferDto> all = null;
        if (sort.equals("proposedPrice"))
            all = offerDao.findAllOfferOfAnOrders(order.getId(), Sort.by(sort).ascending()).stream()
                    .map(offer -> mapper.map(offer, OfferDto.class)).collect(Collectors.toList());
        if (sort.equals("expert.score"))
            all = offerDao.findAllOfferOfAnOrders(order.getId(), Sort.by(sort).descending()).stream()
                    .map(offer -> mapper.map(offer, OfferDto.class)).collect(Collectors.toList());
        if (all != null) {
            return all;
        } else
            throw new NoOffer("no offer for this order Exist yet ");
    }

    @Override
    public List<Offer> findAllOfferOfAnOrders(Orders order) {
        List<Offer> all = offerDao.findAllOfferOfAnOrder(order.getId());
        if (all.size() != 0) {
            return all;
        } else
            throw new NoOffer("no offer for this order Exist yet ");
    }

    @Override
    public OfferDto findByOrderAndExpert(OrdersDto ordersDto, ExpertDto expertDto) {
        Orders order = orderRepository.findByCodeNumber(ordersDto.getCodeNumber()).get();
        Expert expert = expertRepository.findByEmail(expertDto.getEmail()).get();
        Optional<Offer> offer = offerDao.findByOrdersAndExpert(order, expert);
        if (offer.isPresent()) {
            return mapper.map(offer.get(), OfferDto.class);
        } else
            throw new NoOffer("offer not found!");
    }

    @Override
    public List<OfferDto> findAllOfferAnExpert(ExpertDto expertDto) {
        Expert expert = expertRepository.findByEmail(expertDto.getEmail()).get();
        List<Offer> allOfferAnExpert = offerDao.findAllOfferAnExpert(expert);
        if (allOfferAnExpert.size() != 0) {
            return allOfferAnExpert.stream()
                    .map(offer -> mapper.map(offer, OfferDto.class)).collect(Collectors.toList());
        } else
            throw new NoOffer("no offer!");
    }

    @Override
    public List<OfferDto> findAllOffersAnExpert(ExpertDto expertDto, OfferStatus status) {
        Expert expert = expertRepository.findByEmail(expertDto.getEmail()).get();
        List<Offer> allOfferAnExpert = offerDao.findAllOfferAnExpertWithStatus(expert, status);
        if (allOfferAnExpert.size() != 0) {
            return allOfferAnExpert.stream()
                    .map(offer -> mapper.map(offer, OfferDto.class)).collect(Collectors.toList());
        } else
            throw new NoOffer("no offer!");
    }

    @Override
    public OfferDto findAcceptedOfferOfAnOrder(OrdersDto ordersDto) {
        Orders order = mapper.map(ordersDto, Orders.class);
        Offer acceptedOffer = null;
        if (order.getState().equals(OrderState.PAID)) {
            Set<Offer> offers = order.getOffers();
            for (Offer offer : offers) {
                if (offer.getStatus().equals(OfferStatus.ACCEPTED)) {
                    acceptedOffer = offer;
                }
            }
            return mapper.map(acceptedOffer, OfferDto.class);
        } else {
            throw new NoOffer("nothing found!");
        }
    }

    @Override
    public boolean isAllowToOffer(ExpertDto expertDto, String orderCode) {
        Expert expert = expertRepository.findByEmail(expertDto.getEmail()).get();
        Orders orders = orderRepository.findByCodeNumber(orderCode).get();
        Optional<Offer> byOrdersAndExpert = offerDao.findByOrdersAndExpert(orders, expert);
        if (byOrdersAndExpert.isPresent())
            return false;
        else return true;
    }
}