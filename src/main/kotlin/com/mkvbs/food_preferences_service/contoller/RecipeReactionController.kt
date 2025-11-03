package com.mkvbs.food_preferences_service.contoller

import com.mkvbs.food_preferences_service.dto.recipeReaction.RecipeReactionDto
import com.mkvbs.food_preferences_service.dto.recipeReaction.RecipeReactionResponseDto
import com.mkvbs.food_preferences_service.service.IRecipeService
import com.mkvbs.food_preferences_service.utils.recipe.toDomain
import com.mkvbs.food_preferences_service.utils.recipe.toResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

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
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status CREATED"
    )
    @PostMapping("/add-recipe-reaction")
    fun addRecipeReaction(@Valid @RequestBody reactionToCreate: RecipeReactionDto): ResponseEntity<RecipeReactionResponseDto> {
        val reactionDomain = reactionToCreate.toDomain()
        val savedReaction = service.create(reactionDomain).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReaction)
    }

    @Operation(
        summary = "Delete recipe reaction REST API",
        description = "REST API to delete recipe reaction for specific user"
    )
    @ApiResponse(
        responseCode = "204",
        description = "HTTP Status No content"
    )
    @DeleteMapping("/delete-recipe-reactions/{recipeId}")
    fun deleteAllByUserId(@PathVariable recipeId: UUID): ResponseEntity<Void> {
        service.deleteAllByUser(recipeId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}