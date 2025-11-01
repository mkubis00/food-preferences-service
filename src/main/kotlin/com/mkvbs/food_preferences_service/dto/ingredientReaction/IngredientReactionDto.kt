package com.mkvbs.food_preferences_service.dto.ingredientReaction

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(
    name= "IngredientReaction",
    description = "Schema to hold ingredient reaction information"
)
data class IngredientReactionDto(
    @field:Schema(
        description = "UUID of the ingredient reaction, should not be included during creation of the ingredient reaction",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val id: UUID?,

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
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val isLiked: Boolean,
)