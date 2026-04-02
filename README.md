# second-round-assignm-final-17958-chetan
Final Project Assignment - This repository contains the complete final project code and documentation.

# E-commerce Backend System

## Overview

This project implements the backend for an e-commerce platform using **Spring Boot**, **Spring Security**, **JWT authentication**, and **Stripe payment integration**.

The system allows users to register, browse products, manage carts, place orders, and process payments.

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Security + JWT
* Spring Data JPA
* MySQL
* Stripe Payment Gateway
* Maven

---

## Features

### Authentication

* User registration
* User login
* JWT-based authentication

### Product Management

* Create product
* View all products
* Update product
* Delete product

### Cart Management

* Add product to cart
* View cart
* Remove product from cart

### Order Management

* Create order from cart
* View user orders

### Payment Integration

* Stripe checkout session
* Payment success / cancel handling

---

## Project Structure

```
controller
service
repository
entity
dto
security
config
exception
```

---

## Running the Project

1. Clone the repository

```
git clone 
```

2. Create a configuration file

Copy:

```
application-example.properties
```

to

```
application.properties
```

3. Update database and API keys.

4. Run the application

```
mvn spring-boot:run
```

---

## API Documentation

After running the project:

```
http://localhost:8080/swagger-ui.html
```

---

## Test Payment Card (Stripe)

```
Card Number: 4242 4242 4242 4242
Expiry: Any future date
CVC: Any 3 digits
```
