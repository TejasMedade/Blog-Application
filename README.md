# Blogging Application

![Blogging Application](https://user-images.githubusercontent.com/105907169/209240082-4c5793e1-e72f-4778-bb9f-194bbdc4d3a1.jpg)


 ## **REST API SERVICE**

**REST API** allows **Bloggers / Users** to **Post** their ideas and views with **Images** as **Interactive Blogs** on their profile, **Users** can **View & Search** all the **Blogs & Blog Categories** as per their personal interests & can **Comment** their views on the **Posts**.
  
API also provides **User, Admin Spring Security & JWT Cookie Token Based Authentication, Validation & Authorization**. 

API also provides **Pagination, Sorting & Searching features**.

API also provides **Custom Exception Handling for all Exceptions & Validations**.

API also provides **Custom Request and Response Data Transfer Objects for all HTTP Requests**.

The API's primary objective is to provide **Users with both a Simple & User-Friendly Blogging Experience**.

The application will be used by the **two** categories of users:

- **Administrator**

- **Customer** 

## Tech Stack

- JAVA
- SPRING
- SPRINGBOOT
- HIBERNATE
- MAVEN
- J.D.B.C
- MYSQL
- POSTMAN

## Dependencies

- JWT AUTHENTICATION
- SPRING SECURITY
- SPRING DATA JPA 
- SPRING BOOT DEVTOOLS
- SPRING WEB
- HIBERNATE
- MYSQL DRIVER
- VALIDATION
- LOMBOK
- MODEL MAPPER
- LOGGER


## System Structure

The REST API allows a Customer to **Signup, Signin** & **View, Create Posts and Comment on Blogs**, as well as the Administrator view all features and services provided by the API for better customer support. 

- User / Blogger
    -
    - Signup
    - Signin & Signout
    - Update all User Details
    - View, Create, Update & Delete Posts
    - View, Create, Update & Delete Categories
    - Add Comments on the Post
    - Update Posts Images
    - Search & Sort Posts 
    - Search Users by Name
    - View Posts Based by Custom Pagination
    - Delete Account


- Administrator
    -
    - Signin & Signout
    - SignUp other Admins 
    - View all Users, Posts, Categories & Comments
    - Delete Admin from Database

- In Progress
    -
    - Add Validation
    - Search Users by name
    - Default Image Setup
    - Update Post Should Have Update Image Option as well
    - Only Admins can Other Delete Admins

## Setting & Installation 

Install the Spring Tools Suite 
```bash
https://spring.io/tools
```

Install MySQL Community Server

```bash
https://dev.mysql.com/downloads/mysql/
```

Clone the Project

```bash
git clone https://github.com/TejasMedade/Blog-Application
```

Open MySQL Server
```bash
Create a New Database in SQL: "blog_db" 
```
Admin Login Details For Your Database

```bash
{
    "password": "Tejas@1998",
    "username": "tejasmedade@gmail.com"
}
```

**IMPORTANT NOTE**

```bash
Use PostMan software.
JWT Authentication is Cookie Based Authentication, Make sure your request has embedded cookies. 
Roles are already set into the database, Fire appropriate requests for Admin and User respectively.
An Admin is also an User.
Only Users with Admin roles can create other Admins for the database.
All GET HttpMethods are Public.
```


## Run Locally


Go to the Project Directory

```bas
Open the Blog Application Folder with S.T.S
```

Go to **src/main/resources > application.properties** & change your username and password (MySQL server username & password)

```bash
spring.datasource.username="username"

spring.datasource.password="password"
```

To change the **Server Port**

```bash
server.port=8088
```

Go to **com.masai package > Blog_Application.java**

```bash
Run as Spring Boot App !
```
Swagger-UI 
```bash
Swagger Doesn't Support Cookie Based Authorization, Will Try To Implement It Once Swagger Provide Those Features.
```
 
## ER Diagram
 


## URL
```bash
http://localhost:8088
```
## API REFERENCES


## Authorization & Authentication Controller 

![Screenshot 2022-12-22 at 04-53-01 Swagger UI](https://user-images.githubusercontent.com/105907169/209022327-180a8d24-6bf8-45e0-90e9-9cda080769ca.png)

## User Controller

![Screenshot 2022-12-22 at 04-54-17 Swagger UI](https://user-images.githubusercontent.com/105907169/209022330-974606a5-8339-4a9b-ba5c-c74e5a9408cc.png)

## Post Controller

![Screenshot 2022-12-22 at 04-54-05 Swagger UI](https://user-images.githubusercontent.com/105907169/209022334-735db752-6148-4a44-99da-5d0bb4b5a5fe.png)


## Category Controller

![Screenshot 2022-12-22 at 04-53-15 Swagger UI](https://user-images.githubusercontent.com/105907169/209022324-da219775-d39b-4d60-ac38-345ea3604656.png)


## Comment Controller

![Screenshot 2022-12-22 at 04-53-53 Swagger UI](https://user-images.githubusercontent.com/105907169/209022336-7cac4c01-e9e4-4baa-9d01-cbb984797852.png)


## Contributions

Contributions are always **Welcome** !

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **Greatly Appreciated**.

If you have a suggestion that would make this REST API better, Please fork the repo and create a pull request. You can also connect with me for further development of this application !

Don't forget to give the project a star ! Thank You !

## 🔗 Contact Me

[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://tejasmedade.github.io/)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/TejasMedade)

[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/TejasMedade)

## Authors

- [Shreyas Vilas Medade](https://github.com/medadeshreyas)

- [Tejas Vilas Medade](https://github.com/tejasmedade)

## Acknowledgements

- [Masai School](https://www.masaischool.com/)
