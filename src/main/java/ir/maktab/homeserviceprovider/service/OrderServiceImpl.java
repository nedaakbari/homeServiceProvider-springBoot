package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Address;
import ir.maktab.data.entity.Orders;
import ir.maktab.data.entity.Person.Customer;
import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.entity.service.SubCategory;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.repository.AddressRepository;
import ir.maktab.data.repository.CustomerRepository;
import ir.maktab.data.repository.OrderRepository;
import ir.maktab.data.repository.SubCategoryRepository;
import ir.maktab.data.repository.specification.OrderSpecifications;
import ir.maktab.dto.*;
import ir.maktab.service.exception.DuplicateData;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.interfaces.CommentService;
import ir.maktab.service.interfaces.ExpertService;
import ir.maktab.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ModelMapper mapper;
    private final OrderRepository orderDao;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final SubCategoryRepository subRepository;
    private final CommentService commentService;
    private final ExpertService expertService;

    @Override
    public void save(OrdersDto ordersDto, CustomerDto customerDto, AddressDto addressDto, String subCategoryDto) {
        SubCategory subCategory = subRepository.findByTitle(subCategoryDto).get();
        // if (ordersDto.getProposedPrice() >= subCategory.getBasePrice()) {
        Customer customer = customerRepository.findByEmail(customerDto.getEmail()).get();
        Address address = addressRepository.findAddressesByZipCode(addressDto.getZipCode()).get();
        Orders orders = mapper.map(ordersDto, Orders.class);
        System.out.println(orders);
        orders.setCustomer(customer);
        orders.setAddress(address);
        orders.setSubCategory(subCategory);
        orders.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        orderDao.save(orders);
        // } else throw new LessAmount("amount can not be less than base amount");
    }

    @Override
    public void updateState(OrdersDto ordersDto, OrderState state) {
        Orders orders = orderDao.findByCodeNumber(ordersDto.getCodeNumber()).get();
        orders.setState(state);
        orderDao.save(orders);
    }


    @Override
    public void placeScore(String orderCodeNumber, String comment, double score) {
        Orders orders = orderDao.findByCodeNumber(orderCodeNumber).get();// ّبین اردر رو نداری که نخوای دوباره پیداش کنی
        if (orders.getComment() != null) {
            throw new DuplicateData("you comment for this before");
        } else {
            orders.setComment(comment);
            orders.setScore(score);
            orderDao.save(orders);
            Customer customer = orders.getCustomer();
            Expert expert = orders.getExpert();
            expertService.updateScore(expert, score);
            commentService.save(customer, expert, orders, comment);
        }
    }

    @Override
    public void delete(OrdersDto ordersDto) {
        Optional<Orders> orders = orderDao.findByCodeNumber(ordersDto.getCodeNumber());
        orderDao.delete(orders.get());
    }

    @Override
    public List<OrdersDto> getAll() {
        return orderDao.findAll().stream()
                .map(orders -> mapper.map(orders, OrdersDto.class))
                .collect(Collectors.toList());
    }



    @Override
    public Orders getById(Long theId) {
        Optional<Orders> foundOrder = orderDao.findById(theId);
        return foundOrder.orElseThrow(() -> new NotFoundDta("❌❌❌ Error, no order found ❌❌❌ "));
    }


    @Override
    public Orders findByUUID(String uuid) {
        Optional<Orders> foundOrder = orderDao.findByCodeNumber(uuid);
        return foundOrder.orElseThrow(() -> new NotFoundDta("❌❌❌ Error, no order found ❌❌❌ "));
    }

  /*  @Override
    public OrdersDto findByCodeNumber(String CodeNumber) {
        Optional<Orders> foundOrder = orderDao.findByCodeNumber(CodeNumber);
        if (foundOrder.isPresent())
            return mapper.map(foundOrder.get(), OrdersDto.class);
        else
            throw  new NotFoundDta("❌❌❌ Error, no order found ❌❌❌ ");
    }*/

    @Override
    public OrdersDto findOrderByCodeNumber(String CodeNumber) {
        Optional<Orders> found = orderDao.findByCodeNumber(CodeNumber);
        if (found.isPresent())
            return mapper.map(found.get(), OrdersDto.class);
        else
            throw new NotFoundDta("❌❌❌ no order founded ❌❌❌");
    }

    @Override
    public List<OrdersDto> findOrdersOfSubService(int subServiceId) {
        List<Orders> orders = orderDao.findOrdersOfSubService(subServiceId);//findOrdersOfSubService
        return orders.stream().map(item -> mapper.map(item, OrdersDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrdersDto> findOrdersOfSubServices(String subServiceTitle) {
        SubCategory subCategory = subRepository.findByTitle(subServiceTitle).get();
        List<Orders> orders = orderDao.findOrdersOfSubService(subCategory.getId());
        if (orders.size() == 0)
            throw new NotFoundDta("no orders yet");
        else
            return orders.stream().map(item -> mapper.map(item, OrdersDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrdersDto> findOrderOfCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findByEmail(customerDto.getEmail()).get();
        List<Orders> orderOfCustomer = orderDao.findOrderOfCustomer(customer.getId());
        return orderOfCustomer.stream().map(item -> mapper.map(item, OrdersDto.class)).collect(Collectors.toList());
    }


    @Override
    public List<OrdersDto> findOrdersForExpert(ExpertDto expertDto) {
        Set<SubCategoryDto> subCategoryList = expertDto.getSubCategoryList();
        List<Orders> ordersForExpert = new ArrayList<>();
        for (SubCategoryDto subCategory : subCategoryList) {
            String title = subCategory.getTitle();
            SubCategory foundSub = subRepository.findByTitle(title).get();
            List<Orders> orders = orderDao.findOrdersOfSubService(foundSub.getId());//
            ordersForExpert.addAll(orders);
        }
        return ordersForExpert.stream().map(item -> mapper.map(item, OrdersDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrdersDto> searchOrders(OrderFilterDto dto) {
        Sort sort = Sort.by("orderRegistrationDate");
        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize(), sort);
        Specification<Orders> specification = OrderSpecifications.orderFilter(dto);

        return orderDao.
                findAll(Specification.where(specification), pageable)
                .stream().map(Orders -> mapper.map(Orders, OrdersDto.class))
                .collect(Collectors.toList());
    }

}


