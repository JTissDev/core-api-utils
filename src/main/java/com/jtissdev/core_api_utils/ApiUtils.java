package com.jtissdev.core_api_utils;

/**
 * Classe utilitaire principale pour la bibliothèque core-api-utils.
 * Fournit des constantes et des méthodes d'initialisation.
 *
 * @author j.tissier
 * @version 1.1.0
 * @since 1.0.0
 */
public class ApiUtils {

	/**
	 * Version courante de la bibliothèque.
	 * Cette valeur est automatiquement remplacée par la version du projet lors de la compilation.
	 */
	public static final String VERSION = "${project.version}";

	/**
	 * Initialise la bibliothèque et affiche la version.
	 * Cette méthode doit être appelée au démarrage de l'application.
	 *
	 * @author j.tissier
	 * @since 1.0.0
	 */
	public static void init() {
		System.out.println("api-utils loaded. Version: " + VERSION);
	}
}
