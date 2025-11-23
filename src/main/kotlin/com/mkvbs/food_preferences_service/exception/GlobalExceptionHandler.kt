package com.mkvbs.food_preferences_service.exception

import com.mkvbs.food_preferences_service.dto.ErrorResponseDto
import jakarta.validation.ConstraintViolationException
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
 * Global exception handler
 *
 * @constructor Create empty Global exception handler
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    /**
     * Handle global exceptions
     *
     * @param ex cached Exception
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(Exception::class)
    fun handleGlobalExceptions(ex: Exception): ResponseEntity<ErrorResponseDto> {
        val errorResponse = if (ex.message != null) {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.message!!)
        } else {
            ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error, ${ex::class.simpleName}")
        }
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    /**
     * Handle method argument not valid.
     * Validate requests bodies.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return nmap with uncorrected attributes and the reasons
     */
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors: Map<String, String?> = ex.bindingResult.allErrors.associate { error ->
            val fieldName = if (error is FieldError) error.field else error.objectName
            fieldName to error.defaultMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    /**
     * Handle constraint violation.
     * It's used for example during path variable validations
     *
     * @param ex cached ConstraintViolationException
     * @return map with uncorrected variable and the reasons
     */
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<Map<String, String?>> {
        val errors: Map<String, String?> = ex.constraintViolations.associate { violation ->
            val field = violation.propertyPath.toString().substringAfterLast(".")
            field to violation.message
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}