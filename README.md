# Blogging Application

![Blogging Application](https://user-images.githubusercontent.com/105907169/209240082-4c5793e1-e72f-4778-bb9f-194bbdc4d3a1.jpg)


 ## **WEB SERVICE : REST A.P.I.**
 
The Blogging Application's REST API is a web-based platform that empowers bloggers and users to share their ideas and perspectives in an interactive way. The API allows users to publish blogs on their personal profiles, complete with image support, which enhances the user experience. The API also enables users to view and search for blogs and blog categories that align with their personal interests, fostering a community-driven platform. Users can also leave comments on published posts, encouraging engagement. 

The API uses standard HTTP methods to retrieve, create, update, and delete data, providing a secure and robust way for the Blogging Application to interact with the data, making it easy and safe for users to share their thoughts and ideas. This REST API is an ideal solution for anyone who wants to connect with others and share their ideas through blogging.

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



## User Functionalities

- **Authentication Management**

  - Endpoint for Sign Up
  - Endpoint for Sign In
  - Endpoint for Sign Out

- **Profile Management**

  - Endpoint for Updating User Information
  - Endpoint for Retrieving User Information
  - Endpoint for Deleting User Account

- **Post Management**

  - Endpoint for Creating Posts
  - Endpoint for Updating Posts
  - Endpoint for Retrieving Posts
  - Endpoint for Deleting Posts
  - Endpoint for Adding Comments to Posts
  - Endpoint for Updating Post Images
  - Endpoint for Retrieving Posts with Custom Pagination

- **Category Management**

  - Endpoint for Creating Categories
  - Endpoint for Updating Categories
  - Endpoint for Retrieving Categories
  - Endpoint for Deleting Categories

- **Searching & Sorting Posts**

  - Endpoint for Searching Posts by :
    - Title
    - Date
    - Category
  - Endpoint for Sorting Posts by :
    - Date
    - Popularity

- **User Search**

  - Endpoint for Searching Users by :
    - Name
    
## Administrator Functionalities    
  
- **Authentication Management**

  - Endpoint for Sign Up Other Admin Accounts
  - Endpoint for Sign In
  - Endpoint for Sign Out


- **Profile Management**
  - Endpoint for Deleting Admin Accounts

- **Post Management**
  - Endpoint for Retrieving Posts
  - Endpoint for Deleting Posts

- **Category Management**
  - Endpoint for Retrieving Categories
  - Endpoint for Deleting Categories

- **Comment Management**
  - Endpoint for Retrieving Comments
  - Endpoint for Deleting Comments



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
At present, Swagger does not support cookie-based authorization. The team will look into implementing it once Swagger provides the necessary features to support it.
```

## Important Note


- At present, Swagger does not support cookie-based authorization. The team will look into implementing it once Swagger provides the necessary features to support it.


- When utilizing the PostMan software, ensure that the request includes embedded cookies as JWT authentication is implemented as a cookie-based authentication mechanism.


- The roles have already been established within the database. Ensure that the appropriate requests are executed for both the Admin and User roles.


- It's important to note that an Admin is also considered as a User. Only Users with Admin privileges have the ability to create additional Admins within the database.


- The expiration time limit for JWT tokens is 20 minutes. Subsequently, a new login session is required after the 20-minute duration.


 
## ER Diagram

 ![ER_Diagram](https://user-images.githubusercontent.com/105907169/209962800-caffd493-7f0a-45ba-a421-c6f912bee76d.jpg)



## Base Url
```bash
http://localhost:8088
```

## PostMan Documentation

Check Out the Below Given Link For Documentation with all API Requests, Responses, Headers & Request Body. 

**POSTMAN DOCUMENTATION** : https://documenter.getpostman.com/view/24342917/2s8ZDbVL15

## API References

## Authorization & Authentication API Reference 

![Screenshot 2022-12-22 at 04-53-01 Swagger UI](https://user-images.githubusercontent.com/105907169/209022327-180a8d24-6bf8-45e0-90e9-9cda080769ca.png)

## User API Reference

![Screenshot 2022-12-22 at 04-54-17 Swagger UI](https://user-images.githubusercontent.com/105907169/209022330-974606a5-8339-4a9b-ba5c-c74e5a9408cc.png)

## Post API Reference

![Screenshot 2022-12-22 at 04-54-05 Swagger UI](https://user-images.githubusercontent.com/105907169/209022334-735db752-6148-4a44-99da-5d0bb4b5a5fe.png)


## Category API Reference

![Screenshot 2022-12-22 at 04-53-15 Swagger UI](https://user-images.githubusercontent.com/105907169/209022324-da219775-d39b-4d60-ac38-345ea3604656.png)


## Comment API Reference

![Screenshot 2022-12-22 at 04-53-53 Swagger UI](https://user-images.githubusercontent.com/105907169/209022336-7cac4c01-e9e4-4baa-9d01-cbb984797852.png)


## Contributions

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **Greatly Appreciated**.

If you have any ideas on how to improve this resume, please feel free to fork the repository and submit a pull request. Your contributions, no matter how big or small, are greatly appreciated and will help to make this repository even better.

In addition to contributing to the repository, you can also connect with me for further development and collaboration on this API. Together, we can continue to improve and evolve the API to meet the needs of the community.

We encourage you to give the repository a star and we thank you for your interest in this project. 

Your support is greatly appreciated.

## 🔗 Contact Me

[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://tejasmedade.github.io/)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/TejasMedade)

[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/TejasMedade)

## Authors

- [Shreyas Vilas Medade](https://github.com/medadeshreyas)

- [Tejas Vilas Medade](https://github.com/tejasmedade)

## Acknowledgements

- [Masai School](https://www.masaischool.com/)
