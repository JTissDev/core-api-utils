# Changelog

All notable changes to this project will be documented in this file.

## [1.1.0] - 2025-05-27
### Added
- Ajout de nouvelles méthodes dans `ApiInfoBuilder` pour utiliser les informations du POM :
  - `buildInfo(String serviceName, Map<String, Object> pomInfo)`
  - `fullInfo(String serviceName, Map<String, Object> pomInfo)`
- Intégration complète avec `PomInfoReader` pour récupérer automatiquement les métadonnées du projet

### Changed
- Amélioré l'extraction des métadonnées du POM dans le `PomInfoReader`

## [1.0.0] - 2025-05-01
### Added
- Initial project setup.
- Core utility classes for validation, logging, and error handling.
- Custom validation annotations (`@ValidUUID`, `@ValidISODate`, etc.).
- Centralized exception handling with `GlobalExceptionHandler`.
- Pre-configured Jackson `ObjectMapper` for Java 8+ date/time types.

### Changed
- Improved `ApiInfoBuilder` to include additional metadata fields.

### Fixed
- N/A
