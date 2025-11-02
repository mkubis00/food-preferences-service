package com.mkvbs.food_preferences_service.dto.ingredientReaction

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import java.util.*

@Schema(
    name= "IngredientReaction",
    description = "Schema used to hold information needed to create ingredient reaction"
)
data class IngredientReactionDto(
    @field:Schema(
        description = "UUID of the user which reacted to passed ingredient ID",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    @field:NotEmpty(message = "Name can not be null or empty")
    val userId: UUID,

    @field:Schema(
        description = "UUID of the ingredient",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    @field:NotEmpty(message = "Name can not be null or empty")
    val ingredientId: UUID,

    @field:Schema(
        description = "Defines if user likes ingredient or not",
        example = "true"
    )
    @field:NotEmpty(message = "IsLiked can not be null or empty")
    val isLiked: Boolean,
)