Order Management System
This Java application provides a user-friendly interface for managing orders, clients, and items in a store. The system follows a layered architecture and utilizes reflection for dynamic functionality.

Functionality:
Order Management: Create, update, and delete orders with customer information, order items, quantities, and prices.
Client Management: Add and manage client details such as name, contact information, and address.
Item Management: Add and manage item records including name, description, and price.
Architecture:
The application follows a layered architecture, separating concerns into distinct layers:

Presentation Layer: Provides a user interface for interacting with the system.
Business Layer: Implements business logic and manages data operations.
Data Access Layer: Handles database connectivity and CRUD operations.
Reflection:
Reflection is used to dynamically create SQL queries and handle results, enhancing flexibility and adaptability.