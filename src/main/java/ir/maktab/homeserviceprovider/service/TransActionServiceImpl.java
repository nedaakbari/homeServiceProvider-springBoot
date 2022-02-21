package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Orders;
import ir.maktab.data.entity.Person.Customer;
import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.entity.TransActions;
import ir.maktab.data.enums.OrderState;
import ir.maktab.data.repository.CustomerRepository;
import ir.maktab.data.repository.OrderRepository;
import ir.maktab.data.repository.TransActionRepository;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.OrdersDto;
import ir.maktab.dto.TransActionDto;
import ir.maktab.service.exception.NotEnoughMoney;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.interfaces.OrderService;
import ir.maktab.service.interfaces.TransActionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransActionServiceImpl implements TransActionService {
    private final ModelMapper mapper;
    private final TransActionRepository transActionDao;
    private final CustomerServiceImpl customerService;
    private final CustomerRepository customerRepository;
    private final ExpertServiceImpl expertService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public void save(TransActionDto transActionDto,OrdersDto ordersDto) {
        TransActions transActions = mapper.map(transActionDto, TransActions.class);
        transActions.setExpertAccNumber(ordersDto.getExpert().getAccNumber());
        transActions.setAmount(ordersDto.getAgreedPrice());
        transActions.setCustomer(customerRepository.findByEmail(ordersDto.getCustomer().getEmail()).get());
        transActionDto.setAmount(ordersDto.getAgreedPrice());
        orderService.updateState(ordersDto, OrderState.PAID);
        transActionDao.save(transActions);
    }

    public void delete(TransActions transActions) {
        transActionDao.delete(transActions);
    }

    public List<TransActionDto> getAll() {
        return transActionDao.findAll().stream()
                .map(transActions -> mapper.map(transActions, TransActionDto.class)).collect(Collectors.toList());
    }

    public TransActions getById(Long theId) {
        return transActionDao.findById(theId).orElseThrow(() -> new NotFoundDta("no transAction found"));
    }

    // @Transactional()//Propagation=Propagation.REQUIRED
    @Override
    public void paidForOrder(String ordersDto) {
        TransActions transActions = new TransActions();
        Orders orders = orderRepository.findByCodeNumber(ordersDto).get();
        Customer customer = orders.getCustomer();
        Expert expert = orders.getExpert();
        Double customerCurt = customer.getCreditCart();
        Double orderPrice = orders.getAgreedPrice();
        if (customerCurt >= orderPrice) {
            transActions.setAmount(orderPrice);
            transActions.setCustomer(customer);
            transActions.setOrders(orders);
            transActions.setExpertAccNumber(expert.getAccNumber());
            CustomerDto cDto = mapper.map(customer, CustomerDto.class);
            transActionDao.save(transActions);
            customerService.updateCreditCart(customer.getCreditCart()-orderPrice, cDto);
            expertService.updateCreditCart(expert.getCreditCart()+(orderPrice*.7), expert.getEmail());
            orderService.updateState(mapper.map(orders,OrdersDto.class), OrderState.PAID);
        } else {
            throw new NotEnoughMoney("you dont have enough money in your creditCart");
        }
    }

}
