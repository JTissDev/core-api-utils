package com.jtissdev.core_api_utils.dto;

/**
 * A generic class used to represent the response of an API call, encapsulating
 * the success status, data, and any additional message.
 *
 *
 * @param <T> The type of the data returned in the response.
 * @author J.Tissier
 * @contact jtissdev@gmail.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class ApiResponse<T> {


	private boolean success;
	private T data;
	private String message;

	public ApiResponse() {}

	/**
	 * Constructs a new ApiResponse instance with the specified success status, data, and message.
	 *
	 * @param success Indicates whether the operation or request was successful.
	 * @param data The data returned in the response. This is of generic type T.
	 * @param message An additional message providing context for the response, such as an error or informational note.
	 *                Can be null.
	 *                This message is typically used to provide additional information
	 *                about the success or failure of the operation.
	 *                For example, it can be an error message in case of failure
	 *                or a success message in case of success.
	 *                This message can be null if not needed.
	 *
	 * @since 1.0.0
	 */
	public ApiResponse(boolean success, T data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}

	/**
	 * Creates a new ApiResponse object with the success flag set to true and the provided data.
	 * The message is set to null.
	 *
	 * @param <T> The type of the data included in the response.
	 * @param data The data to include in the successful response. Can be null.
	 * @return An ApiResponse object encapsulating the success status, provided data, and no message.
	 *
	 * @since 1.0.0
	 */
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(true, data, null);
	}


	/**
	 * Creates an error ApiResponse object with the success flag set to false, no data,
	 * and the specified error message.
	 *
	 * @param <T> The type of the data that would have been returned if the response were successful.
	 * @param message The error message to include in the response. Cannot be null.
	 * @return An ApiResponse object encapsulating the failure status, no data, and the provided error message.
	 *
	 * @since 1.0.0
	 */
	public static <T> ApiResponse<T> error(String message) {
		return new ApiResponse<>(false, null, message);
	}

	/**
	 * Returns the success status of the response.
	 *
	 * @return true if the operation or request was successful, otherwise false.
	 *
	 * @since 1.0.0
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Updates the success status of the response.
	 *
	 * @param success A boolean value indicating whether the operation or request was successful.
	 *
	 * @since 1.0.0
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Retrieves the data associated with this response.
	 *
	 * @return The data of type T contained in the response. May be null if no data is present.
	 *
	 * @since 1.0.0
	 */
	public T getData() {
		return data;
	}

	/**
	 * Updates the data associated with this ApiResponse.
	 *
	 * @param data The new data to set for the response. This should be of the same generic type T
	 *             as specified in the ApiResponse. Can be null if no data is to be set.
	 *
	 * @since 1.0.0
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Retrieves the message associated with this response.
	 *
	 * @return The message providing additional information about the response.
	 *         Can be null if no message is set.
	 *
	 * @since 1.0.0
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Updates the message associated with this ApiResponse instance.
	 *
	 * @param message The new message to set for the response.
	 *                This message provides additional context, such as an error
	 *                description or informational note, and can be null if no message is to be set.
	 *
	 * @since 1.0.0
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
