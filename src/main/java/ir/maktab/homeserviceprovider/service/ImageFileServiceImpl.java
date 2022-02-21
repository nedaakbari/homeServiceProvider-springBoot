package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.ImageFile;
import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.repository.ImageFileRepository;
import ir.maktab.service.interfaces.ImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
@RequiredArgsConstructor
public class ImageFileServiceImpl implements ImageFileService {

    private final ImageFileRepository imageFileRepository;

    @Override
    public void uploadImageFile(CommonsMultipartFile image, Expert expert) {
       // if (image.getName().contains(".jpg")) {
            ImageFile imageFile = new ImageFile();
            imageFile.setName(image.getOriginalFilename());
            imageFile.setData(image.getBytes());
            imageFile.setExpert(expert);
            imageFileRepository.save(imageFile);
        /*} else
            throw new RuntimeException("pic file must be .jpg");*/
    }


}
