package com.jtissdev.commons.dto;

/**
 * Structure standardisée pour les réponses d'API.
 * Cette classe encapsule le résultat d'une opération d'API, incluant un statut de succès,
 * des données optionnelles et un message descriptif.
 *
 * @param <T> Le type des données incluses dans la réponse.
 * 
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private String message;

    public ApiResponse() {}

    /**
     * Construit une nouvelle instance ApiResponse avec les paramètres spécifiés.
     *
     * @param success Indique si l'opération a réussi.
     * @param data    Les données à inclure dans la réponse (peut être null).
     * @param message Un message descriptif supplémentaire (peut être null).
     */
    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    /**
     * Crée une réponse de succès avec les données spécifiées.
     *
     * @param <T>  Le type des données.
     * @param data Les données à inclure dans la réponse.
     * @return Une instance ApiResponse avec success=true et les données fournies.
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    /**
     * Crée une réponse de succès avec les données et le message spécifiés.
     *
     * @param <T>     Le type des données.
     * @param data    Les données à inclure dans la réponse.
     * @param message Un message descriptif supplémentaire.
     * @return Une instance ApiResponse avec success=true, les données et le message fournis.
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message);
    }

    /**
     * Crée une réponse d'erreur avec le message spécifié.
     *
     * @param <T>     Le type des données (non utilisé car null pour les erreurs).
     * @param message Le message d'erreur.
     * @return Une instance ApiResponse avec success=false, pas de données et le message d'erreur.
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
