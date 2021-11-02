package java_rustem.jpa_lessons.entyti;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cretate_1 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        manager.createQuery("select p from Product p");
        /*TypedQuery<Product> productTypedQuery= manager.createQuery("select p from Product p where p.price > 100000 and p.price < 500000", Product.class);
        List<Product> products= productTypedQuery.getResultList();
        for (Product product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }*/
        /*int minPrice = 100000;
        TypedQuery<Product> query = manager.createQuery("select p from Product p where p.price < " + minPrice, Product.class);
        List<Product> productList = query.getResultList();
        for (Product product : productList) {
            System.out.println(product.getName() + " = " + product.getPrice());
        }*/
/*
        double minPrice = 70000;
        double maxPrice = 500000;
        TypedQuery<Product> query = manager.createQuery("select p from Product p where p.price between ?1 and ?2", Product.class);
        // between - age >= 18 and age <= 45 - входит ли возраст в диапозон от 18 до 45.
        //           age between 18 and 45  - входит ли возраст в диапозон от 18 до 45.
        query.setParameter(1, minPrice);
        query.setParameter(2, maxPrice);
        List<Product> productList = query.getResultList();
        for (Product product : productList) {
            System.out.println(product.getName() + " = " + product.getPrice());
        }
        */

        // Query - объект для выполнения запросов на изменение данных, т.е. запросы
        //         по типу update, delete, insert, alter и т.д.
/*
        try {
            manager.getTransaction().begin();
            *//*Query query = manager.createQuery("delete from Product p where p.price < ?1");
            query.setParameter(1, 69000d); // после числа маленькая буква d преобразует тип данных в double
            // .executeUpdate() - метод для выполнения запросов без результата. Выполняет любой запрос кроме select
            query.executeUpdate();*//*
            Product product = manager.find(Product.class, 1L);
            manager.remove(product);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
        */
       /* try {
            double sum = 15000;
            double minSum = 70000;
            manager.getTransaction().begin();
            // Прибавить 15000 к стоимости всем товарам которые стоят меньше 70000
            Query query = manager.createQuery("update Product set price = price + ?1 where price <= ?2");
            query.setParameter(1, sum);
            query.setParameter(2, minSum);
            query.executeUpdate();
            manager.getTransaction().commit();
        }catch (Exception e ){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }*/

        TypedQuery<Category> categoryTypedQuery = manager.createQuery("select c from Category c", Category.class);
        List<Category> categoryList = categoryTypedQuery.getResultList();
        for (Category category : categoryList) {
            System.out.println(
                    category.getId() + " = " + category.getName());
        }
        System.out.println("------");

        Scanner scanner = new Scanner(System.in);
        Category category = new Category();
        System.out.println("Введите название категории: ");
        String NewCategory = scanner.nextLine();
        TypedQuery<Category> categoryTypedQuery1 = manager.createQuery("select c from Category c where c.name = ?1", Category.class);
        categoryTypedQuery1.setParameter(1, NewCategory);
        List<Category> categories = categoryTypedQuery1.getResultList();
        while (categories.size() != 0) {
            System.out.println("Категория существует!");
            System.out.println("Введите название категории: ");
            NewCategory = scanner.nextLine();
            categoryTypedQuery1 = manager.createQuery("select c from Category c where c.name = ?1", Category.class);
            categoryTypedQuery1.setParameter(1, NewCategory);
            categories = categoryTypedQuery1.getResultList();
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
}

// --- Создание категории ---
// Введите название категории: ____
// Введите характеристики категории через запятую: _____

// Пример:
// --- Создание категории ---
// Введите название категории: Смартфоны
//Введите характеристики категории через запятую: Материалы корпуса, Емкость аккумулятора, Кол-во ОЗУ

// В базе данных должна создатся категория с 3 характеристиками:
// Смартфоны
// * Матиралы корпуса
// * Емкость аккумулятора
// * Кол-во ОЗУ

// Добавить проверку на уникальность категории

