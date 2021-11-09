package java_rustem.jpa_lessons.entyti;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Catalog {

    public static Scanner scanner = new Scanner(System.in);

    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");


    public static void main(String[] args) {
        System.out.println("Выберете действие:\n 1)Добавить товар \n 2)Добавить категорию \n 3)Редактировать");
        String actionId = scanner.nextLine();
        switch (actionId) {
            case "1" -> add_product();
            case "2" -> add_category();
            case "3" -> update();
        }
    }

    private static void add_product() {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Category> categoryTypedQuery = manager.createQuery("select c from Category c", Category.class);

        List<Category> categoryList = categoryTypedQuery.getResultList();
        for (Category category : categoryList) {
            System.out.println(category.getId() + " = " + category.getName());
        }

        System.out.println("Выберете категорию (номер): ");
        String categoryNumIn = scanner.nextLine();
        Category category = manager.find(Category.class, Long.parseLong(categoryNumIn));

        System.out.println(category.getName());
        System.out.println("Введите название: ");
        String productNameIn = scanner.nextLine();
        Product product = new Product();
        try {
            product.setCategory(category);
            product.setName(productNameIn);
            manager.getTransaction().begin();
            manager.persist(product);
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
            product.setCategory(category);
            product.setPrice(Double.parseDouble(productPriceIn));
            manager.getTransaction().begin();
            manager.persist(product);
            manager.getTransaction().commit();
        } catch (Exception e1) {
            manager.getTransaction().rollback();
            e1.printStackTrace();
        }

        List<Characteristic> characteristics = product.getCategory().getCharacteristics();
        System.out.println("---------");

        for (Characteristic characteristic : characteristics) {
            System.out.println("Введите " + characteristic.getName() + ":");
            String productMeaningIn1 = scanner.nextLine();
            Meaning meaning = new Meaning();
            meaning.setCharacteristic(characteristic);
            meaning.setProduct(product);
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

    /**
     * Добавление новой категории
     */
    private static void add_category(){
        EntityManager manager = factory.createEntityManager();
        manager.createQuery("select p from Product p");
        /*
          вывод списка существующих категорий
         */
        TypedQuery<Category> categoryTypedQuery = manager.createQuery(
                "select c from Category c", Category.class);
        List<Category> categoryList = categoryTypedQuery.getResultList();
        for (Category category : categoryList) {
            System.out.println(
                    category.getId() + " = " + category.getName());
        }

        Category category = new Category();
        System.out.println("Введите название категории: ");
        String NewCategory = scanner.nextLine();
        TypedQuery<Category> categoryNewTypedQuery = manager.createQuery(
                "select c from Category c where c.name = ?1", Category.class);
        categoryNewTypedQuery.setParameter(1, NewCategory);
        List<Category> categories = categoryNewTypedQuery.getResultList();

        while (categories.size() != 0) {
            System.out.println("Категория существует!");
            System.out.println("Введите название категории: ");
            NewCategory = scanner.nextLine();
            categoryNewTypedQuery = manager.createQuery(
                    "select c from Category c where c.name = ?1", Category.class);
            categoryNewTypedQuery.setParameter(1, NewCategory);
            categories = categoryNewTypedQuery.getResultList();
        }

        try {
            manager.getTransaction().begin();
            category.setName(NewCategory);
            manager.persist(category);
            System.out.println("Введите характеристики категории через запятую: ");
            String NewCharacteristic = scanner.nextLine();
            String[] words = NewCharacteristic.split(",\\s?");
            System.out.println(Arrays.toString(words));
            for (String word : words) {
                Characteristic characteristic = new Characteristic();
                characteristic.setCategory(category);
                characteristic.setName(word);
                manager.persist(characteristic);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Изменение товара(наименование, цена, характеристики)
     */
    private static void update (){
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Product> productTypedQuery = manager.createQuery("select p from Product p", Product.class);
         /*
          вывод списка существующих товаров
         */
        List<Product> products = productTypedQuery.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName());
        }

        System.out.println("Введите продукт (номер) :");
        String productNameIn = scanner.nextLine();
        Product product = manager.find(Product.class, Long.parseLong(productNameIn));
        System.out.println(product.getName());
        System.out.println("Введите наименование" + "[" + product.getName() + "]" + ":");
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
