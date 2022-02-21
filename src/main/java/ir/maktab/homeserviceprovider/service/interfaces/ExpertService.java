package ir.maktab.homeserviceprovider.service.interfaces;

import ir.maktab.data.entity.Person.Expert;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.ExpertFilterDto;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface ExpertService {

    ExpertDto register(ExpertDto expertDto, CommonsMultipartFile image);

    ExpertDto login(ExpertDto expertDto);

    void delete(ExpertDto expertDto);

    List<ExpertDto> getAll();

    ExpertDto getById(Integer theId);

    ExpertDto findExpertByUseAndPass(String username, String password);

    void updateAccNumber(long accNumber, ExpertDto expertDto);

    void updateCreditCart(double amount, String expertEmail);

    void updatePassword(String newPassword, String oldPassword, ExpertDto expertDto);

    void updateScore(Expert expert, double getScore);

    void removeSubCategoryFromExpertList(ExpertDto expertDto, String subTitle);

    void addSubCategoryToExpertList(ExpertDto expertDto, String subCategoryTitle);

    ExpertDto findByEmail(String email);

    List<ExpertDto> searchExperts(ExpertFilterDto dto);

    ExpertDto findByEmailAndPassWord(ExpertDto expertDto);

    List<ExpertDto> findAllExpertOfSubCategory(String subCategoryTitle);
}
