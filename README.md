# Spring POS System

## Overview
The Spring POS (Point of Sale) System is a robust application designed to streamline sales transactions, manage inventory, and generate insightful reports for retail businesses. Built with the Spring framework, this system is efficient and scalable, making it suitable for various retail environments.

## Features
- **Product Management**: Create, read, update, and delete product information.
- **Sales Management**: Process sales transactions and handle different payment methods.
- **Inventory Tracking**: Monitor stock levels and receive notifications for low stock.
- **Reporting**: Generate detailed reports on sales, inventory, and user activities.
- **RESTful API**: Expose endpoints for integration with frontend applications or third-party services.

## Technology Stack
- **Backend**: Spring
- **Database**: MySQL 
- **Build Tool**: Maven
- **Testing**: Postman

## Requirements
- JDK 17 
- Maven
- MySQL 

## Getting Started

### Steps
1. **Clone the repository - Backend**
    ```bash
    https://github.com/sachindusandaruwan/Spring_POS_System_API.git
    
2. **Configure the database**
    - Create a MySQL database named `pos_spring`
    - Update the `application.properties` file with your MySQL credentials
   
    ```properties
    
    var dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost:3306/pos_spring");
        dmds.setUsername("root");
        dmds.setPassword("password");
        return dmds;
    ```

3. **Build the project**
    ```bash
    mvn clean install
    ```

4. **Deploy to Tomcat**
    - Ensure Tomcat is installed and running.
    - Copy the generated WAR file from the `target` directory to the Tomcat `webapps` directory.
    - Restart Tomcat.

## Usage
### Running the Server
After installation, run the server by starting Tomcat. The API will be available at `http://localhost:8080/`.

## Project Structure

### Back-end

The back-end code is organized to follow best practices and maintainability. Important directories and files include:

- `src/main/java`: Contains Java classes.
- `src/main/resources/schema`: Database schema.
- `src\main\resources\logback.xml`: Logger configuration.
- `src\main\webapp\WEB-INF`: Configuration files for the JavaEE application.

#### Project Packages

(src\main\java\lk\ijse\gdse68\aad\spring_pos_system_api)

- **config**: Contains configuration classes, such as security filters and logging filters, responsible for setting up the security mechanisms (e.g., JWT filters) and logging functionality across the application.
- **controller**: Contains classes defining the API endpoints and services.
- **sevice**: Business Objects - classes that encapsulate business logic.
- **dao**: Data Access Objects - classes responsible for database interactions.
- **entity**: Entity classes representing database tables.
- **dto**: Data Transfer Objects - classes used for data exchange between layers.
- **exception**: Contains custom exception classes that handle different types of errors throughout the application..
- **customObject**: Contains custom object classes, which are utility classes or specific objects that don’t necessarily fit into other categories. .
- **util**: Utility classes that provide common functionalities or helper methods used across the application..


## Getting Started

To set up and run the POS system locally, follow these steps:

1. Clone the repository.  
2. Set up the back-end dependencies.  
3. Configure the database connection.  
4. Deploy the JavaEE application on the Apache Tomcat server.

## Dependencies

#### Back-end

- **Spring Boot 3.3.3**: Used for building and deploying the POS system as a RESTful API.
- **Spring Data JPA 3.3.3**: Provides JPA support for database interaction.
- **Hibernate 6.6.0**: Object-relational mapping tool to manage database entities.
- **ModelMapper 3.2.1**: Framework for mapping between object models.
- **Spring WebMVC**: Handles the HTTP requests and responses.

#### Database

- **MySQL Connector**: Java-based driver for connecting to MySQL databases. (Version 8.0.33)
- **JNDI**: Java API for managing database connections efficiently through connection pooling, enhancing performance and scalability.

#### Development Tools

- **Maven 4.0.0**: Build automation and project management tool.
- **Logback**: Logging framework for logging application events.

### Accessing the API
The API will be available at `http://localhost:8080/`.

## API Documentation
For detailed API documentation, please refer to the project’s Swagger UI and Postman collections.

- **API documentation URL**: [Postman API Documentation](https://documenter.getpostman.com/view/35384520/2sAXxV4pTV)

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

<div align="center">

#### This project is licensed under the [MIT License](LICENSE)

#### © 2024 All Right Reserved, Designed By [Sachindu Sandaruwan](https://github.com/sachindusandaruwan)

</div>
