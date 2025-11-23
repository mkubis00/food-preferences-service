package com.mkvbs.food_preferences_service.contoller

import com.mkvbs.food_preferences_service.dto.ErrorResponseDto
import com.mkvbs.food_preferences_service.dto.ingredientReaction.IngredientReactionDto
import com.mkvbs.food_preferences_service.dto.ingredientReaction.IngredientReactionResponseDto
import com.mkvbs.food_preferences_service.service.IIngredientReactionService
import com.mkvbs.food_preferences_service.utils.ingredient.LoggingHelper
import com.mkvbs.food_preferences_service.utils.ingredient.toDomain
import com.mkvbs.food_preferences_service.utils.ingredient.toResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Tag(
    name = "TO BE UPDATED",
    description = "TO BE UPDATED"
)
@RestController
@RequestMapping("/api/ingredient-reaction/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class IngredientResponseController(
    private val ingredientResponseService: IIngredientReactionService,
) {

    @Operation(
        summary = "TO BE UPDATED",
        description = "TO BE UPDATED"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "TO BE UPDATED"),
            ApiResponse(
                responseCode = "500", description = "TO BE UPDATED", content = [Content(
                    schema = Schema(
                        implementation = ErrorResponseDto::class
                    )
                )]
            )
        ]
    )
    @GetMapping("/get-negative-ingredients-reaction-for-user/{userId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getNegativeIngredientsReactionsForUser(
        @Validated @PathVariable userId: UUID,
        request: HttpServletRequest
    ): ResponseEntity<List<IngredientReactionResponseDto>> {
        val start = System.currentTimeMillis()

        LoggingHelper.logRequest(
            controllerClass = IngredientResponseController::class.java,
            method = "GET",
            path = "/api/ingredient-reaction/v1/get-negative-ingredients-reaction-for-user/$userId",
            pathVariables = mapOf("userId" to userId),
            request = request
        )

        val userReactions = ingredientResponseService.getNegativeReactionsForUser(userId).map { it.toResponseDto() }
        val response = ResponseEntity.status(HttpStatus.OK).body(userReactions)

        LoggingHelper.logResponse(
            controllerClass = IngredientResponseController::class.java,
            path = "/api/ingredient-reaction/v1/get-negative-ingredients-reaction-for-user/$userId",
            pathVariables = mapOf("userId" to userId),
            status = response.statusCode.value(),
            durationMs = System.currentTimeMillis() - start,
            request = request
        )

        return response
    }

    @Operation(
        summary = "TO BE UPDATED",
        description = "TO BE UPDATED"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "TO BE UPDATED"),
            ApiResponse(
                responseCode = "409",
                description = "TO BE UPDATED",
                content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]
            )
        ]
    )
    @PostMapping("/create-ingredient-reaction")
    fun createIngredientReaction(
        @Valid @RequestBody reactionToSave: IngredientReactionDto,
        request: HttpServletRequest
    ): ResponseEntity<IngredientReactionResponseDto> {
        val start = System.currentTimeMillis()

        LoggingHelper.logRequest(
            controllerClass = IngredientResponseController::class.java,
            method = "POST",
            path = "/api/ingredient-reaction/v1/create-ingredient-reaction",
            pathVariables = mapOf(),
            request = request
        )

        val savedReactionResponseDto =
            ingredientResponseService.createIngredientReaction(reactionToSave.toDomain()).toResponseDto()
        val response = ResponseEntity.status(HttpStatus.OK).body(savedReactionResponseDto)

        LoggingHelper.logResponse(
            controllerClass = IngredientResponseController::class.java,
            path = "/api/ingredient-reaction/v1/create-ingredient-reaction",
            pathVariables = mapOf(),
            status = response.statusCode.value(),
            durationMs = System.currentTimeMillis() - start,
            request = request
        )

        return response
    }

    @Operation(
        summary = "TO BE UPDATED",
        description = "TO BE UPDATED"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "TO BE UPDATED"),
            ApiResponse(
                responseCode = "500",
                description = "TO BE UPDATED",
                content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]
            )
        ]
    )
    @DeleteMapping("remove-ingredient-reactions/{ids}")
    fun deleteIngredientReactions(@PathVariable ids: List<UUID>): ResponseEntity<Void> {
        ingredientResponseService.deleteIngredientReactionByIds(ids)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}