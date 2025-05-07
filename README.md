# JiIaMD

## Overview
Jiiamd is a project developed as part of a series of laboratory exercises during my 5th semester in the course "Java in Internet and Mobile Devices." The project explores the implementation of the Four-Square Cipher, starting with a desktop application and later transitioning to a web-based application. The project includes comprehensive testing and adheres to standard Java development practices.

## Features
- **Four-Square Cipher Implementation**: Encode and decode text using the Four-Square Cipher algorithm.
- **Desktop Application**: Initial implementation of the cipher in a standalone desktop application.
- **Web Application**: Extended functionality with a Spring-based web application, including RESTful APIs and a user interface.
- **History Management**: Track encoding and decoding operations with user-specific history records.
- **Validation**: Input validation to ensure only Latin characters are processed.
- **Testing**: Comprehensive unit and integration tests to ensure reliability.

## Technologies Used
- **Java**: Core programming language.
- **Spring Framework**: For building the web application.
- **Maven**: Dependency management and build automation.
- **Thymeleaf**: For rendering views in the web application.
- **JUnit**: For testing.
- **H2 Database**: In-memory database for storing history records.
- **HTML/CSS**: For the web application's front-end.

## Project Structure
- **Desktop Application**: Initial implementation of the cipher logic.
- **Web Application**: RESTful API and web interface for encoding, decoding, and managing history.
- **Tests**: Unit and integration tests for both the desktop and web applications.

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/yiro0/jiiamd.git
   ```
2. Navigate to the project directory:
   ```bash
   cd jiiamd
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the web application:
   ```bash
   mvn spring-boot:run
   ```
5. Ensure the server is running, or else just access the application in your browser at localhost port `8080`.

## Endpoints
### REST API
- **POST /cipher/encode**: Encode text using the Four-Square Cipher.
- **POST /cipher/decode**: Decode text using the Four-Square Cipher.
- **GET /cipher/history**: Retrieve the history of encoding and decoding operations.

### Web Interface
- **/addHistory**: Add a new history record.
- **/history**: View all history records.

## Testing
Run the tests using Maven:
```bash
mvn test
```