truncate table meaning;
truncate table characteristic;
truncate table products;
truncate table category_products;


insert into category_products(id, category_name)
values (1, 'Мониторы'),
       (4, 'Фотоопараты');

insert into products(category_id, product_name, price)
values (1, 'Монитор Игровой Samsung Dark Blue', 63990 ),
       (4, 'Беззеркальный фотоаппарат Nikon Z', 949990),
       (4, 'Цыфровой фотоопарат Matrix', 450990);

insert into characteristic(category_id, name)
values (1, 'Тип матрицы'),
       (1, 'Разрешение экрана'),
       (4, 'Формат матрицы'),
       (4, 'Форматы изображения');

insert into meaning(product_id, characteristic_id, text)
values (1, 1, 'IPS'),
       (1, 2, '1920x1080 (FHD)'),
       (2, 1, 'APS-C'),
       (2, 2, 'APS-Cx'),
       (2, 1, 'JPEG'),
       (2, 2, 'JPEG, RAW');