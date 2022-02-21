package ir.maktab.homeserviceprovider.data.entity.service;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotNull(message = "title cannot be null")
    private String title;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)// todo میخوام وقتی یه کتگوری دیلیت یا ابدیت میشه همه زیر خدمت هاش هم دیلیت یا ابدیت بشن
    private Set<SubCategory> subServiceList = new HashSet<>();

    @Override
    public String toString() {
        return "MainService{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
