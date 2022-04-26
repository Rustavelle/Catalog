SET FOREIGN_KEY_CHECKS = 0;
truncate table meaning;
truncate table characteristic;
truncate table products;
truncate table category_products;
SET FOREIGN_KEY_CHECKS = 1;

insert into category_products(id, category_name)
values (1, 'Мониторы'),
       (2, 'Фотоаппараты');

insert into products(category_id, product_name, price)
values (1, 'Монитор Игровой Samsung Dark Blue', 63990 ),
       (2, 'Без зеркальный фотоаппарат Nikon Z', 949990),
       (2, 'Цифровой фотоаппарат Matrix', 450990);

insert into characteristic(category_id, name)
values (1, 'Тип матрицы'),
       (1, 'Разрешение экрана'),
       (2, 'Формат матрицы'),
       (2, 'Форматы изображения');

insert into meaning(product_id, characteristic_id, text)
values (1, 1, 'IPS'),
       (1, 2, '1920x1080 (FHD)'),
       (2, 1, 'APS-C'),
       (2, 2, 'APS-Cx'),
       (2, 1, 'JPEG'),
       (2, 2, 'JPEG, RAW');