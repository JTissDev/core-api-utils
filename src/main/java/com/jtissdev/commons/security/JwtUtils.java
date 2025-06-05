package com.jtissdev.commons.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utilitaire pour la manipulation des tokens JWT (JSON Web Tokens).
 * Cette classe fournit des méthodes pour générer, valider et manipuler des tokens JWT,
 * utilisés pour l'authentification et l'autorisation.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@Component
public class JwtUtils {

    @Value("${jwt.secret:defaultSecretKeyWhichShouldBeChangedInProduction}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private long expirationMs; // 24 heures par défaut

    /**
     * Extrait le nom d'utilisateur du token JWT.
     *
     * @param token Le token JWT.
     * @return Le nom d'utilisateur extrait du token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait la date d'expiration du token JWT.
     *
     * @param token Le token JWT.
     * @return La date d'expiration du token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrait une revendication spécifique du token JWT.
     *
     * @param token          Le token JWT.
     * @param claimsResolver La fonction à appliquer aux revendications pour extraire la valeur désirée.
     * @param <T>            Le type de la revendication à extraire.
     * @return La valeur de la revendication extraite.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrait toutes les revendications du token JWT.
     *
     * @param token Le token JWT.
     * @return Toutes les revendications contenues dans le token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Vérifie si le token est expiré.
     *
     * @param token Le token JWT à vérifier.
     * @return true si le token est expiré, false sinon.
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    /**
     * Génère un token JWT pour un utilisateur.
     *
     * @param userDetails Les détails de l'utilisateur pour lequel générer le token.
     * @return Le token JWT généré.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Génère un token JWT avec des revendications supplémentaires.
     *
     * @param userDetails Les détails de l'utilisateur pour lequel générer le token.
     * @param claims      Les revendications supplémentaires à inclure dans le token.
     * @return Le token JWT généré avec les revendications supplémentaires.
     */
    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Crée un token JWT avec les revendications spécifiées et le sujet.
     *
     * @param claims Les revendications à inclure dans le token.
     * @param subject Le sujet du token (généralement le nom d'utilisateur).
     * @return Le token JWT créé.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Valide un token JWT pour un utilisateur spécifique.
     *
     * @param token       Le token JWT à valider.
     * @param userDetails Les détails de l'utilisateur pour lequel valider le token.
     * @return true si le token est valide pour l'utilisateur spécifié, false sinon.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Obtient la clé de signature pour les tokens JWT.
     *
     * @return La clé de signature.
     */
    private Key getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
