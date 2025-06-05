package com.jtissdev.commons.controllers;

import com.jtissdev.commons.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * Contrôleur commun fournissant des endpoints pour les métriques d'application.
 * Ce contrôleur expose des endpoints pour obtenir des informations sur les performances
 * et l'utilisation des ressources de l'application.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/metrics")
@ConditionalOnClass(name = "org.springframework.boot.actuate.endpoint.annotation.Endpoint")
@ConditionalOnMissingBean(name = "metricsEndpoint")
public class CommonMetricsController {

    private static final Logger logger = LoggerFactory.getLogger(CommonMetricsController.class);

    /**
     * Récupère les métriques JVM de base pour l'application en cours d'exécution.
     *
     * @return Une réponse API contenant des métriques JVM (mémoire, threads, CPU).
     */
    @GetMapping("/jvm")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getJvmMetrics() {
        Map<String, Object> metrics = new HashMap<>();

        // Métriques de mémoire
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        Map<String, Object> memoryMetrics = new HashMap<>();
        memoryMetrics.put("heapMemoryUsage", memoryMXBean.getHeapMemoryUsage());
        memoryMetrics.put("nonHeapMemoryUsage", memoryMXBean.getNonHeapMemoryUsage());
        metrics.put("memory", memoryMetrics);

        // Métriques de threads
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        Map<String, Object> threadMetrics = new HashMap<>();
        threadMetrics.put("threadCount", threadMXBean.getThreadCount());
        threadMetrics.put("daemonThreadCount", threadMXBean.getDaemonThreadCount());
        threadMetrics.put("peakThreadCount", threadMXBean.getPeakThreadCount());
        threadMetrics.put("totalStartedThreadCount", threadMXBean.getTotalStartedThreadCount());
        metrics.put("threads", threadMetrics);

        // Métriques du système d'exploitation
        OperatingSystemMXBean osMXBean = ManagementFactory.getOperatingSystemMXBean();
        Map<String, Object> osMetrics = new HashMap<>();
        osMetrics.put("availableProcessors", osMXBean.getAvailableProcessors());
        osMetrics.put("systemLoadAverage", osMXBean.getSystemLoadAverage());
        metrics.put("os", osMetrics);

        logger.debug("JVM metrics collected: {}", metrics);

        return ResponseEntity.ok(ApiResponse.success(metrics, "JVM metrics"));
    }

    /**
     * Récupère les métriques de l'application, y compris le temps d'exécution.
     *
     * @return Une réponse API contenant des métriques d'application.
     */
    @GetMapping("/application")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getApplicationMetrics() {
        Map<String, Object> metrics = new HashMap<>();

        // Temps d'exécution
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        metrics.put("uptime", uptime);
        metrics.put("uptimeFormatted", formatUptime(uptime));

        // Propriétés JVM
        Map<String, Object> jvmProps = new HashMap<>();
        jvmProps.put("javaVersion", System.getProperty("java.version"));
        jvmProps.put("javaVendor", System.getProperty("java.vendor"));
        jvmProps.put("javaHome", System.getProperty("java.home"));
        metrics.put("jvmProperties", jvmProps);

        logger.debug("Application metrics collected: {}", metrics);

        return ResponseEntity.ok(ApiResponse.success(metrics, "Application metrics"));
    }

    /**
     * Formate le temps d'exécution en une chaîne lisible (jours, heures, minutes, secondes).
     *
     * @param uptime Le temps d'exécution en millisecondes.
     * @return Une chaîne formatée représentant le temps d'exécution.
     */
    private String formatUptime(long uptime) {
        long uptimeInSeconds = uptime / 1000;
        long days = uptimeInSeconds / (24 * 3600);
        long remainingSeconds = uptimeInSeconds % (24 * 3600);
        long hours = remainingSeconds / 3600;
        remainingSeconds %= 3600;
        long minutes = remainingSeconds / 60;
        long seconds = remainingSeconds % 60;

        return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
    }
}
