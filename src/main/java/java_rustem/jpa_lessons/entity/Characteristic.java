package java_rustem.jpa_lessons.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characteristic")
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    private List<Meaning> meanings;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public List<Meaning> getMeanings() {

        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {

        this.meanings = meanings;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
