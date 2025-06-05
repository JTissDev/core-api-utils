# Spring API Commons Library

## Présentation

Cette bibliothèque fournit des composants réutilisables pour standardiser le développement des APIs REST Spring Boot. Elle est conçue pour être intégrée dans plusieurs projets d'API afin de maintenir une cohérence dans le code, les structures de réponse, et les fonctionnalités communes.

## Objectifs

- Standardiser les réponses d'API à travers tous les projets
- Fournir des contrôleurs communs (santé, surveillance, etc.)
- Centraliser les configurations de sécurité et de documentation
- Réduire la duplication de code entre les projets
- Faciliter la maintenance et les mises à jour

## Composants à implémenter

### Modèles de réponse

- `ApiResponse<T>` : Structure standardisée pour les réponses d'API
- `PagedApiResponse<T>` : Structure standardisée pour les réponses paginées
- `ErrorResponse` : Structure standardisée pour les erreurs

### Contrôleurs communs

- `CommonHealthController` : Points de terminaison pour vérifier l'état de santé de l'application
- `CommonMetricsController` : Points de terminaison pour les métriques d'application

### Exceptions et gestionnaires

- `ApiException` : Exception de base pour toutes les erreurs d'API
- `ResourceNotFoundException` : Exception pour les ressources introuvables
- `ValidationException` : Exception pour les erreurs de validation
- `GlobalExceptionHandler` : Gestionnaire commun pour convertir les exceptions en réponses HTTP

### Sécurité

- `SecurityUtils` : Utilitaires pour la sécurité
- `JwtUtils` : Utilitaires pour la manipulation des tokens JWT
- `CommonSecurityConfig` : Configuration de sécurité commune qui peut être étendue

### Documentation

- `CommonOpenApiConfig` : Configuration de base pour OpenAPI/Swagger

### Utilitaires

- `DateUtils` : Utilitaires pour la manipulation des dates
- `StringUtils` : Utilitaires pour la manipulation des chaînes de caractères
- `ValidationUtils` : Utilitaires pour la validation des entrées

### Aspects

- `LoggingAspect` : Aspect pour la journalisation automatique des entrées/sorties de méthodes
- `PerformanceAspect` : Aspect pour mesurer les performances des méthodes

## Structure proposée

```
src/main/java/com/jtissdev/commons/
├── config/
│   ├── CommonOpenApiConfig.java
│   ├── CommonSecurityConfig.java
│   └── CommonWebConfig.java
├── controllers/
│   ├── CommonHealthController.java
│   └── CommonMetricsController.java
├── dto/
│   ├── ApiResponse.java
│   ├── ErrorResponse.java
│   └── PagedApiResponse.java
├── exceptions/
│   ├── ApiException.java
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── ValidationException.java
├── security/
│   ├── JwtUtils.java
│   └── SecurityUtils.java
├── utils/
│   ├── DateUtils.java
│   ├── StringUtils.java
│   └── ValidationUtils.java
└── aspects/
    ├── LoggingAspect.java
    └── PerformanceAspect.java
```

## Intégration dans les projets

### Maven

```xml
<dependency>
    <groupId>com.jtissdev</groupId>
    <artifactId>spring-api-commons</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.jtissdev:spring-api-commons:1.0.0'
```

## Configuration

Pour activer les fonctionnalités de la bibliothèque, ajoutez les annotations suivantes à votre classe principale :

```java
@SpringBootApplication
@Import({
    CommonOpenApiConfig.class,
    CommonSecurityConfig.class,
    CommonWebConfig.class,
    GlobalExceptionHandler.class
})
public class MyApplication {
    // ...
}
```

## Exemple d'utilisation

### Réponse API standardisée

```java
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDto>> getBook(@PathVariable UUID id) {
        BookDto book = bookService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(book, "Livre récupéré avec succès"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }
}
```

## Bonnes pratiques

- Utilisez les structures de réponses communes pour toutes les API
- Étendez les classes de base plutôt que de les dupliquer
- Utilisez les gestionnaires d'exceptions communs
- Configurez les propriétés spécifiques via les fichiers application.properties/application.yml

## Roadmap

- Ajout de fonctionnalités d'audit
- Support pour l'authentification OAuth2
- Support pour la validation basée sur des groupes
- Intégration avec Spring Cloud pour les microservices

## Licence

MIT

## Contact

Jérôme Tissier - jtissdev@gmail.com
