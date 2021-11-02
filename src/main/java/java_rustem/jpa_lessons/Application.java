package java_rustem.jpa_lessons;

import java_rustem.jpa_lessons.entyti.Category;
import java_rustem.jpa_lessons.entyti.Characteristic;
import java_rustem.jpa_lessons.entyti.Meaning;
import java_rustem.jpa_lessons.entyti.Product;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        // Maven -  система сборки для Java проектов позволяющая автоматизировать
        // проыесс поключения внешних библиотек и процесс компоновки в результирующий jar (Java приложние),
        // war (веб java приложение) или ear (несколько скомпонованных приложений в одно общее) архив.

        // JPA - нобор спецификаций (стандарт), который описывает как java должна взаимодействовать
        // с реляционными (табличными) базами данных по принципу ORM.
        // Так как JPA  всего лишь стандарт, он не предоставляет ни одного конкретного класса, а только описывает
        // как они (классы) должны быть реализованными.

        // ORM (Object Relational Mapping -  Объектно Реляционный Маппинг) - подход который подрузамивает
        // преоброзование данных из таблиц к объектам языка програмированния.


        // table users
        // * id bigint unsigned ...
        // * first_name varchar(50) ...
        // * last_name varchar (50)...
        // * salary decimal ...
        // * birthdate date ...

        // class User
        // * long id
        // * String first_name
        // * String last_name
        // * Double salary
        // * LocalDate birthdate

        // Сущесивует множество реализаций стандарта JPA, наиболее популярные из них
        // * Hibernate - применяется повсеместно, обрела большую популярность за счет пости 100% поддержки стандарта JPA
        // * EclipseLink - еще одна реализация.
        // * ... и т.д.

        // hibernate-core - название библиотеки которую необходимо поключить для того чтобы пользоватся возмоднастями
        // Hibernate и JPA  в частности.

        // mvnrepository.org - центральное хранилище JAVA библиотек

        // сущность - класс который описывает определенный элемент из базы данных

        // persistence.xml - конфигурационный файл в котором хранятся параментры подключения к базе данных.

        // Java Application -> Java MySql Driver -> MySql Database
        // Java Application -> Python MySql Driver -> MySql Database
        // Java Application -> C++ MySql Driver -> MySql Database


        //EntityManagerFactory - создает объект подключения к базе данных и держит его в открытом состоянии.
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        // Persistence - клас через который создается объект ManagerFactory
        // EntityManager = отвечает за взаимодействие с сущностями (выборка, запись , редоктирование, удоление и т.д.)
        EntityManager manager = factory.createEntityManager();
        // find() - позволяет получить сущность имеющий первичный ключ

        // второй параметр find - значение для поля в сущности, которое помечено при помощи вннотации @Id. (ключ)
        /*
        Category category = manager.find(Category.class, 4L);
        if (category != null) {
            System.out.println(category.getName());
            List<Product> products1 = category.getProducts();
            for (Product product : products1) {
                System.out.println(product.getName());
            }
            List<Product> products = category.getProducts();
            long sum = 0;
            for (Product product : products) {
                sum += product.getPrice();
            }
            System.out.println(sum / products.size());
        }
        // В данный момент category1 является обычным Java объектом никак не привязанным к базе данных.
        Category category1 = new Category();
        category1.setName("Мебель");

        Characteristic characteristic1 = new Characteristic();
        characteristic1.setCategory(category);
        characteristic1.setName("Материал");

        Characteristic characteristic2 = new Characteristic();
        characteristic2.setCategory(category);
        characteristic2.setName("Ширина");

        Characteristic characteristic3 = new Characteristic();
        characteristic3.setCategory(category);
        characteristic3.setName("Высота");

        try {
            *//*List<Characteristic> characteristics = new ArrayList<>();
        characteristics.add(characteristic1);
        characteristics.add(characteristic2);
        characteristics.add(characteristic3);
        category.setCharacteristics(characteristics);*//*
            // *.getTransaction().begin() - начинает новую транзакцию. Транзакция определяет список изменений
            // которые должны быть зафиксированны в базе данных.
            manager.getTransaction().begin();
            // *.persist(Object o) - привязывает объект к EntityManager-y, тем самым делая его сущностью.
            manager.persist(category1);
            manager.persist(characteristic1);
            manager.persist(characteristic2);
            manager.persist(characteristic3);
            // *.getTransaction().commit() - тправляет все изменения зафиксированные транзакцией в базу данных.
            manager.getTransaction().commit();
            // Exception e - общий род.класс для все ошибок в Java.
        } catch (Exception e) {
            // *.getTransaction().rollback() - отменяет все изменения связанные с транзакцией.
            manager.getTransaction().rollback();
            e.printStackTrace();// вывод все информации которая была об ошибке.
        }
*/

        // Взаимодействие с базой данных через EntityManager:
        // 1) Начало транзакции - этап в котором создается пустой набор задач.
        // 2) Локальные изменения - операции создания новых сущностей, изменениния либо удоления существующих и т.д..
        //    Все локальные изменения фиксируются в транзакции.
        // 3) Подтверждение транзакции - процесс фиксации локальных изменений в соответсвии с
        //    сосотоянием базы данных на момент выполнения.

        // Category.class = информация по типу данных
        // Class <T> -> Class<Category> <- Category.class
      /*  Category category = manager.find(Category.class, 6L);
        try {
            manager.getTransaction().begin();
            category.setName("Шкафы"); // локальное измененние объекта
            manager.getTransaction().commit();
        }catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }*/

       /* Category category = manager.find(Category.class, 6L);
        try {
            manager.getTransaction().begin();
            // .remove - удоляет сущности превращая его в обычный класс
            manager.remove(category);
            manager.getTransaction().commit();
        }catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }*/

        // TypedQuery <X> - объект применяемый для написания и выполнения запросов кторое подразумевают
        // наличие результата, где Х, тип данных результата.
        // Запросы в JPA  формируются при помощи спец.языка, JPQL.
        // При большей части JPQL это тот же самый SQL, но вместо таблиц он взаимодействует с сущностями JPA.

        // SQL -> select * from categories where like 'A%' order by name
        // JPQL -> select c from Category c where c.name like 'A%' order by c.name

        TypedQuery<Category> query = manager.createQuery("select c from Category c", Category.class);
        // select - запрос на наличие объекта
        List<Category> categories = query.getResultList();
        for (Category category : categories) {
            System.out.println(category.getName());
        }


    }
}

