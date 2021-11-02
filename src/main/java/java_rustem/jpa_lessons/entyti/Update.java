package java_rustem.jpa_lessons.entyti;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoublePredicate;

public class Update {
    public static void main(String[] args) {

        // Изменить наиминование товара и цену. Категорию изменять нельзя
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");

        EntityManager manager = factory.createEntityManager();

        TypedQuery<Product> productTypedQuery = manager.createQuery("select p from Product p", Product.class);
        List<Product> products = productTypedQuery.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName());
        }
        System.out.println("--------");

        Scanner scanner = new Scanner(System.in);// ссистемный ввод, (.out) истемный вывод
        System.out.println("Введите продукт (номер) :");
        String productNameIn = scanner.nextLine();
        Product product = manager.find(Product.class, Long.parseLong(productNameIn));
        System.out.println(product.getName());

        // Введите название [Old name]: ___
        // Введите цену [Old price]: ___
        System.out.println("Введите наиминование" + "[" + product.getName() + "]" + ":");
        String productNewName = scanner.nextLine();
        if (!productNewName.isEmpty()) {
            product.setName(productNewName);
        }
        System.out.println("Введите цену" + "[" + product.getPrice() + "]" + ":");
        String productNewPrice = scanner.nextLine();
        if (!productNewPrice.isEmpty()){
            product.setPrice(Double.parseDouble(productNewPrice));
        }
        try {
            manager.getTransaction().begin();
            manager.persist(product);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }


        List<Characteristic> characteristics = product.getCategory().getCharacteristics();
        for (Characteristic characteristic : characteristics) {
            TypedQuery<Meaning> meaningTypedQuery = manager.createQuery("select m from Meaning m " +
                    "where m.product = ?1 and m.characteristic = ?2", Meaning.class);
            meaningTypedQuery.setParameter(1, product);
            meaningTypedQuery.setParameter(2, characteristic);
            List<Meaning> meanings = meaningTypedQuery.getResultList();
            System.out.println("Введите " + characteristic.getName() + "[" + meanings.get(0).getText() + "]" + " :");
            String productMeaningNew = scanner.nextLine();
            if (!productMeaningNew.isEmpty()){
                meanings.get(0).setText(productMeaningNew);
            }
            try {
                manager.getTransaction().begin();
                manager.persist(meanings.get(0));
                manager.getTransaction().commit();
            } catch (Exception e1) {
                manager.getTransaction().rollback();
                e1.printStackTrace();
            }
        }
    }
}