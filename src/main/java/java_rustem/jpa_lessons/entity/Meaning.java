package java_rustem.jpa_lessons.entity;

import javax.persistence.*;

@Entity
@Table(name = "meaning")
public class Meaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "characteristic_id")
    private Characteristic characteristic;

    private String text;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {

        this.product = product;
    }

    public Characteristic getCharacteristic() {

        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {

        this.characteristic = characteristic;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }
}
