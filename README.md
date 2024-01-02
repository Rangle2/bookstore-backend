# Getting Started

## User Controller

This controller class manages fundamental operations related to users, including user creation, editing, deletion, searching, and login functionalities.

### Endpoints

- **Access User by ID**
  GET /api/user/userId/{userId}

  Retrieves a user with the specified user ID.

- **Access User by Username**
  GET /api/user/username/{username}

  Retrieves a user with the specified username.

- **Access User by Username and Email**
  GET /api/user/user/{username}/{email}

  Retrieves a user with the specified username and email. Returns HTTP 200 if the user is found, and HTTP 404 if not found.

- **Get Current User**
  GET /api/user/currentUser

  Retrieves the currently logged-in user.

- **User Login**
  POST /api/user/login

  Authenticates a user based on the provided credentials and returns an authentication token. Returns HTTP 202 if successful.

- **Create User**
  POST /api/user/create

  Creates a new user based on the provided user information. If the username and email already exist, it returns a conflict status (HTTP 409) with an appropriate message. Otherwise, it creates the user and returns HTTP 201.

- **Edit User**
  PUT /api/user/edit/{userId}

  Edits an existing user's information based on the provided changes and user ID.

- **Delete User by ID**
  DELETE /api/user/delete/userId/{userId}

  Deletes a user with the specified user ID.

- **Create Admin User**
  POST /api/user/4215621356

  Creates an admin user based on the provided admin user information.

## Book Controller

The BookController class manages operations related to books, providing endpoints for retrieving, creating, editing, and deleting book information.

### Endpoints

- **Get Book by ID**
  GET /api/books/get/{bookId}

  Retrieves book details with the specified book ID.

- **Get All Books**
  GET /api/books/get

  Retrieves a list of all books available.

- **Create Book**
  POST /api/books/create

  Creates a new book based on the provided book information.

- **Edit Book**
  PUT /api/books/edit/{bookId}

  Edits an existing book's information based on the provided changes and book ID.

- **Delete Book**
  DELETE /api/books/delete/{bookId}

  Deletes a book with the specified book ID.

## Category Controller

The CategoryController class manages operations related to product categories, providing endpoints for retrieving, creating, editing, and deleting category information.

### Endpoints

- **Get Category by ID**
  GET /api/category/{categoryId}

  Retrieves category details with the specified category ID.

- **Get All Categories**
  GET /api/category/get

  Retrieves a list of all categories available.

- **Create Category**
  POST /api/category/create/{productId}

  Creates a new category based on the provided category information and associates it with the specified product ID.

- **Edit Category**
  PUT /api/category/edit/{categoryId}/{productId}

  Edits an existing category's information based on the provided changes, category ID, and product ID.

- **Delete Category**
  DELETE /api/category/delete/{categoryId}/{productId}

  Deletes a category with the specified category ID associated with the provided product ID.

  Note: The {productId} path variable in the endpoints indicates the association of categories with a specific product.
  createCategory associates the newly created category with the provided product ID.
  editCategory and deleteCategory require both category ID and product ID for proper identification.
  These endpoints collectively facilitate the management of product categories within the application.

## Product Controller

The ProductController class manages operations related to products, providing endpoints for retrieving, creating, editing, and deleting product information.

### Endpoints

- **Get Product by ID**
  GET /api/products/get/{productId}

  Retrieves product details with the specified product ID.

- **Get All Products**
  GET /api/products/get

  Retrieves a list of all products available.

- **Create Product**
  POST /api/products/create

  Creates a new product based on the provided product information.

- **Edit Product**
  PUT /api/products/edit/{productId}

  Edits an existing product's information based on the provided changes and product ID.

- **Delete Product**
  DELETE /api/products/delete/{productId}

  Deletes a product with the specified product ID.

  Note: The {productId} path variable in the endpoints uniquely identifies each product.
  createProduct creates a new product based on the information provided in the request body.
  editProduct updates the existing product details based on the changes provided in the request body.
  deleteProductById removes a product with the specified product ID from the system.
  These endpoints collectively facilitate the management of products within the application.

# Contributing to Bookstore Project

Thank you for considering contributing to the Bookstore Project. As this project is primarily for personal development, we currently do not accept external contributions.

If you have any feedback, suggestions, or find issues in the project, feel free to open an issue on GitHub. Your input is valuable and appreciated.

Happy coding!

# License
#### This project is licensed under the MIT License.

## Database Settings

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/yourDatabaseName
spring.datasource.username=yourUserName
spring.datasource.password=yourPass
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

