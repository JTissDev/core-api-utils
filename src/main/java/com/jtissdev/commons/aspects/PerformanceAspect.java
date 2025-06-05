package com.jtissdev.commons.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Aspect pour mesurer les performances des méthodes.
 * Cet aspect intercepte les appels aux méthodes configurées et mesure leur temps d'exécution,
 * en enregistrant les méthodes qui prennent plus longtemps qu'un seuil configurable.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@Aspect
@Component
@ConditionalOnProperty(name = "commons.performance.aspect.enabled", havingValue = "true", matchIfMissing = true)
public class PerformanceAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

    /**
     * Seuil en millisecondes au-delà duquel une méthode est considérée comme lente.
     * Peut être configuré via la propriété commons.performance.threshold.ms.
     */
    private final long thresholdMs;

    /**
     * Construit un PerformanceAspect avec le seuil spécifié.
     *
     * @param thresholdMs Le seuil en millisecondes.
     */
    public PerformanceAspect(@org.springframework.beans.factory.annotation.Value(
            "${commons.performance.threshold.ms:500}")
            long thresholdMs) {
        this.thresholdMs = thresholdMs;
        logger.info("Performance monitoring initialized with threshold: {} ms", thresholdMs);
    }

    /**
     * Définit un pointcut pour toutes les méthodes des contrôleurs.
     */
    @Pointcut("within(com.jtissdev.*.controllers..*) || within(*..*Controller)")
    public void controllerPointcut() {
        // Définition du pointcut
    }

    /**
     * Définit un pointcut pour toutes les méthodes des services.
     */
    @Pointcut("within(com.jtissdev.*.services..*) || within(*..*Service)")
    public void servicePointcut() {
        // Définition du pointcut
    }

    /**
     * Définit un pointcut pour toutes les méthodes des repositories.
     */
    @Pointcut("within(com.jtissdev.*.repositories..*) || within(*..*Repository)")
    public void repositoryPointcut() {
        // Définition du pointcut
    }

    /**
     * Mesure le temps d'exécution des méthodes interceptées et journalise celles qui dépassent le seuil.
     *
     * @param joinPoint Le point de jonction où l'aspect est appliqué.
     * @return Le résultat de l'exécution de la méthode interceptée.
     * @throws Throwable Si une exception est lancée par la méthode interceptée.
     */
    @Around("controllerPointcut() || servicePointcut() || repositoryPointcut()")
    public Object measureMethodPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getDeclaringType().getSimpleName();

        long startTime = System.currentTimeMillis();
        try {
            // Execute the method
            return joinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            if (executionTime > thresholdMs) {
                logger.warn("Slow execution detected: {}.{}() executed in {} ms (threshold: {} ms)",
                        className, methodName, executionTime, thresholdMs);
            } else if (logger.isDebugEnabled()) {
                logger.debug("{}.{}() executed in {} ms", className, methodName, executionTime);
            }
        }
    }
}
