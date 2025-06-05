package com.jtissdev.commons.controllers;

import com.jtissdev.commons.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Contrôleur commun fournissant des endpoints pour vérifier l'état de santé de l'application.
 * Ce contrôleur expose des endpoints pour obtenir des informations sur l'état du service,
 * sa version, et d'autres métadonnées utiles pour la surveillance et le débogage.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/health")
public class CommonHealthController {

    private final Optional<BuildProperties> buildProperties;
    private final Optional<GitProperties> gitProperties;

    /**
     * Constructeur pour CommonHealthController.
     *
     * @param buildProperties Les propriétés de build, peuvent être null si non disponibles.
     * @param gitProperties   Les propriétés Git, peuvent être null si non disponibles.
     */
    @Autowired
    public CommonHealthController(Optional<BuildProperties> buildProperties, Optional<GitProperties> gitProperties) {
        this.buildProperties = buildProperties;
        this.gitProperties = gitProperties;
    }

    /**
     * Endpoint simple pour vérifier si le service répond.
     *
     * @return Une réponse API indiquant que le service est en ligne.
     */
    @GetMapping("/ping")
    public ResponseEntity<ApiResponse<String>> ping() {
        return ResponseEntity.ok(ApiResponse.success("pong", "Service is up and running"));
    }

    /**
     * Endpoint fournissant des informations détaillées sur l'application.
     *
     * @return Une réponse API contenant des métadonnées sur l'application.
     */
    @GetMapping("/info")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getInfo() {
        Map<String, Object> info = new HashMap<>();

        // Ajouter les informations de build si disponibles
        buildProperties.ifPresent(props -> {
            Map<String, Object> buildInfo = new HashMap<>();
            buildInfo.put("version", props.getVersion());
            buildInfo.put("artifact", props.getArtifact());
            buildInfo.put("group", props.getGroup());
            buildInfo.put("name", props.getName());
            buildInfo.put("time", props.getTime().toString());
            info.put("build", buildInfo);
        });

        // Ajouter les informations Git si disponibles
        gitProperties.ifPresent(props -> {
            Map<String, Object> gitInfo = new HashMap<>();
            gitInfo.put("branch", props.getBranch());
            gitInfo.put("commitId", props.getCommitId());
            gitInfo.put("commitTime", props.getCommitTime().toString());
            info.put("git", gitInfo);
        });

        // Ajouter des informations sur le runtime Java
        Map<String, Object> runtimeInfo = new HashMap<>();
        runtimeInfo.put("javaVersion", System.getProperty("java.version"));
        runtimeInfo.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        runtimeInfo.put("freeMemory", Runtime.getRuntime().freeMemory());
        runtimeInfo.put("totalMemory", Runtime.getRuntime().totalMemory());
        info.put("runtime", runtimeInfo);

        return ResponseEntity.ok(ApiResponse.success(info, "Application info"));
    }
}
