# ATM Simulator Web App
This is a web application that simulates an ATM, allowing users to perform transactional actions such as withdraw and transfer. It also enables users to create a bank account using AccountManagement. The following features are available :

- Only eligible users are allowed to perform transactional actions using the ATM.
- The user must enter the correct card number and password to access transactional features.
- Withdrawals are only allowed if the user's balance is sufficient.
- Transfers can only be made to one of the eligible accounts.
- Only the admin can verify if a user is eligible or not.
- After a user submission, a message is sent to the admin to make the user eligible.

## Technologies Used
This application was developed using the following technologies:

- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Spring Security
- JWT authentication
- Java Mail

### Getting Started
To run the application, follow these steps:

- Clone the repository.
- Configure the MySQL database settings in the application.properties file.
- Run the application using the following command :
_mvn spring-boot:run_
- Open a web browser and navigate to http://localhost:8080/.

### Usage
Once the application is running, users can create a bank account using the AccountManagement feature. To access transactional features, users must enter their card number and password. Withdrawals are only allowed if the user's balance is sufficient, and transfers can only be made to eligible accounts.

Only the admin can verify if a user is eligible or not. After a user submission, a message is sent to the admin to make the user eligible.

### Conclusion
The ATM Simulator Web App is a convenient way for users to perform transactional actions and manage their bank accounts. With its robust security features, it provides a seamless experience for users. 

🌟Some of its implementations are still on way, so look forward to it!
