package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Comment;
import ir.maktab.data.entity.Orders;
import ir.maktab.data.entity.Person.Customer;
import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.repository.CommentRepository;
import ir.maktab.dto.CommentDto;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final ModelMapper mapper;
    private final CommentRepository commentDao;

    @Override
    public void save(Customer customer, Expert expert, Orders orders, String comment) {
        Comment comments = new Comment();
        comments.setCustomer(customer);
        comments.setExpert(expert);
        comments.setOrder(orders);
        comments.setComment(comment);
        commentDao.save(comments);
    }

    @Override
    public void delete(CommentDto commentDto) {
        Comment comment = findByUUID(commentDto);
        commentDao.delete(comment);
    }

    @Override
    public List<CommentDto> getAll() {
        return commentDao.findAll().stream()
                .map(comment -> mapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getById(Long theId) {
        Optional<Comment> found = commentDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), CommentDto.class);
        else throw new NotFoundDta("no comment");
    }

    @Override
    public List<CommentDto> findAllCommentOfAnOrder(Orders orders) {
        return commentDao.findAllCommentOfAnOrder(orders).stream()
                .map(comment -> mapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public Comment findByUUID(CommentDto commentDto) {
        Optional<Comment> found = commentDao.findByCodeNumber(commentDto.getCodeNumber());
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no comment founded");
    }
}
