package java_rustem.jpa_lessons.entyti;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "product_name")
    private String name;

    @OneToMany (mappedBy = "product", cascade = CascadeType.REMOVE) // каскадные действия, отвечают за то что должно
    // произойти с записями приявязвнными к базовой записи с которой производится удоления либо изменения
    // cascade = CascadeType.REMOVE - при удоление продукта удоляются привязанные записи ( характеристики )
    // cascade = CascadeType.REMOVE - в случае удоления основной записи удоляют все привязанные записи из внешней таблицы
    private List<Meaning> meaning;

    private double price;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Meaning> getMeaning() {
        return meaning;
    }

    public void setMeaning(List<Meaning> meaning) {
        this.meaning = meaning;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
