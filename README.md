# Digital Library Project


**Digital Library: A Server/Client Java Application**

**Project Description:**

The Digital Library project is a comprehensive application developed using Java, following a Server/Client architecture. This project aims to leverage algorithmic modules to enhance efficiency and demonstrate key programming principles and design patterns learned throughout the course. The application includes various features such as optimization algorithms, business logic services, data management, and a graphical user interface.

**Key Components:**

1. **Algorithm Module**:
   - Implements a family of optimization algorithms, packaged separately as `AlgorithmModule.jar`.
   - Includes at least two algorithms to improve the efficiency of the application.
   - Provides unit tests to ensure stability and coverage.

2. **Service Design and Implementation**:
   - Adheres to Object-Oriented Programming principles and design patterns.
   - Implements `LibraryServices`, responsible for business logic, accessing data repositories, and integrating algorithm components.
   - Supports actions like save, delete, and retrieve for `DataModels`.
   - Uses interfaces like `IDao` for data access and `IAlgoStringMatching` for algorithmic operations, ensuring flexibility and adherence to the open/closed principle.

3. **Data Management**:
   - Implements `IDao` interface for data operations.
   - `DaoFileImpl` class manages reading and writing data to a local file (`txt.DataSource`).
   - Ensures efficient data streaming and serialization/deserialization.

4. **Server/Client Communication**:
   - Implements a server (`Server` class) using `ServerSocket` to listen for client requests.
   - Handles client requests through `HandleRequest` class, processing JSON formatted requests using GSON.
   - Utilizes Factory Pattern to route requests to appropriate controllers.
   - Controllers expose application APIs for operations like save, delete, and retrieve.

5. **Graphical User Interface (GUI)**:
   - Developed as a separate project using JavaFX.
   - Implements an MVC model to ensure a loosely coupled, flexible, and maintainable architecture.
   - Client-side components communicate with the server using sockets to perform application actions.
   - Provides a user-friendly interface reflecting all server-side functionalities.

**Project Structure**:

1. **Part A - Algorithms Module**:
   - Define optimization needs and implement a family of algorithms.
   - Package as `AlgorithmModule.jar` and include in the main project.

2. **Part B - Service Design**:
   - Plan system architecture and design using OOP principles and design patterns.
   - Implement `LibraryServices` and `IDao` for data operations.
   - Integrate algorithm components and ensure flexibility for future enhancements.

3. **Part C - Server/Client Communication**:
   - Develop server-side components (`Server`, `HandleRequest`, Controllers) for request handling.
   - Implement JSON-based communication and ensure seamless interaction between client and server.

4. **Part D - User Interface**:
   - Build the GUI using JavaFX.
   - Implement client-side components to communicate with the server.
   - Ensure a responsive and user-friendly interface for the application.

The Digital Library project showcases the integration of multiple Java programming concepts and design patterns, providing a robust and efficient server/client application with a rich graphical user interface.
