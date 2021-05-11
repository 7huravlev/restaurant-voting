# Java Enterprise Project Restaurant Voting
Spring Boot

Contact Nikolay Zhuravlev

## REST API

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
- URL: /api/dishes
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

`curl -s -X POST -d '{"date":"2020-02-01","restaurant":"restaurant4","name":"dish9","price":210}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/dish --user admin@gmail.com:admin`
