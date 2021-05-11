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
VALUES (sysdate(), 'dish1', 70, 'restaurant1', 2),
       (sysdate(), 'dish2', 50, 'restaurant1', 2),
       (sysdate(), 'dish3', 190, 'restaurant1', 2),
       (sysdate(), 'dish4', 50, 'restaurant2', 3),
       (sysdate(), 'dish5', 110, 'restaurant2', 3),
       (sysdate(), 'dish6', 130, 'restaurant2', 3),
       (sysdate(), 'dish7', 80, 'restaurant2', 3);