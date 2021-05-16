INSERT INTO users (email, first_name, last_name, password)
VALUES ('user@yandex.ru', 'User_First', 'User_Last', '{noop}password'),
       ('admin@gmail.com', 'Admin_First', 'Admin_Last', '{noop}admin'),
       ('admin2@gmail.com', 'Admin2_First', 'Admin2_Last', '{noop}admin'),
       ('user2@yandex.ru', 'User2_First', 'User2_Last', '{noop}password');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('ADMIN', 3),
       ('USER', 4);

INSERT INTO dish (date, name, price, restaurant, user_id)
VALUES ('2021-05-15', 'dish1', 70, 'restaurant1', 2),
       ('2021-05-16', 'dish2', 50, 'restaurant1', 2),
       ('2021-05-16', 'dish3', 190, 'restaurant1', 2),
       ('2021-05-15', 'dish4', 50, 'restaurant2', 3),
       ('2021-05-16', 'dish5', 110, 'restaurant2', 3),
       ('2021-05-16', 'dish6', 130, 'restaurant2', 3),
       ('2021-05-16', 'dish7', 80, 'restaurant2', 3);

INSERT INTO vote (date_time, restaurant, user_id)
VALUES ('2021-05-14', 'restaurant1', 1),
       ('2021-05-14', 'restaurant1', 4),
       ('2021-05-15', 'restaurant1', 1),
       ('2021-05-15', 'restaurant2', 2),
       ('2021-05-15', 'restaurant2', 4),
       ('2021-05-16', 'restaurant2', 1),
       ('2021-05-16', 'restaurant2', 2);