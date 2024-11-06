TaskManager
TaskManager is a simple Java-based task management application built with Spring Boot. The application provides a REST API for managing tasks and a console interface for users to interact with the task management system.

Features
Create, view, update, and delete tasks through a console application.
RESTful API endpoints to interact with task data.
Dockerized setup using Docker Compose for easy deployment.
In-memory H2 database for lightweight data storage.
Technologies Used
Java(JDK 17) (Spring Boot)
REST API with Spring Web
Spring Data JPA
H2 In-memory Database
Docker and Docker Compose
Project Structure
TaskManagerApplication: The main Spring Boot application class.
TaskManagerConsole: Console application that interacts with the REST API.
TaskController: REST controller to handle API requests.
TaskRepository: JPA repository for managing task persistence.
Getting Started
Prerequisites
Docker and Docker Compose installed.
Clone the Repository
bash
Copy code
git clone https://github.com/yourusername/TaskManager.git
cd TaskManager
Build the Application
To build the application and create the necessary JAR files, run:

bash
Copy code
./gradlew clean build
Docker Setup
The project includes two Dockerfiles and a Docker Compose configuration to set up the server and console components.

Docker Compose
To build and run the application with Docker Compose, use:

bash
Copy code
docker-compose build
docker-compose up -d
This will:

Start the Spring Boot server on localhost:8080.
Start the console application in a separate container.
To interact with the console, attach to the task-manager-console container:

bash
Copy code
docker-compose run task-manager-console
API Endpoints
Endpoint	HTTP Method	Description
/tasks	GET	Retrieve all tasks
/tasks	POST	Create a new task
/tasks/{id}	PUT	Update a specific task
/tasks/{id}	DELETE	Delete a specific task
Usage
Start the application by running docker-compose up.

Attach to the console container:

bash
Copy code
docker-compose run task-manager-console
In the console, you will see a menu with options to:

View all tasks
Create a new task
Update a task
Delete a task
Exit the console
Select an option by entering a number (1-5) to perform the desired action.

Example Console Output
sql
Copy code
Task Manager Console
1. View All Tasks
2. Create a New Task
3. Update a Task
4. Delete a Task
5. Exit
Choose an option: 
Database Configuration
The application uses an H2 in-memory database, which is configured in the application.properties file. This is suitable for development and testing purposes, as data will be cleared upon each application restart.

Troubleshooting
Console Not Responding: Ensure the server container is up and running. 
Database Persistence: The current setup uses an in-memory database. For persistent data, you can configure a different database (e.g., MySQL) in application.properties.
