package java_rustem.jpa_lessons.entyti;

import javax.persistence.*;
import java.util.List;

@Entity // указывает что класс будет взаимодействать с базой данных
@Table(name = "category_products") // название таблицы к кторой привязываем
public class Category {

    @Id // указание поля первичного ключа (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // указывает тип генерации для значения поля
    private long id;

    @OneToMany(mappedBy = "category")
    private List<Characteristic> characteristics;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Column(name = "category_name") // указывает на какое поле должны ссылатся текущие поля класса
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