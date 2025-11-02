package com.mkvbs.food_preferences_service.contoller

import com.mkvbs.food_preferences_service.dto.ingredientReaction.IngredientReactionDto
import com.mkvbs.food_preferences_service.dto.ingredientReaction.IngredientReactionResponseDto
import com.mkvbs.food_preferences_service.service.IIngredientReactionService
import com.mkvbs.food_preferences_service.utils.ingredient.toDomain
import com.mkvbs.food_preferences_service.utils.ingredient.toResponse
import io.swagger.v3.oas.annotations.Operation
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
    name = "CRUD REST APIs for ingredient reactions",
    description = "Rest Api to create, get, delete ingredient reactions"
)
@RestController
@RequestMapping("/api/ingredient-reaction/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class IngredientReactionController(
    val service: IIngredientReactionService
) {

    @Operation(
        summary = "Create ingredient reaction REST API",
        description = "REST API to create new ingredient reaction. " +
                "If reaction with only different isLike status already exists override isLike attribute and returns it. " +
                "If exactly the same reaction exists return existing ingredient reaction"
    )
    @PostMapping("/add-ingredient-reaction")
    fun addRecipeReaction(@Valid @RequestBody reactionToCreate: IngredientReactionDto): ResponseEntity<IngredientReactionResponseDto> {
        val reactionDomain = reactionToCreate.toDomain()
        val savedReactionResponseDto = service.create(reactionDomain).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReactionResponseDto)
    }
}