# core-api-utils

## Description
`core-api-utils` is a Java utility library designed to simplify API development by providing reusable components for validation, request generation, and error handling. It is built with modern Java features and integrates seamlessly with Spring Boot.

## Features
- **Validation Utilities**: Custom annotations for validating UUIDs, ISO dates, IBANs, emails, and more.
- **Request Generators**: Tools for building API metadata and standardized request/response structures.
- **Error Handling**: Centralized exception handling with customizable error responses.
- **JSON Configuration**: Pre-configured Jackson `ObjectMapper` for handling Java 8+ date/time types.

## Requirements
- Java 21 or higher
- Maven 3.8+
- Spring Boot 3.2.0

## Installation
Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.jtissdev</groupId>
    <artifactId>core-api-utils</artifactId>
    <version>1.1.0-SNAPSHOT</version>
</dependency>
```

## Usage
### Validation
Use custom annotations for validation:
```java
@ValidUUID
private String id;

@ValidISODate
private String date;

@FrenchPostalCode
private String postalCode;
```

### Request Generation
Generate API metadata:
```java
Map<String, Object> info = ApiInfoBuilder.buildInfo("MyService", "1.0.0");
System.out.println(info);
```

### Error Handling
Centralized exception handling:
```java
throw new ApiException("ERR001", "Invalid input data");
```

### JSON Configuration
Pre-configured `ObjectMapper`:
```java
@Autowired
private ObjectMapper objectMapper;
```

## Contributing
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Submit a pull request.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
