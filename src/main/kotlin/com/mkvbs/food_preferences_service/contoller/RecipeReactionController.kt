package com.mkvbs.food_preferences_service.contoller

import com.mkvbs.food_preferences_service.dto.ErrorResponseDto
import com.mkvbs.food_preferences_service.dto.recipeReaction.RecipeReactionDto
import com.mkvbs.food_preferences_service.dto.recipeReaction.RecipeReactionResponseDto
import com.mkvbs.food_preferences_service.service.IRecipeService
import com.mkvbs.food_preferences_service.utils.recipe.toDomain
import com.mkvbs.food_preferences_service.utils.recipe.toResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "CRUD REST APIs for Recipe reactions",
    description = "Rest Api to create recipe reactions"
)
@RestController
@RequestMapping("/api/recipe-reaction/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class RecipeReactionController(
    private val service: IRecipeService,
) {

    @Operation(
        summary = "Create recipe reaction REST API",
        description = "REST API to create new recipe reaction"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "HTTP Status CREATED"
            ),
            ApiResponse(
                responseCode = "409",
                description = "HTTP Status CONFLICT",
                content = [
                    Content(
                        schema = Schema(implementation = ErrorResponseDto::class)
                    )
                ]
            )
        ]
    )
    @PostMapping("/add-recipe-reaction")
    fun addRecipeReaction(@Valid @RequestBody reactionToCreate: RecipeReactionDto): ResponseEntity<RecipeReactionResponseDto> {
        val reactionDomainToCreate = reactionToCreate.toDomain()
        val createdReaction = service.create(reactionDomainToCreate).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReaction)
    }

}