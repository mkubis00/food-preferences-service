package com.mkvbs.food_preferences_service.dto.ingredientReaction

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(
    name = "IngredientReactionResponse",
    description = "Schema to hold ingredient reaction information"
)
data class IngredientReactionResponseDto(

    @field:Schema(
        description = "UUID of the user which reacted to passed ingredient ID",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val userId: UUID,

    @field:Schema(
        description = "UUID of the ingredient",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val ingredientId: UUID,

    @field:Schema(
        description = "Defines if user likes ingredient or not",
        example = "true"
    )
    val isLiked: Boolean,
)
