# Employee Management API

This is a Spring Boot project that provides an API for managing employees. The API allows you to perform the following operations:

- Add an employee to the database.
- Get a list of employees with pagination and sorting options.
- Delete an employee by their ID.
- Update the details of an employee by their ID.
- Get the Nth level manager of an employee.

## Technologies Used

- Spring Boot: To develop the RESTful API.
- CouchDB Server: As the NoSQL database for storing employee information.
- Eclipse IDE: For development and testing.
- Postman: To test the API endpoints.

###  Couch DataBase Server setup
- After successful installation, set the username as mycouch,password as couch@16
- crate bucket and name it as newbucket

To ensure that Request and Response for all APIs to be in JSON (Go to Postman->Headers->set these as key value pairs (Content-Type:application/json),(Accept:application/json))

#### How to Test the API

To test the API using Postman:
1. Open Postman and import the provided collection of API endpoints.
2. Use the different endpoints and HTTP methods (POST, GET, DELETE, PUT) to test each functionality with various input data.
3. Inspect the response to ensure the API is working as expected.


1. **Add an Employee:**

   - Endpoint: `POST /employees`  (http://localhost:8080/employees)
   - Request Body: Provide the employee details in the request body in JSON format.(Body->raw->give data in json format)
      eg:{
    
    "employeeName":" govindraj",
    "phoneNumber":"995677691",
    "email":"govindraj@gmail.com",
    "reportsTo":"50c1edfa-959e-4d33-b58e-742cd69ae0b",
    "emp_ProfileImg":"https://unsplash.com/s/photos/female-employee"
       }
   - Response: The API will add the employee to the database and return their unique ID (`e_ID`).

2. **Get All Employees with Pagination and Sorting:**

   - Endpoint: `GET /getemployees` ( http://localhost:8080/getemployees?page=0&pageSize=10&sortBy=employeeName)
   - Query Parameters:
     - `page`: The page number (default is 0).(However,you can change in the uri u r sending from postman)
     - `pageSize`: The number of employees per page (default is 10).(However,you can change in the uri u r sending from postman)
     - `sortBy`: The field to sort the employees by (default is `employeeName`).(However,you can change in the uri u r sending from postman)
   - Response: The API will return a list of employees based on the specified pagination and sorting criteria.(However,you can change in the uri u r sending from postman)

3. **Delete an Employee:**

   - Endpoint: `DELETE /employees/{id}`  (http://localhost:8080/employees/{e_ID})  here replace e_ID with the id of the employee you wanted to delete
   - Path Parameter:
     - `id`: The unique ID of the employee to delete.
   - Response: The API will delete the employee with the specified ID from the database.

4. **Update an Employee:**

   - Endpoint: `PUT /employees/{id}`   (http://localhost:8080/employees/{e_ID})  here replace e_ID with the id of the employee you wanted to update
   - Path Parameter:
     - `id`: The unique ID of the employee to update.
   - Request Body: Provide the updated employee details in the request body in JSON format.
   - Response: The API will update the employee's details in the database.

5. **Get Nth Level Manager of an Employee:**

   - Endpoint: `GET /employees/{employeeId}/{level}`
   - Path Parameters:
     - `employeeId`: The unique ID of the employee.
     - `level`: The level of the manager to retrieve (e.g., 1 for direct manager, 2 for their manager's manager, and so on).
   - Response: The API will return the Nth level manager of the employee.

**Note:** Before testing the API, ensure that your CouchDB server is running, and the connection details in your application properties are correct.



