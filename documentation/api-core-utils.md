# Guide d'intégration de la bibliothèque `core-api-utils`

Ce document présente les fonctionnalités principales de la bibliothèque `core-api-utils` et comment les intégrer efficacement dans vos projets Java.

## Table des matières

- [Présentation](#présentation)
- [Installation](#installation)
  - [Prérequis](#prérequis)
  - [Configuration Maven](#configuration-maven)
- [Fonctionnalités disponibles](#fonctionnalités-disponibles)
  - [Validations métier](#validations-métier)
  - [Génération de documentation API](#génération-de-documentation-api)
  - [Gestion des erreurs](#gestion-des-erreurs)
  - [Configuration JSON](#configuration-json)
- [Documentation des méthodes](#documentation-des-méthodes)
  - [ApiUtils](#apiutils)
  - [ApiInfoBuilder](#apiinfobuilder)
  - [ApiHelpStructure](#apihelpstructure)
  - [PomInfoReader](#pominforeader)
  - [Validateurs](#validateurs)
- [Exemples d'intégration](#exemples-dintégration)
  - [Validation des données](#validation-des-données)
  - [Documentation automatique](#documentation-automatique)
  - [Génération d'informations API](#génération-dinformations-api)

## Présentation

La bibliothèque `core-api-utils` est conçue pour simplifier le développement d'API Java en fournissant des composants réutilisables pour la validation des données, la génération de documentation API et la standardisation des réponses. 

Développée en Java 21, elle s'intègre parfaitement avec Spring Boot 3.x et Jakarta EE, en proposant des utilitaires qui permettent de réduire le code répétitif et d'améliorer la cohérence des API.

## Installation

Ajoutez la dépendance suivante à votre fichier `pom.xml` :

```xml
<dependency>
    <groupId>com.jtissdev</groupId>
    <artifactId>core-api-utils</artifactId>
    <version>1.3.0</version>
</dependency>
```

### Prérequis

- Java 21 ou supérieur
- Maven 3.8+
- Spring Boot 3.2.0
- Jackson 2.15.0

Initialisez la bibliothèque au démarrage de votre application :

```java
import com.jtissdev.core_api_utils.ApiUtils;

// Dans votre classe principale ou un bean de configuration
ApiUtils.init();
```

### 🔧 Configuration Maven

Assurez-vous que votre `pom.xml` contient les informations de base nécessaires pour l'exploitation des méta-données par la bibliothèque :

```xml
<project>
    <!-- Informations de base -->
    <groupId>com.votredomaine</groupId>
    <artifactId>votre-projet</artifactId>
    <version>1.0.0</version>
    <name>Nom de votre projet</name>
    <description>Description de votre projet</description>

    <!-- Information sur les développeurs -->
    <developers>
        <developer>
            <id>votre-id</id>
            <name>Votre Nom</name>
            <email>votre.email@domaine.com</email>
            <roles>
                <role>developer</role>
            </roles>
        </developer>
    </developers>

    <!-- Configuration du SCM -->
    <scm>
        <connection>scm:git:https://github.com/jtissdev/core-api-utils.git</connection>
        <developerConnection>scm:git:https://github.com/jtissdev/core-api-utils.git</developerConnection>
        <url>https://github.com/jtissdev/core-api-utils</url>
    </scm>
</project>
```

La bibliothèque utilise le plugin `buildnumber-maven-plugin` pour générer des informations sur la révision du code source et le moment de la compilation :

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>buildnumber-maven-plugin</artifactId>
    <version>3.2.0</version>
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
        <shortRevisionLength>8</shortRevisionLength>
        <revisionOnScmFailure>UNKNOWN</revisionOnScmFailure>
        <timestampFormat>{0,date,yyyy-MM-dd'T'HH:mm:ssZ}</timestampFormat>
    </configuration>
</plugin>
```

Cette configuration permet de générer des informations SCM (système de gestion de version) et un timestamp de build qui sont accessibles via la classe `MetadataReader`.
```

💡 Assurez-vous que les champs description et developers sont bien définis dans votre pom.xml.

## Fonctionnalités disponibles

### Validations métier

La bibliothèque fournit plusieurs validateurs personnalisés :

| Validateur | Description | Utilisation |
|------------|-------------|-------------|
| `ValidUUID` | Validation des identifiants UUID | Identifiants uniques de ressources |
| `FrenchPostalCode` | Validation des codes postaux français (5 chiffres) | Adresses postales |

### Génération de documentation API

Outils disponibles pour la génération automatique de documentation :

| Classe | Méthode | Description |
|--------|---------|-------------|
| `ApiInfoBuilder` | `buildInfo(...)` | Génère des informations de base sur l'API |
| `ApiInfoBuilder` | `fullInfo(...)` | Génère des informations complètes sur l'API |
| `ApiInfoBuilder` | `fullInfo(String, Map<String, Object>)` | Génère des informations complètes à partir des données du POM |
| `ApiHelpStructure` | `standardHeaders()` | Génère des en-têtes standards pour les requêtes d'aide |
| `ApiHelpStructure` | `standardResponseBody(...)` | Structure de base pour les réponses d'aide |
| `ApiHelpStructure` | `basicGet()`, `basicPost()`, etc. | Génère une documentation standard pour différentes méthodes HTTP |
| `MetadataReader` | `readMetadata()` | Lit les métadonnées du projet pour l'auto-documentation |

### Gestion des erreurs

Classes pour une gestion cohérente des erreurs :

| Classe | Fonction | Description |
|--------|----------|-------------|
| `ApiResponse<T>` | Encapsulation de réponses | Structure standardisée pour toutes les réponses API |
| `ApiException` | Exception personnalisée | Base pour toutes les exceptions métier |
| `GlobalExceptionHandler` | Gestionnaire central | Intercepte les exceptions et produit des réponses cohérentes |

### Configuration JSON

Utilitaires pour la manipulation JSON :

| Classe | Fonction | Description |
|--------|----------|-------------|
| `ObjectMapperConfig` | Configuration Jackson | Configuration prête à l'emploi pour la sérialisation/désérialisation JSON |

## Documentation des méthodes

Cette section détaille les principales classes et méthodes disponibles dans la bibliothèque `api-core-utils`.

### ApiUtils

Classe utilitaire principale fournissant des constantes et des méthodes d'initialisation.

```java
public class ApiUtils {
    // Version courante de la bibliothèque
    public static final String VERSION;

    // Initialise la bibliothèque et affiche la version
    public static void init();
}
```

### ApiResponse

Classe générique pour encapsuler toutes les réponses API de manière cohérente.

```java
public class ApiResponse<T> {
    // Constructeurs
    public ApiResponse(boolean success, String message, T data);
    public ApiResponse(boolean success, String message, T data, List<String> errors);

    // Getters
    public boolean isSuccess();
    public String getMessage();
    public T getData();
    public List<String> getErrors();

    // Méthodes utilitaires pour créer des réponses
    public static <T> ApiResponse<T> success(String message, T data);
    public static <T> ApiResponse<T> error(String message, List<String> errors);
}
```

### ApiInfoBuilder

Classe pour générer des informations sur l'API, utile pour les endpoints d'information.

```java
public class ApiInfoBuilder {
    // Construit des informations de base sur le service avec le nom et la version fournis
    public static Map<String, Object> buildInfo(String serviceName, String version);

    // Construit des informations de base sur le service en utilisant les informations du POM
    public static Map<String, Object> buildInfo(String serviceName, Map<String, Object> pomInfo);

    // Construit des informations complètes sur le service avec tous les détails fournis
    public static Map<String, Object> fullInfo(
        String serviceName, 
        String version, 
        String description, 
        String developerEmail, 
        String ownerEmail
    );

    // Construit des informations complètes sur le service en utilisant les informations du POM
    public static Map<String, Object> fullInfo(String serviceName, Map<String, Object> pomInfo);
}
```

#### Exemple d'utilisation

```java
@GetMapping("/info")
public ResponseEntity<Object> getInfo() {
    return ResponseEntity.ok(ApiInfoBuilder.fullInfo(
        "My API", "1.3.0", "API publique de mon projet",
        "contact@monapi.com", "owner@entreprise.com"
    ));
}
```

Ou en récupérant dynamiquement depuis le POM (si la configuration META-INF/maven est correcte) :

```java
@GetMapping("/info")
public ResponseEntity<Object> getPomInfo() {
    Map<String, Object> pomInfo = PomInfoReader.readPomInfo();
    return ResponseEntity.ok(ApiInfoBuilder.fullInfo("My API", pomInfo));
}
```
```

### ApiHelpStructure

Classe pour générer des structures d'aide standardisées pour les endpoints API.

```java
public class ApiHelpStructure {
    // Retourne les en-têtes standards
    public static Map<String, Object> standardHeaders();

    // Produit une réponse body standardisée
    public static Map<String, Object> standardResponseBody(
        String endpoint,
        String description,
        Map<String, Object> exampleRequest
    );

    // Structure une documentation pour différents types de requêtes
    public static Map<String, Object> basicGet(String endpoint, String description);
    public static Map<String, Object> basicPost(String endpoint, String description);
    public static Map<String, Object> basicPut(String endpoint, String description);
    public static Map<String, Object> basicDelete(String endpoint, String description);
}
```

#### Exemple d'utilisation

```java
@GetMapping("/help")
public ResponseEntity<Object> getHelp() {
    return ResponseEntity.ok(
        ApiHelpStructure.basicGet("/help", "Récupère l'aide de toutes les routes.")
    );
}
```

Réponse JSON attendue :
```json
{
    "endpoint": "/help",
    "description": "Récupère l'aide de toutes les routes.",
    "headers": {
        "X-Help-Request": true,
        "X-Request-Type": "HELP",
        "Accept": "application/json"
    },
    "request": {
        "method": "GET"
    },
    "response": {
        "status": "200 OK",
        "body": {
            "info": "Example response body"
        }
    }
}
```
```

### MetadataReader

Classe pour lire les informations du fichier POM de l'application via un fichier de propriétés généré.

```java
public class MetadataReader {
    // Lit les informations des métadonnées (groupId, artifactId, version, description, developer)
    public static Map<String, Object> readMetadata();
}
```

#### Exemple d'utilisation

```java
import com.jtissdev.core_api_utils.metadata.MetadataReader;

@GetMapping("/meta")
public ResponseEntity<Object> getMeta() {
    return ResponseEntity.ok(MetadataReader.readMetadata());
}
```

Réponse JSON attendue :
```json
{
    "groupId": "com.jtissdev",
    "artifactId": "core-api-utils",
    "version": "1.3.0",
    "buildNumber": "a1b2c3d4",
    "buildTimestamp": "2025-06-05T10:15:30Z",
    "scmBranch": "main",
    "implementationTitle": "Core API Utilities",
    "implementationVersion": "1.3.0",
    "implementationVendor": "jtissdev"
}
```
```

### Validateurs

La bibliothèque fournit plusieurs validateurs avec une interface commune héritée de `ConstraintValidator`.

#### FrenchPostalCodeValidator

```java
public class FrenchPostalCodeValidator implements ConstraintValidator<FrenchPostalCode, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.matches("\\d{5}"); // Valide que le code postal est composé exactement de 5 chiffres
    }
}
```

#### ValidUUIDValidator

```java
public class ValidUUIDValidator implements ConstraintValidator<ValidUUID, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true;
        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
```

#### Utilisation des validateurs

```java
public class UserDto {
    @ValidUUID
    private String id;

    @NotBlank
    private String name;

    @FrenchPostalCode
    private String postalCode;

    // Getters et setters
}
```

## Exemples d'intégration

### Validation des données

Intégration des validateurs dans un DTO :

```java
import com.jtissdev.core_api_utils.validation.ValidUUID;
import com.jtissdev.core_api_utils.validation.format.FrenchPostalCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class UserDto {
    @ValidUUID
    private String id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Email
    private String contactEmail;

    @FrenchPostalCode
    private String postalCode;

    // Getters et setters
}
```

Utilisation dans un contrôleur Spring :

```java
import com.jtissdev.core_api_utils.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distilleries")
public class DistilleryController {

    @PostMapping
    public ResponseEntity<ApiResponse<DistilleryDto>> createDistillery(
            @Valid @RequestBody DistilleryDto dto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, "Validation error", null,
                    bindingResult.getFieldErrors().stream()
                        .map(e -> e.getField() + ": " + e.getDefaultMessage())
                        .toList()));
        }

        // Logique métier pour créer la distillerie

        return ResponseEntity.ok(new ApiResponse<>(true, "Distillerie créée", dto));
    }
}
```

### Documentation automatique

Configuration d'un endpoint d'information API :

```java
import com.jtissdev.core_api_utils.requestgen.ApiInfoBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiDocController {

    @GetMapping("/info")
    public ResponseEntity<Object> getApiInfo() {
        return ResponseEntity.ok(ApiInfoBuilder.fullInfo(
            "Distillery API",
            "1.0.0",
            "API de gestion des distilleries",
            "contact@distillery-api.com",
            "tech@distillery-api.com"
        ));
    }

    @GetMapping("/help/distilleries")
    public ResponseEntity<Object> getDistilleriesHelp() {
        Map<String, Object> exampleRequest = Map.of(
            "name", "Distillerie des Highlands",
            "foundedYear", 1824,
            "region", "Scotland",
            "contactEmail", "contact@highlands.com"
        );

        return ResponseEntity.ok(
            ApiHelpStructure.standardResponseBody(
                "/api/distilleries",
                "Gestion des distilleries",
                exampleRequest
            )
        );
    }
}
```

### Réponse API standardisée

Exemple d'utilisation de la classe `ApiResponse` pour standardiser les réponses :

```java
import com.jtissdev.core_api_utils.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distilleries")
public class DistilleryController {

    @GetMapping
    public ResponseEntity<ApiResponse<List<DistilleryDto>>> getAllDistilleries() {
        List<DistilleryDto> distilleries = distilleryService.findAll();

        return ResponseEntity.ok(new ApiResponse<>(
            true,
            "Distilleries récupérées avec succès",
            distilleries
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DistilleryDto>> getDistilleryById(@PathVariable String id) {
        try {
            DistilleryDto distillery = distilleryService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Distillerie récupérée avec succès",
                distillery
            ));
        } catch (DistilleryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
```

### Gestion des exceptions

Création d'une exception métier personnalisée :

```java
import com.jtissdev.core_api_utils.exception.ApiException;

public class DistilleryNotFoundException extends ApiException {
    public DistilleryNotFoundException(String id) {
        super("DISTILLERY_NOT_FOUND", "La distillerie avec l'ID " + id + " n'a pas été trouvée");
    }
}
```

Configuration du gestionnaire global d'exceptions :

```java
import com.jtissdev.core_api_utils.dto.ApiResponse;
import com.jtissdev.core_api_utils.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DistilleryExceptionHandler {

    @ExceptionHandler(DistilleryNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleDistilleryNotFound(DistilleryNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>(
                false,
                ex.getMessage(),
                null,
                List.of(ex.getErrorCode())
            ));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse<>(
                false,
                ex.getMessage(),
                null,
                List.of(ex.getErrorCode())
            ));
    }
}
```

---

Ce guide couvre les principales fonctionnalités de la bibliothèque `api-core-utils` et montre comment les intégrer efficacement dans le projet `distillery-api`. Pour des cas d'utilisation plus spécifiques ou des fonctionnalités avancées, consultez la documentation complète de la bibliothèque.
