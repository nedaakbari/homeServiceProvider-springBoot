package ir.maktab.homeserviceprovider.service.interfaces;


import ir.maktab.data.entity.Person.Expert;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ImageFileService {

    void uploadImageFile(CommonsMultipartFile image, Expert expert);
}
