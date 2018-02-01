create table if not exists MANUFACTURER(
ID INT(11) not null primary key auto_increment,
NAME varchar (50) not null
);

create table if not exists PRODUCT(
ID INT(11) not null primary key auto_increment,
NAME varchar (50) not null,
PRICE decimal not null,
ID_MANUFACTURER int not null,
FOREIGN KEY (ID_MANUFACTURER) REFERENCES MANUFACTURER (ID_MANUFACTURER)
);

INSERT INTO manufacturer VALUES
(1, 'Zolotoy_vek'), -- ювалирная
(2, 'Derby'), -- Чемоданы, сумки
(3, 'VIF'), -- Чемоданы, сумки
(4, 'Enjee'), -- Товары для детей
(5, 'Buol'), -- Посуда
(6, 'Mafia'), -- eat
(7, 'Katysha'); -- eat

INSERT INTO product VALUES
(1, 'Ring', 2986 , 1),
(2, 'Bracelet', 3976, 1),
(3, 'Conversation', 8346, 1),
(4, 'Pendant', 6423, 1),
(5, 'pizza', 259, 6),
(6, 'sushi', 346, 6),
(7, 'pasta', 120, 7),
(8, 'vareniki', 80, 7),
(9, 'backpack', 964, 2),
(10, 'clutch', 679, 2),
(11, 'purse', 349, 2),
(12, 'car', 497, 4),
(13, 'doll', 687, 4),
(14, 'train', 346, 4),
(15, 'briks', 763, 4),
(16, 'fork', 48, 5),
(17, 'spoon', 56, 5),
(18, 'bowl', 105, 5),
(19, 'kettle', 1300, 5),
(20, 'suitcase', 989, 3),
(21, 'bag', 973, 3);