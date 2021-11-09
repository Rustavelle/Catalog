package java_rustem.jpa_lessons.entyti;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_products")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "category")
    private List<Characteristic> characteristics;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Column(name = "category_name")
    private String name;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public List<Characteristic> getCharacteristics() {

        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {

        this.characteristics = characteristics;
    }

    public List<Product> getProducts() {

        return products;
    }

    public void setProducts(List<Product> products) {

        this.products = products;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}