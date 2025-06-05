package com.jtissdev.commons.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Utilitaires pour la sécurité de l'application.
 * Cette classe fournit des méthodes utiles pour accéder aux informations
 * de sécurité et d'authentification dans l'application.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public class SecurityUtils {

    private SecurityUtils() {
        // Empêche l'instanciation
    }

    /**
     * Récupère le nom d'utilisateur de l'utilisateur actuellement authentifié.
     *
     * @return Un Optional contenant le nom d'utilisateur si authentifié, ou vide sinon.
     */
    public static Optional<String> getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return Optional.of(((UserDetails) principal).getUsername());
        }

        if (principal instanceof String) {
            return Optional.of((String) principal);
        }

        return Optional.empty();
    }

    /**
     * Vérifie si l'utilisateur actuel a une autorité spécifique.
     *
     * @param authority L'autorité à vérifier.
     * @return true si l'utilisateur a l'autorité spécifiée, false sinon.
     */
    public static boolean hasAuthority(String authority) {
        return getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(authority));
    }

    /**
     * Vérifie si l'utilisateur actuel a l'une des autorités spécifiées.
     *
     * @param authorities Les autorités à vérifier.
     * @return true si l'utilisateur a au moins une des autorités spécifiées, false sinon.
     */
    public static boolean hasAnyAuthority(String... authorities) {
        Collection<? extends GrantedAuthority> userAuthorities = getAuthorities();
        for (String authority : authorities) {
            if (userAuthorities.stream().anyMatch(a -> a.getAuthority().equals(authority))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Récupère les autorités de l'utilisateur actuellement authentifié.
     *
     * @return Une collection d'autorités, ou une collection vide si non authentifié.
     */
    public static Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Collections.emptyList();
        }

        return authentication.getAuthorities();
    }

    /**
     * Vérifie si un utilisateur est actuellement authentifié.
     *
     * @return true si un utilisateur est authentifié, false sinon.
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * Vérifie si l'utilisateur actuel est anonyme.
     *
     * @return true si l'utilisateur est anonyme, false sinon.
     */
    public static boolean isAnonymous() {
        return hasAuthority("ROLE_ANONYMOUS");
    }

    /**
     * Récupère l'objet Authentication actuel.
     *
     * @return Un Optional contenant l'objet Authentication si disponible, ou vide sinon.
     */
    public static Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }
}
