package com.jtissdev.commons.dto;

import java.util.List;

/**
 * Structure standardisée pour les réponses d'API paginées.
 * Cette classe étend {@link ApiResponse} pour inclure des informations de pagination
 * avec une collection de données.
 *
 * @param <T> Le type des éléments dans la collection paginée.
 * 
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public class PagedApiResponse<T> extends ApiResponse<List<T>> {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PagedApiResponse() {
        super();
    }

    /**
     * Construit une nouvelle instance PagedApiResponse avec tous les paramètres.
     *
     * @param success       Indique si l'opération a réussi.
     * @param data          La liste d'éléments pour la page actuelle.
     * @param message       Un message descriptif (peut être null).
     * @param page          Le numéro de la page actuelle (base 0).
     * @param size          Le nombre d'éléments par page.
     * @param totalElements Le nombre total d'éléments disponibles.
     * @param totalPages    Le nombre total de pages.
     */
    public PagedApiResponse(boolean success, List<T> data, String message,
                            int page, int size, long totalElements, int totalPages) {
        super(success, data, message);
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    /**
     * Crée une réponse paginée de succès avec les paramètres spécifiés.
     *
     * @param <T>           Le type des éléments dans la collection.
     * @param data          La liste d'éléments pour la page actuelle.
     * @param page          Le numéro de la page actuelle (base 0).
     * @param size          Le nombre d'éléments par page.
     * @param totalElements Le nombre total d'éléments disponibles.
     * @param totalPages    Le nombre total de pages.
     * @return Une instance PagedApiResponse avec les paramètres fournis et success=true.
     */
    public static <T> PagedApiResponse<T> success(List<T> data, int page, int size,
                                                long totalElements, int totalPages) {
        return new PagedApiResponse<>(true, data, null, page, size, totalElements, totalPages);
    }

    /**
     * Crée une réponse paginée de succès avec les paramètres spécifiés et un message.
     *
     * @param <T>           Le type des éléments dans la collection.
     * @param data          La liste d'éléments pour la page actuelle.
     * @param message       Un message descriptif.
     * @param page          Le numéro de la page actuelle (base 0).
     * @param size          Le nombre d'éléments par page.
     * @param totalElements Le nombre total d'éléments disponibles.
     * @param totalPages    Le nombre total de pages.
     * @return Une instance PagedApiResponse avec les paramètres fournis et success=true.
     */
    public static <T> PagedApiResponse<T> success(List<T> data, String message, int page, int size,
                                                long totalElements, int totalPages) {
        return new PagedApiResponse<>(true, data, message, page, size, totalElements, totalPages);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
