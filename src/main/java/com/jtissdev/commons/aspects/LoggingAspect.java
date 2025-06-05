package com.jtissdev.commons.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect pour la journalisation automatique des entrées/sorties de méthodes.
 * Cet aspect intercepte les appels aux méthodes configurées et enregistre
 * les informations sur les appels, les arguments, les résultats et les exceptions.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@Aspect
@Component
@ConditionalOnProperty(name = "commons.logging.aspect.enabled", havingValue = "true", matchIfMissing = true)
public class LoggingAspect {

    private static final int MAX_PAYLOAD_LENGTH = 10000;

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
     * Intercepte et journalise les appels aux méthodes des contrôleurs, services et repositories.
     *
     * @param joinPoint Le point de jonction où l'aspect est appliqué.
     * @return Le résultat de l'exécution de la méthode interceptée.
     * @throws Throwable Si une exception est lancée par la méthode interceptée.
     */
    @Around("controllerPointcut() || servicePointcut() || repositoryPointcut()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = getLogger(joinPoint);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getDeclaringType().getSimpleName();

        // Log method entry
        if (logger.isDebugEnabled()) {
            logger.debug("Entering: {}.{}() with arguments: {}",
                    className, methodName, truncate(Arrays.toString(joinPoint.getArgs())));
        }

        try {
            // Execute method
            Object result = joinPoint.proceed();

            // Log method exit
            if (logger.isDebugEnabled()) {
                logger.debug("Exiting: {}.{}() with result: {}",
                        className, methodName, truncate(result != null ? result.toString() : "null"));
            }

            return result;
        } catch (Exception e) {
            // Log error but don't handle it
            logger.error("Exception in {}.{}() with cause: {}", className, methodName, e.getMessage());
            throw e;
        }
    }

    /**
     * Intercepte et journalise les exceptions lancées par les méthodes.
     *
     * @param joinPoint Le point de jonction où l'aspect est appliqué.
     * @param e L'exception lancée.
     */
    @AfterThrowing(pointcut = "controllerPointcut() || servicePointcut() || repositoryPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Logger logger = getLogger(joinPoint);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getDeclaringType().getSimpleName();

        logger.error("Exception in {}.{}() with cause: {}", className, methodName, e.getMessage(), e);
    }

    /**
     * Récupère le logger approprié pour la classe cible.
     *
     * @param joinPoint Le point de jonction associé à la classe cible.
     * @return Le logger pour la classe cible.
     */
    private Logger getLogger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }

    /**
     * Tronque une chaîne de caractères si elle dépasse la longueur maximale.
     *
     * @param str La chaîne à tronquer.
     * @return La chaîne tronquée si nécessaire.
     */
    private String truncate(String str) {
        if (str == null) {
            return "null";
        }
        if (str.length() <= MAX_PAYLOAD_LENGTH) {
            return str;
        }
        return str.substring(0, MAX_PAYLOAD_LENGTH) + "... (truncated)";
    }
}
