package com.mkvbs.food_preferences_service.exception

import com.mkvbs.food_preferences_service.dto.ErrorResponseDto
import jakarta.validation.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Global exception handler.
 *
 * Provides centralized handling for application-wide exceptions and ensures
 * that all errors are logged and returned in a consistent response format.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler(
    private val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
) : ResponseEntityExceptionHandler() {

    private final fun logError(ex: Exception) {
        log.error(ex.message, ex)
    }

    /**
     * Handles all uncaught exceptions.
     *
     * Logs the exception and returns a generic 500 Internal Server Error response.
     *
     * @param ex the thrown exception
     * @return ResponseEntity containing an ErrorResponseDto
     */
    @ExceptionHandler(Exception::class)
    fun handleGlobalExceptions(ex: Exception): ResponseEntity<ErrorResponseDto> {
        logError(ex)
        val errorResponse = if (ex.message != null) {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.message!!)
        } else {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error, ${ex::class.simpleName}")
        }
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    /**
     * Handles validation errors for request bodies annotated with @Valid.
     *
     * Extracts invalid fields and returns a map of field names with associated error messages.
     *
     * @param ex the MethodArgumentNotValidException containing validation errors
     * @param headers HTTP headers
     * @param status HTTP status
     * @param request the web request
     * @return ResponseEntity containing a map of invalid fields and reasons
     */
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        logError(ex)
        val errors: Map<String, String?> = ex.bindingResult.allErrors.associate { error ->
            val fieldName = if (error is FieldError) error.field else error.objectName
            fieldName to error.defaultMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    /**
     * Handles constraint violations such as invalid @PathVariable or @RequestParam.
     *
     * Extracts the invalid parameter name and corresponding validation message.
     *
     * @param ex the ConstraintViolationException
     * @return ResponseEntity containing the invalid field and reason
     */
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<Map<String, String?>> {
        logError(ex)
        val errors: Map<String, String?> = ex.constraintViolations.associate { violation ->
            val field = violation.propertyPath.toString().substringAfterLast(".")
            field to violation.message
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    /**
     * Handles AlreadyExistException thrown when attempting to create a resource
     * that already exists.
     *
     * @param ex the AlreadyExistException
     * @return ResponseEntity with a 409 Conflict status
     */
    @ExceptionHandler(AlreadyExistException::class)
    fun handleAlreadyExistException(ex: AlreadyExistException): ResponseEntity<ErrorResponseDto> {
        logError(ex)
        val errorResponse = if (ex.message != null) {
            ErrorResponseDto(HttpStatus.CONFLICT, ex.message!!)
        } else {
            ErrorResponseDto(HttpStatus.CONFLICT, "Internal Server Error, ${ex::class.simpleName}")
        }
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    /**
     * Handles MissingIdException thrown when an entity is expected to have an ID,
     * but it is missing in the request or database operation.
     *
     * @param ex the MissingIdException
     * @return ResponseEntity with a 500 Internal Server Error status
     */
    @ExceptionHandler(MissingIdException::class)
    fun handleMissingIdException(ex: MissingIdException): ResponseEntity<ErrorResponseDto> {
        logError(ex)
        val errorResponse = if (ex.message != null) {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.message!!)
        } else {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error, ${ex::class.simpleName}")
        }
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}