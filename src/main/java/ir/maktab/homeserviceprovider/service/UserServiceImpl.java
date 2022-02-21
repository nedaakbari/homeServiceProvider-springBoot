package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Person.User;
import ir.maktab.data.enums.UserRegistrationStatus;
import ir.maktab.data.repository.UserRepository;
import ir.maktab.data.repository.specification.UserSpecifications;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserFilterDto;
import ir.maktab.service.exception.DuplicateData;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.exception.UserNotFoundException;
import ir.maktab.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userDao;
    private final ModelMapper mapper;

    @Override
    public void register(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        Optional<User> foundUser = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("❌❌❌ Error,user with these use an pass is already exist ❌❌❌");
        } else {
            userDao.save(user);
        }
    }

    @Override
    public UserDto login(UserDto userDto) {//can't private because of implement interface and have no body
        Optional<User> user = userDao.findByEmailAndPassword
                (userDto.getEmail(), userDto.getPassword());
        if (user.isEmpty())
            throw new UserNotFoundException();
        //throw new ExpertNotFoundException("not.found");
        return mapper.map(user.get(), UserDto.class);
    }

    @Override
    public void delete(UserDto userDto) {
        User user = userDao.findByEmail(userDto.getEmail()).get();
        userDao.delete(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    public List<UserDto> findAllUserForVerify() {
        return userDao.findAllUserForVerify().stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }


    @Override
    public UserDto getById(Integer theId) {
        Optional<User> foundUser = userDao.findById(theId);
        if (foundUser.isPresent())
            return mapper.map(foundUser.get(), UserDto.class);
        else
            throw new NotFoundDta("❌❌❌ Error not found user ❌❌❌");
    }

    @Override
    public UserDto getByEmail(String email) {//todo
        Optional<User> found = userDao.findByEmail(email);
        if (found.isPresent())
            return mapper.map(found.get(),UserDto.class);
        else throw new NotFoundDta("not user found with this email");
    }

    @Override
    public void updatePassword(String newPassword, UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(newPassword);
        userDao.save(user);
    }

    @Override
    public void updateCreditCart(double amount, UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setCreditCart(amount);
        userDao.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userDao.findByEmail(userDto.getEmail()).get();
        userDao.save(user);
    }

    public void updateStatus(String userEmail) {
        userDao.updateStatus(userEmail, UserRegistrationStatus.CONFIRMED);
    }

    @Override
    public UserDto findUserByUseAndPass(String userName, String password) {
        Optional<User> foundUser = userDao.findByUsernameAndPassword(userName, password);
        if (foundUser.isPresent())
            return mapper.map(foundUser.get(), UserDto.class);
        else
            throw new NotFoundDta("❌❌❌ Error not found user ❌❌❌");
    }

    @Override
    public boolean isDuplicateEmail(String email) {
        Optional<User> userByEmail = userDao.findByEmail(email);
        if (userByEmail.isPresent())
            return true;
        else
            return false;
    }

    @Override
    public List<UserDto> findAllUsersByFilter(UserFilterDto dto) {//String firstname, String lastname, String email, Role role
        Specification<User> userSpecification = UserSpecifications.filterUsers(dto);
        return userDao.findAll(userSpecification).stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

 /*   @Override
    public List<UserDto> findByPagination(int firstPage, int pageSize, String firstname, String lastname, String email, Role role) {
        Specification<User> userSpecification = UserSpecifications.selectByFilter(firstname, lastname, email, role);
        Page<User> pu = userDao.findAll((userSpecification), PageRequest.of(firstPage, pageSize, Sort.by("score").ascending()));
        return pu.stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }*/

    @Override
    public List<UserDto> searchUsers(UserFilterDto dto) {
        Sort sort = Sort.by("lastName").ascending();
        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize(), sort);//برای پیجینیشنه هست

        //Specification<Offer> specification
        Specification<User> specification = UserSpecifications.filterUsers(dto);
        //میتونیم یه اسپیسیفیکشن دیگه هم داشته باشیم که اینا رو با هم اند کنیم بعدش
        // Specification<Expert> specification2 = ExpertSpecifications.filterProducts(dto);
        // List<ExpertDto> experts = expertService.findAllExpertOfSubCategory(dto.getSubCategoryTitle());
        return userDao
                .findAll(Specification.where(specification), pageable)
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return userDao.findAll(pageable);
    }



  /*  public List<UserDto> findUserFilteringAndPagination(UserFilterDto filterDto) {
        PageRequest.of(filterDto.getPageNumber(),filterDto.getPageSize()
        ,Sort.by(""))

        Specification<User> userSpecification = UserDao.selectByFilter(firstname, lastname, email, role);
        return userDao.findAll(userSpecification).stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }


    @Override
    public List<ProductDto> filterProducts(ProductCategoryDto dto) {
        Sort sort = Sort.by("price").descending().and(Sort.by("name"));
        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize(), sort);


        Specification<Product> specification = ProductSpecifications.filterProducts(dto);
//        Specification<Product> specification2 = ProductSpecifications.filterProducts(dto);
        return productRepository
                .findAll(specification, pageable)
                .stream()
                .map(product -> productMapper.toProductDto(product))
                .collect(Collectors.toList());
    }
*/

  /*  @Transactional
    public List<CourseDto> findMaxMatch(CourseDto courseDto, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.Direction.ASC, "category");
        Category category = categoryService.findCategoryByName(courseDto.getCategory());
        LocalDate startDate = courseDtoConverter.stringToLocalDate(courseDto.getStartDate());
        LocalDate endDate = courseDtoConverter.stringToLocalDate(courseDto.getEndDate());
        int duration = courseDtoConverter.stringToIntConverter(courseDto.getDuration());
        int capacity = courseDtoConverter.stringToIntConverter(courseDto.getCapacity());
        Page<Course> matchedCourses = courseRepository.findAll(
                CourseSpecifications.findMaxMatch(category, courseDto.getTitle(), duration, capacity,
                        startDate, endDate), pageable);
        return courseDtoConverter.convertCourseListToDtoList(matchedCourses.getContent());
    }*/

 /* @Transactional
  public int getNumberOfPages() {
      int totalCourses = courseRepository.countAll();
      int rowsNumberInPage = Integer.parseInt(env.getProperty("Page.Rows"));
      double pages = (double) totalCourses / rowsNumberInPage;
      return (int) Math.ceil(pages);
  }

    @Transactional
    public int getNumberOfCourses() {
        return courseRepository.countAll();
    }*/

/*    @Transactional
    public long getNumberOfMatchedPages(CourseDto courseDto) {
        Category category = categoryService.findCategoryByName(courseDto.getCategory());
        LocalDate startDate = courseDtoConverter.stringToLocalDate(courseDto.getStartDate());
        LocalDate endDate = courseDtoConverter.stringToLocalDate(courseDto.getEndDate());
        int capacity = courseDtoConverter.stringToIntConverter(courseDto.getCapacity());
        int duration = courseDtoConverter.stringToIntConverter(courseDto.getDuration());
        long totalMatched = courseRepository.count(CourseSpecifications.findMaxMatch(category, courseDto.getTitle(),
                duration, capacity, startDate, endDate));
        int rowsNumberInPage = Integer.parseInt(env.getProperty("Page.Rows"));
        double pages = (double) totalMatched / rowsNumberInPage;
        return (int) Math.ceil(pages);
    }*/

/*@Transactional
public long getNumberOfTeacherCoursesPage(UserDto teacherDto) {
    User teacher = userService.findUserByEmail(teacherDto.getEmail());
    long totalCourses = courseRepository.countByParticipantsContains(teacher);
    int rowsNumberInPage = Integer.parseInt(env.getProperty("Page.Rows"));
    double pages = (double) totalCourses / rowsNumberInPage;
    return (int) Math.ceil(pages);
}*/

}
