package ir.maktab.homeserviceprovider.data.entity;

import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.enums.ImageType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ImageType type = ImageType.PROFILE;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] data;

    @ManyToOne
    private Expert expert;

}
