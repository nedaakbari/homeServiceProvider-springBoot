package ir.maktab.homeserviceprovider.service.interfaces;

import ir.maktab.data.entity.Comment;
import ir.maktab.data.entity.Orders;
import ir.maktab.data.entity.Person.Customer;
import ir.maktab.data.entity.Person.Expert;
import ir.maktab.dto.CommentDto;

import java.util.List;


public interface CommentService {

    void save(Customer customer, Expert expert, Orders orders, String comment);

    void delete(CommentDto commentDto);

    List<CommentDto> getAll();

    CommentDto getById(Long theId);

    List<CommentDto> findAllCommentOfAnOrder(Orders orders);

    Comment findByUUID(CommentDto commentDto);
}