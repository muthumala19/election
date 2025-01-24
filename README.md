# How to Run the VoteZone Application

Follow these steps to build and run the Election application with Docker Compose.

## Prerequisites
Make sure the following are installed on your system:
- **Java 17**
- **Maven** (latest version recommended)
- **Docker** and **Docker Compose**

---

## Steps to Run the Application

### 1. Navigate to the Project Directory
Open a terminal and navigate to the project directory containing the `pom.xml` and `docker-compose.yml` files:
```
cd /path/to/election-directory
```

### 2.  Compile the Application
Run the following Maven command to compile the source code:
```
mvn compile
```
 ### 3. Build the application docker image
Run the following command to build the application docker image named as `votezone`:
```
docker build -t votezone .
```



### 4.  Start the Application
Run the following command to start the application and its dependencies using Docker Compose:
```
docker-compose up --build
```

## What Happens Next?
- The **VoteZone** application will be accessible at [http://localhost:8088/votezone](http://localhost:8088/votezone).
- The **main database** (PostgreSQL) will be running on port `5432`.
- The **Quartz database** (PostgreSQL) will be running on port `5433`.

### Docker Services Overview
The following services will be started by Docker Compose:
1. `votezone` - The main application container.
2. `votezone_primary_database` - The main PostgreSQL database for the application.
3. `votezone_quartz_database` - The PostgreSQL database for Quartz job scheduling.

---

## Logs and Monitoring
- Application logs are stored in the `votezone_logs` volume.
- To view logs in real-time, use the following command:
  ```
  docker-compose logs -f
  ```
## Stopping the Application
- To stop and remove all containers, use the following command:
    ```
    docker-compose down
    ```

