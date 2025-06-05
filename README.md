# core-api-utils

## Description

`core-api-utils` is a Java utility library designed to simplify API development by providing reusable components for
validation, request generation, and error handling. It is built with modern Java features and integrates seamlessly with
Spring Boot.

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
    - [Validation](#validation)
    - [Request Generation](#request-generation)
    - [Error Handling](#error-handling)
    - [JSON Configuration](#json-configuration)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)
- [Contact](#contact)

## Features

- **Validation Utilities**: Custom annotations for validating UUIDs, ISO dates, IBANs, emails, and more.
- **Request Generators**: Tools for building API metadata and standardized request/response structures.
- **Error Handling**: Centralized exception handling with customizable error responses.
- **JSON Configuration**: Pre-configured Jackson `ObjectMapper` for handling Java 8+ date/time types.
- **Spring Boot Integration**: Easy integration with Spring Boot applications.
- **Documentation**: Comprehensive Javadoc and examples for quick reference.
- **Maven Support**: Easily add to your Maven projects with a simple dependency.
- **Java 21+ Compatibility**: Utilizes features from Java 21, ensuring modern development practices.
- **Unit Tests**: Includes unit tests for all components to ensure reliability and correctness.
- **OpenAPI Support**: Integration with OpenAPI for API documentation generation.
- **Custom Annotations**: Annotations for validating French postal codes and other specific formats.
- **API Metadata Generation**: Tools for generating API metadata, including service name and version.
- **Error Codes**: Custom error codes for consistent error handling across APIs.
- **ObjectMapper Configuration**: Pre-configured `ObjectMapper` for JSON serialization/deserialization.
- **Centralized Exception Handling**: A base exception class for API errors, allowing for consistent error responses.
- **API Documentation**: Automatically generated API documentation using OpenAPI annotations.
- **Dependency Management**: Uses Maven for dependency management, ensuring easy integration and updates.
-

## Requirements

- Java 21 or higher
- Maven 3.8+
- Spring Boot 3.2.0
- Jackson 2.15.0

## Installation

Add the following dependency to your `pom.xml`:

```xml

<dependency>
    <groupId>com.jtissdev</groupId>
    <artifactId>core-api-utils</artifactId>
    <version>1.3.0</version>
</dependency>
```

## Usage

### 🔧 Maven Configuration

Ensure you have the following in your `pom.xml` to enable snapshot dependencies:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>buildnumber-maven-plugin</artifactId>
      <version>1.4</version>
      <executions>
        <execution>
          <phase>validate</phase>
          <goals>
            <goal>create</goal>
          </goals>
        </execution>
      </executions>
      <configuration>
        <doCheck>false</doCheck>
        <doUpdate>false</doUpdate>
        <format>${project.groupId}\\n${project.artifactId}\\n${project.version}\\n${project.description}\\n${project.developers}</format>
        <timestampFormat>yyyy-MM-dd'T'HH:mm:ssZ</timestampFormat>
      </configuration>
    </plugin>
  </plugins>
</build>

```
💡 Assurez-vous que les champs description et developers sont bien définis dans votre pom.xml.

### request Generation

📌 Générer une réponse info API automatiquement

```java
@GetMapping("/info")
public ResponseEntity<Object> getInfo() {
    return ResponseEntity.ok(ApiInfoBuilder.fullInfo(
        "My API", "1.0.0", "API publique de mon projet",
        "contact@monapi.com", "owner@entreprise.com"
    ));
}
```

🔄 Ou récupérer dynamiquement depuis le POM (si config META-INF/maven faite)
```java
@GetMapping("/info")
public ResponseEntity<Object> getPomInfo() {
    return ResponseEntity.ok(ApiInfoBuilder.fromPomInfo());
}
```

🧭 Générer une aide standard pour une route
```java
@GetMapping("/help")
public ResponseEntity<Object> getHelp() {
    return ResponseEntity.ok(
        ApiHelpStructure.basicGet("/help", "Récupère l'aide de toutes les routes.")
    )
}
```

### 🔍 Pom Information Reader
```java
import com.jtissdev.core_api_utils.requestgen.PomInfoReader;

@GetMapping("/meta")
public ResponseEntity<Object> getMeta() {
    return ResponseEntity.ok(PomInfoReader.readPomInfo());
}
```
Réponse Java Attendue :
```json
{
    "groupId": "com.jtissdev",
    "artifactId": "core-api-utils",
    "version": "1.1.0-SNAPSHOT",
    "description": "Core API Utilities for Java",
    "developers": [
        {
            "name": "Julien Tissier",
            "email": "jtissdev@gmail.com"
        }
    ]
    }
```


### Validation



## Contributing

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
