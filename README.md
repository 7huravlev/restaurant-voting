# Java Enterprise Project Restaurant Voting
Spring Boot

Contact Nikolay Zhuravlev

### Description

Project of a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

## REST API

http://localhost:8080/swagger-ui.html

### 1. Get All Users
- URL: /api/users
- Method: GET
- URL Params: None
- Data Params: None
- Success Response:

> Code: 200 OK  
> Content: [  
> {  
> “email”:[String],  
> “firstName”:[String],  
> “lastName”:[String],  
> “roles”:[Role],  
> “new”:[Boolean]
> },  
> …   
> ]

`curl -s http://localhost:8080/api/users --user admin@gmail.com:admin`

### 2. Get User
- URL: /api/users/{id}
- Method: GET
- URL Params: id
- Data Params: None
- Success Response:

> Code: 200 OK  
> Content: {  
> “email”:[String],  
> “firstName”:[String],  
> “lastName”:[String],  
> “roles”:[Role],  
> “new”:[Boolean]  
> }

`curl -s http://localhost:8080/api/users/1 --user admin@gmail.com:admin`

### 3. Register User
- URL: /api/account/register
- Method: POST
- URL Params: None
- Data Params:  
> {  
> “firstName”:[String],  
> “lastName”:[String],  
> “email”:[String],  
> “password”:[String]  
> }

- Success Response:  
> Code: 200 OK  
> Content: {    
> “id”:[String],  
> “email”:[String],  
> “firstName”:[String],  
> “lastName”:[String],  
> “roles”:[Role],  
> “new”:[Boolean]  
> }

`curl -s -i -X POST -d '{"firstName":"New User First Name", "lastName":"New User Last Name","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/account/register`

### 4. Get Profile
- URL: /api/account
- Method: GET
- URL Params: None
- Data Params: None
- Success Response:

> Code: 200 OK  
> Content: {    
> “id”:[String],  
> “email”:[String],  
> “firstName”:[String],  
> “lastName”:[String],  
> “roles”:[Role],  
> “new”:[Boolean]  
> }

`curl -s http://localhost:8080/api/account --user test@mail.ru:test-password`

### 5. Get Menu
- URL: /api/dishes
- Method: GET
- URL Params: None
- Data Params: None
- Success Response:

> Code: 200 OK  
> Content: [  
> {  
> “date”:[Date],  
> “restaurant”:[String],  
> “name”:[String],  
> “price”:[Integer],  
> “new”:[Boolean],  
> “user”:[User]  
> },  
> …   
> ]

`curl -s http://localhost:8080/api/dishes --user admin@gmail.com:admin`

### 6. Create Dish
- URL: /api/dish
- Method: POST
- URL Params:
> {  
> “date”:[Date],  
> “restaurant”:[String],  
> “name”:[String],  
> “price”:[Integer]  
> }

- Success Response:
> Code: 200 OK  
> Content: {     
> “id”:[String],  
> “date”:[Date],  
> “restaurant”:[String],  
> “name”:[String],  
> “price”:[Integer],  
> “new”:[Boolean]  
> }

`curl -s -X POST -d '{"date":"2021-05-16","restaurant":"restaurant4","name":"dish9","price":210}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/dish --user admin@gmail.com:admin`

### 7. Update Dish
- URL: /api/dish
- Method: PUT
- URL Params:
> {  
> “date”:[Date],  
> “restaurant”:[String],  
> “name”:[String],  
> “price”:[Integer]  
> }

- Success Response:
> Code: 200 OK  

`curl -s -X PUT -d '{"date":"2021-05-16","restaurant":"restaurant4","name":"dish13 changed","price":730}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/dish/3 --user admin@gmail.com:admin`

### 8. Restaurant vote
- URL: /api/vote
- Method: POST
- URL Params:
> {  
> “date_time”:[DateTime],  
> “restaurant”:[String]  
> }

- Success Response:
> Code: 200 OK  
> Content: {     
> “id”:[String],  
> “date_time”:[DateTime],  
> “restaurant”:[String],  
> “new”:[Boolean]  
> }

`curl -s -X POST -d '{"date_time":"2021-05-16T10:30:00.000","restaurant":"restaurant1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/vote --user user2@yandex.ru:password`



