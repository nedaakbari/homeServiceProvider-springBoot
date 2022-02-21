package ir.maktab.homeserviceprovider.data.repository;

import ir.maktab.data.entity.ImageFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageFileRepository extends CrudRepository<ImageFile, Long> {
}
