package ir.maktab.homeserviceprovider.service.interfaces;

import ir.maktab.data.entity.Offer;
import ir.maktab.data.entity.Orders;
import ir.maktab.data.enums.OfferStatus;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.OfferDto;
import ir.maktab.dto.OrdersDto;

import java.util.List;


public interface OfferService {

    void saveOffer(OfferDto offerDto, String email, String codeNumber);

    void delete(OfferDto offerDto);

    void update(OfferDto offerDto);

    List<OfferDto> getAll();

    OfferDto getById(Long theId);

    OfferDto findByOrderAndExpert(OrdersDto ordersDto, ExpertDto expertDto);

    List<OfferDto> findAllOffersAnExpert(ExpertDto expertDto, OfferStatus status);

    //List<OfferDto> sortByScore(OrdersDto ordersDto);

    //List<OfferDto> sortByPrice(OrdersDto ordersDto);

    void acceptedOffer(String choiceOfferCode);

    OfferDto findByUUID(String uuid);

    List<OfferDto> findAllOfferAnExpert(ExpertDto expertDto);

    List<OfferDto> findAllOfferOfAnOrder(String ordersCode, String sort);

    List<Offer> findAllOfferOfAnOrders(Orders order);

    OfferDto findAcceptedOfferOfAnOrder(OrdersDto ordersDto);

    boolean isAllowToOffer(ExpertDto expertDto, String orderCode);
}
