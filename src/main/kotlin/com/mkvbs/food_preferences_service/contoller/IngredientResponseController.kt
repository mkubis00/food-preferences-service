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
    name = "Ingredient Reactions",
    description = "API for managing user ingredient reactions such as likes and dislikes."
)
@RestController
@RequestMapping("/api/ingredient-reaction/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class IngredientResponseController(
    private val ingredientResponseService: IIngredientReactionService,
) {

    @Operation(
        summary = "Get negative ingredient reactions for a specific user",
        description = "Returns a list of ingredient reactions marked as negative (disliked) for the given user."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "List of negative ingredient reactions successfully returned.\n"
            ),
            ApiResponse(
                responseCode = "500",
                description = "Unexpected server error while retrieving ingredient reactions.\n",
                content = [Content(
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
            status = response.statusCode.value(),
            durationMs = System.currentTimeMillis() - start,
            request = request
        )

        return response
    }

    @Operation(
        summary = "Create a new ingredient reaction",
        description = "Creates a new reaction (liked or disliked) for a specific ingredient and user."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Ingredient reaction successfully created."),
            ApiResponse(
                responseCode = "409",
                description = "Conflict — the reaction already exists for this user and ingredient.",
                content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Unexpected server error while creating ingredient reactions.",
                content = [Content(
                    schema = Schema(
                        implementation = ErrorResponseDto::class
                    )
                )]
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
            status = response.statusCode.value(),
            durationMs = System.currentTimeMillis() - start,
            request = request
        )

        return response
    }

    @Operation(
        summary = "Delete ingredient reactions by IDs",
        description = "Deletes multiple ingredient reactions identified by their UUIDs."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Ingredient reactions successfully deleted."),
            ApiResponse(
                responseCode = "500",
                description = "Unexpected server error while deleting ingredient reactions.",
                content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]
            )
        ]
    )
    @DeleteMapping("remove-ingredients-reactions/{ids}")
    fun deleteIngredientReactions(@PathVariable ids: List<UUID>, request: HttpServletRequest): ResponseEntity<Void> {

        val start = System.currentTimeMillis()

        LoggingHelper.logRequest(
            controllerClass = IngredientResponseController::class.java,
            method = "DELETE",
            path = "/api/ingredient-reaction/v1/remove-ingredients-reactions",
            pathVariables = mapOf("IngredientIdToDelete" to ids.toString()),
            request = request
        )

        LoggingHelper.logResponse(
            controllerClass = IngredientResponseController::class.java,
            path = "/api/ingredient-reaction/v1/remove-ingredients-reactions",
            status = HttpStatus.NO_CONTENT.value(),
            durationMs = System.currentTimeMillis() - start,
            request = request
        )

        ingredientResponseService.deleteIngredientReactionsByIds(ids)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @Operation(
        summary = "Delete ingredient reaction by ID",
        description = "Deletes ingredient reaction identified by their UUIDs."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Ingredient reaction successfully deleted."),
            ApiResponse(
                responseCode = "500",
                description = "Unexpected server error while deleting ingredient reaction.",
                content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]
            )
        ]
    )
    @DeleteMapping("remove-ingredients-reaction/{ids}")
    fun deleteIngredientReaction(@PathVariable id: UUID, request: HttpServletRequest): ResponseEntity<Void> {

        val start = System.currentTimeMillis()

        LoggingHelper.logRequest(
            controllerClass = IngredientResponseController::class.java,
            method = "DELETE",
            path = "/api/ingredient-reaction/v1/remove-ingredients-reaction",
            pathVariables = mapOf("IngredientIdToDelete" to id.toString()),
            request = request
        )

        ingredientResponseService.deleteIngredientReactionById(id)

        LoggingHelper.logResponse(
            controllerClass = IngredientResponseController::class.java,
            path = "/api/ingredient-reaction/v1/remove-ingredients-reaction",
            status = HttpStatus.NO_CONTENT.value(),
            durationMs = System.currentTimeMillis() - start,
            request = request
        )

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}