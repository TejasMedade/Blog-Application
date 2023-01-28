# Blogging Application

![Blogging Application](https://user-images.githubusercontent.com/105907169/209240082-4c5793e1-e72f-4778-bb9f-194bbdc4d3a1.jpg)


 ## **REST API SERVICE**
 
The Blogging Application's REST API is a web-based platform that empowers bloggers and users to share their ideas and perspectives in an interactive way. The API allows users to publish blogs on their personal profiles, complete with image support, which enhances the user experience. The API also enables users to view and search for blogs and blog categories that align with their personal interests, fostering a community-driven platform. Users can also leave comments on published posts, encouraging engagement. 

The API uses standard HTTP methods to retrieve, create, update, and delete data, providing a secure and robust way for the Blogging Application to interact with the data, making it easy and safe for users to share their thoughts and ideas. This REST API is an ideal solution for anyone who wants to connect with others and share their ideas through blogging.

The application will be used by the **two** categories of users:

- **Administrator**

- **Blogger / User** 

## Features 

 - The API implements Spring Security and JSON Web Token (J.W.T) for authentication, validation, and authorization of users and administrators.
 - The API includes functionality for pagination, sorting, and searching of data.
 - The API implements custom exception handling for all exceptions and validations.
 - The API utilizes custom request and response data transfer objects for all HTTP requests.
 - The API's primary objective is to provide a streamlined and user-friendly blogging experience for users.
 - Built on REST Architecture
 - Consumable by clients that support HTTP Protocol
 - Can be integrated with any application that supports REST API
 - Suitable for a wide range of use cases.

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

 ![ER_Diagram](https://user-images.githubusercontent.com/105907169/209962800-caffd493-7f0a-45ba-a421-c6f912bee76d.jpg)



## BASE URL
```bash
http://localhost:8088
```
## API REFERENCES

Check Out the Below Given Link For Documntation Which Supports All Details with API Requests, Responses, Headers & Request Body. 

```bash
https://documenter.getpostman.com/view/24342917/2s8ZDbVL15
```


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
