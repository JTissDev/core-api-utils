package com.jtissdev.core_api_utils.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Classe utilitaire pour lire les métadonnées du projet générées à partir du POM et des informations SCM
 */
public class MetadataReader {

    private static final String MAVEN_PROPERTIES_FILE = "/META-INF/maven/com.jtissdev/core-api-utils/pom.properties";

    /**
     * Lit les métadonnées du projet à partir des informations Maven et SCM
     * 
     * @return Map contenant les métadonnées du projet
     */
    public static Map<String, Object> readMetadata() {
        Map<String, Object> metadata = new HashMap<>();

        try (InputStream inputStream = MetadataReader.class.getResourceAsStream(MAVEN_PROPERTIES_FILE)) {
            if (inputStream == null) {
                metadata.put("error", "Fichier de propriétés Maven non trouvé");
                metadata.put("timestamp", getCurrentTimestamp());
                return metadata;
            }

            Properties properties = new Properties();
            properties.load(inputStream);

            // Informations de base du POM
            metadata.put("groupId", properties.getProperty("groupId"));
            metadata.put("artifactId", properties.getProperty("artifactId"));
            metadata.put("version", properties.getProperty("version"));

            // Lecture des informations de build générées par buildnumber-maven-plugin
            Properties buildProps = loadBuildProperties();
            if (buildProps != null) {
                metadata.put("buildNumber", buildProps.getProperty("buildNumber", "UNKNOWN"));
                metadata.put("buildTimestamp", buildProps.getProperty("timestamp", getCurrentTimestamp()));
                metadata.put("scmBranch", buildProps.getProperty("scmBranch", "UNKNOWN"));
            } else {
                metadata.put("timestamp", getCurrentTimestamp());
            }

            // Informations additionnelles à partir du manifeste si disponible
            try {
                Package pkg = MetadataReader.class.getPackage();
                if (pkg != null) {
                    metadata.put("implementationTitle", pkg.getImplementationTitle());
                    metadata.put("implementationVersion", pkg.getImplementationVersion());
                    metadata.put("implementationVendor", pkg.getImplementationVendor());
                }
            } catch (Exception e) {
                // Ignorer les erreurs de manifeste
            }

        } catch (IOException e) {
            metadata.put("error", "Erreur lors de la lecture des métadonnées: " + e.getMessage());
            metadata.put("timestamp", getCurrentTimestamp());
        }

        return metadata;
    }

    /**
     * Charge les propriétés de build générées par buildnumber-maven-plugin
     */
    private static Properties loadBuildProperties() {
        try (InputStream is = MetadataReader.class.getResourceAsStream("/META-INF/build.properties")) {
            if (is == null) return null;

            Properties props = new Properties();
            props.load(is);
            return props;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Obtient le timestamp actuel au format ISO
     */
    private static String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return sdf.format(new Date());
    }
}
