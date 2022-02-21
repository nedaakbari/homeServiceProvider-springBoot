package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.entity.service.SubCategory;
import ir.maktab.data.enums.Role;
import ir.maktab.data.enums.UserRegistrationStatus;
import ir.maktab.data.repository.ExpertRepository;
import ir.maktab.data.repository.SubCategoryRepository;
import ir.maktab.data.repository.specification.ExpertSpecifications;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.ExpertFilterDto;
import ir.maktab.service.exception.*;
import ir.maktab.service.interfaces.ExpertService;
import ir.maktab.service.interfaces.ImageFileService;
import ir.maktab.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertDao;
    private final UserService userService;
    private final ImageFileService imageFileService;
    private final ModelMapper mapper;
    private final SubCategoryRepository subService;


    @Override
    public ExpertDto register(ExpertDto expertDto, CommonsMultipartFile image) {
        Optional<Expert> foundExpert = expertDao.findByUsernameAndPassword(expertDto.getUsername(), expertDto.getPassword());

        if (foundExpert.isPresent()) {
            throw new DuplicateData("this expert is already exist");
        } else {
            boolean duplicateEmail = userService.isDuplicateEmail(expertDto.getEmail());
            if (!duplicateEmail) {
               // foundExpert.get().setConfirmationToken(UUID.randomUUID().toString());
                Expert expert = mapper.map(expertDto, Expert.class);
                expert.setStatus(UserRegistrationStatus.NEW);
                expert.setRole(Role.EXPERT);
                Expert saved = expertDao.save(expert);
                imageFileService.uploadImageFile(image, saved);
                return mapper.map(saved, ExpertDto.class);
            } else
                throw new DuplicateData("this email is already exist");
        }
    }

    @Override
    public ExpertDto login(ExpertDto expertDto) {//can't private because of implement interface and have no body
        Optional<Expert> expert = expertDao.findByEmailAndPassword
                (expertDto.getEmail(), expertDto.getPassword());
        if (expert.isPresent())
            return mapper.map(expert.get(), ExpertDto.class);
        else
            throw new NotFoundDta("no user found with these info");
        //throw new ExpertNotFoundException("not.found");
    }

    @Override
    public void delete(ExpertDto expertDto) {
        Optional<Expert> expert = expertDao.findByEmail(expertDto.getEmail());
        if (expert.isPresent())
            expertDao.delete(expert.get());
        else throw new UserNotFoundException();
    }

    @Override
    public List<ExpertDto> getAll() {
        return expertDao.findAll().stream()
                .map(expert -> mapper.map(expert, ExpertDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExpertDto getById(Integer theId) {
        Optional<Expert> found = expertDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), ExpertDto.class);
        else throw new NotFoundDta("no expert found");
    }

    @Override
    public ExpertDto findByEmail(String email) {
        Optional<Expert> found = expertDao.findByEmail(email);
        if (found.isPresent()) {
            return mapper.map(found.get(), ExpertDto.class);
        } else throw new NotFoundDta("expert not exist!");
        //return expert.orElseThrow(() -> new NotFoundDta("expert not exist!"));
    }

    @Override
    public ExpertDto findByEmailAndPassWord(ExpertDto dto) {
        Optional<Expert> found = expertDao.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (found.isPresent()) {
            return mapper.map(found, ExpertDto.class);
        } else {
            throw new ExpertNotFoundException("no expert founded");
        }
    }

    @Override
    public ExpertDto findExpertByUseAndPass(String username, String password) {
        Optional<Expert> expert = expertDao.findByUsernameAndPassword(username, password);
        if (expert.isPresent()) {
            return mapper.map(expert.get(), ExpertDto.class);
        } else
            throw new NotFoundDta("no expert found with these use and pass");
    }


    @Override
    public void removeSubCategoryFromExpertList(ExpertDto expertDto, String subTitle) {
        Expert expert = expertDao.findByEmail(expertDto.getEmail()).get();
        SubCategory subCategory = subService.findByTitle(subTitle).get();
        Set<SubCategory> expertList = expert.getSubCategoryList();
        expertList.remove(subCategory);
        expert.setSubCategoryList(expertList);
        expertDao.save(expert);
    }

    @Override
    public void addSubCategoryToExpertList(ExpertDto expertDto, String subCategoryTitle) {
        Expert expert = expertDao.findByEmail(expertDto.getEmail()).get();
        Optional<SubCategory> subCategory = subService.findByTitle(subCategoryTitle);
        if (subCategory.isPresent()) {
            Set<SubCategory> expertList = expert.getSubCategoryList();
            expertList.add(subCategory.get());
            expert.setSubCategoryList(expertList);
            expertDao.save(expert);
        } else throw new NotFoundDta("no data found");
    }

    @Override
    public void updateCreditCart(double amount, String expertEmail) {
        expertDao.updateCreditCart(expertEmail, amount);
    }

    @Override
    public void updateScore(Expert expert, double getScore) {
        double expertScore;
        double newScore;
        System.out.println(expert.getScore());
        if (expert.getScore() == null) {
            newScore = getScore;
        } else {
            expertScore = expert.getScore();
            newScore = expertScore + ((expertScore + getScore) / 2);
        }

        expertDao.updateScore(expert.getEmail(), newScore);
    }

    @Override
    public void updatePassword(String newPassword, String oldPassword, ExpertDto expertDto) {
        if (expertDto.getPassword().equals(oldPassword)) {
            expertDao.updatePassword(newPassword, expertDto.getEmail());
        } else
            throw new IncorrectInformation("incorrect password");
    }

    @Override
    public void updateAccNumber(long accNumber, ExpertDto expertDto) {
        expertDao.updateAccNumber(expertDto.getEmail(), accNumber);
    }

    public void updateStatus(String userEmail) {
        expertDao.updateStatus(userEmail, UserRegistrationStatus.CONFIRMED);
    }


    @Override
    public List<ExpertDto> findAllExpertOfSubCategory(String subCategoryTitle) {
        List<Expert> experts = expertDao.findExpertsOfASubCategory(subCategoryTitle);
        return experts.stream().map(expert -> mapper.map(expert, ExpertDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ExpertDto> searchExperts(ExpertFilterDto dto) {
        Sort sort = Sort.by("lastName").ascending();
        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize(), sort);
        Specification<Expert> specification = ExpertSpecifications.filterExpert(dto);

        return expertDao.
                findAll(Specification.where(specification), pageable)
                .stream().map(expert -> mapper.map(expert, ExpertDto.class))
                .collect(Collectors.toList());
    }
}
