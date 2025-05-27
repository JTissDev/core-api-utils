package com.jtissdev.core_api_utils.exception;

/**
 * Exception personnalisée pour les erreurs d'API avec un code d'erreur associé.
 * <p>
 * Cette exception est utilisée pour signaler des erreurs spécifiques à l'API,
 * en incluant à la fois un code d'erreur et un message descriptif.
 * Elle étend {@link RuntimeException} et peut donc être lancée sans être déclarée.
 *
 * @author J.Tissier
 * @version 1.0.0
 * @since 1.0.0
 */
public class ApiException extends RuntimeException {
	/**
	 * Code d'erreur associé à cette exception.
	 * <p>
	 * Ce code est utilisé pour identifier le type d'erreur spécifique qui s'est produite.
	 * Il peut être utilisé pour la journalisation, le traitement des erreurs
	 * ou pour fournir des informations détaillées à l'utilisateur.
	 *
	 */
	private final String code;

	/**
	 * Construit une nouvelle instance d'ApiException avec le code d'erreur et le message spécifiés.
	 *
	 * @param code Le code d'erreur associé à cette exception, utilisé pour identifier le type d'erreur
	 * @param message Le message descriptif de l'erreur
	 * 
	 * @since 1.0.0
	 */
	public ApiException(String code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Récupère le code d'erreur associé à cette exception.
	 *
	 * @return Le code d'erreur sous forme de chaîne de caractères
	 * 
	 * @since 1.0.0
	 */
	public String getCode() {
		return code;
	}
}