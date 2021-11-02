package java_rustem.jpa_lessons.entyti;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create {

    public static void main(String[] args) {
        // описать интерфейс для сохдания товара для того чтобы он сохранился в базе данных
        // Комплектующие [1]
        // Аудиотехника [2]
        // Мебель [3]
        // Выберите категорию (номер): ___
        // Введите название:____
        // Введите цену:____

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");

        EntityManager manager = factory.createEntityManager();

        TypedQuery<Category> categoryTypedQuery = manager.createQuery("select c from Category c", Category.class);
        List<Category> categoryList = categoryTypedQuery.getResultList();
        for (Category category : categoryList) {
            System.out.println(
                    category.getId() + " = " + category.getName());
        }
        System.out.println("------");
        /*TypedQuery<Product> productTypedQuery = manager.createQuery("select p from Product p", Product.class);
        List<Product> products = productTypedQuery.getResultList();
        for (Product product1 : products) {
            System.out.println(product1.getId() + " = " + product1.getName());
                System.out.println(product1.getId() + " = " + product1.getPrice());
        }
        System.out.println("-------");*/

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберете категорию (номер): ");
        String categoryNumIn = scanner.nextLine();
        Category category = manager.find(Category.class, Long.parseLong(categoryNumIn));
        System.out.println(category.getName());

        System.out.println("Введите название: ");
        String productNameIn = scanner.nextLine();
        Product product1 = new Product();
        try {
            product1.setCategory(category);
            product1.setName(productNameIn);
            manager.getTransaction().begin();
            manager.persist(product1);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }

        System.out.println("Введите цену: ");
        String productPriceIn = scanner.nextLine();

        while (!productPriceIn.matches("\\d+")) {
            System.out.println("не верный формат!");
            System.out.println("Введите цену: ");
            productPriceIn = scanner.nextLine();
        }
        try {
            product1.setCategory(category);
            product1.setPrice(Double.parseDouble(productPriceIn));
            manager.getTransaction().begin();
            manager.persist(product1);
            manager.getTransaction().commit();
        } catch (Exception e1) {
            manager.getTransaction().rollback();
            e1.printStackTrace();
        }

        List<Characteristic> characteristics = product1.getCategory().getCharacteristics();
        System.out.println("---------");

        for (Characteristic characteristic : characteristics) {
            System.out.println("Введите " + characteristic.getName() + ":");
            String productMeaningIn1 = scanner.nextLine();
            Meaning meaning = new Meaning();
            meaning.setCharacteristic(characteristic);
            meaning.setProduct(product1);
            meaning.setText(productMeaningIn1);
            try {
                manager.getTransaction().begin();
                manager.persist(meaning);
                manager.getTransaction().commit();
            } catch (Exception e2) {
                manager.getTransaction().rollback();
                e2.printStackTrace();
            }
        }
    }
}
