package mx.com.sheff.cardcollectiontracker.core.common.result

/**
 * A sealed result type representing either a successful operation
 * carrying data, or a failure carrying an error.
 *
 * Used across all layers (data, domain, presentation) to make
 * error handling explicit and exhaustive in `when` expressions.
 *
 * Example:
 * ```
 * when (val result = repository.getCards()) {
 *     is Result.Success -> showCards(result.data)
 *     is Result.Failure -> showError(result.error)
 * }
 * ```
 *
 * Prefer this over Kotlin's built-in `kotlin.Result` because:
 *  - It's a real sealed class (exhaustive in `when` statements)
 *  - The error type is constrained to a domain `AppError`,
 *    not any `Throwable`
 *  - It can be used in `Flow<Result<T>>` without unwrapping issues
 */
sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Failure(val error: AppError) : Result<Nothing>
}

/**
 * Domain-level error type. Concrete failures should be expressed
 * as one of these variants rather than raw exceptions.
 *
 * This keeps the domain layer free of framework-specific exceptions
 * (IOException, HttpException, SQLiteException, etc.). Data layer
 * implementations map their exceptions to one of these cases.
 */
sealed class AppError(open val message: String? = null) {

    /** Network connectivity problem (no internet, timeout, etc.). */
    data class Network(override val message: String? = null) : AppError(message)

    /** HTTP error from a remote API (4xx or 5xx). */
    data class Http(val code: Int, override val message: String? = null) : AppError(message)

    /** Local database or storage problem. */
    data class Storage(override val message: String? = null) : AppError(message)

    /** Resource was requested but does not exist. */
    data class NotFound(override val message: String? = null) : AppError(message)

    /** Authentication or authorization problem. */
    data class Unauthorized(override val message: String? = null) : AppError(message)

    /** Catch-all for genuinely unexpected errors. */
    data class Unknown(override val message: String? = null) : AppError(message)
}

/**
 * Returns the data if this is [Result.Success], or `null` otherwise.
 */
fun <T> Result<T>.getOrNull(): T? = (this as? Result.Success)?.data

/**
 * Returns the data if this is [Result.Success], or [default] otherwise.
 */
fun <T> Result<T>.getOrDefault(default: T): T = (this as? Result.Success)?.data ?: default

/**
 * Returns the error if this is [Result.Failure], or `null` otherwise.
 */
fun Result<*>.errorOrNull(): AppError? = (this as? Result.Failure)?.error

/**
 * Returns true if this is [Result.Success].
 */
val Result<*>.isSuccess: Boolean get() = this is Result.Success

/**
 * Returns true if this is [Result.Failure].
 */
val Result<*>.isFailure: Boolean get() = this is Result.Failure
