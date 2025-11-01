package com.mkvbs.food_preferences_service.dto.recipeReaction

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@Schema(
    name = "RecipeReaction",
    description = "Schema to hold recipe reaction information"
)
data class RecipeReactionResponseDto(
    @field:Schema(
        description = "UUID of the recipe reaction",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val id: UUID,

    @field:Schema(
        description = "UUID of the user which reacted to passed recipe ID",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val userId: UUID,

    @field:Schema(
        description = "UUID of the recipe",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val recipeId: UUID,

    @field:Schema(
        description = "Defines if user likes recipe or not",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val isLiked: Boolean,

    @field:Schema(
        description = "Defines time when user make his decision",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val reactedAt: LocalDateTime,
)