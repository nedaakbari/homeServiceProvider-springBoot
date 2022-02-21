package ir.maktab.homeserviceprovider.service.interfaces;

import ir.maktab.data.entity.TransActions;
import ir.maktab.dto.OrdersDto;
import ir.maktab.dto.TransActionDto;

import java.util.List;

public interface TransActionService {
    void save(TransActionDto transActionDto, OrdersDto ordersDto);

    void delete(TransActions transActions);

    List<TransActionDto> getAll();

    TransActions getById(Long theId);

    void paidForOrder(String ordersDto);
}
