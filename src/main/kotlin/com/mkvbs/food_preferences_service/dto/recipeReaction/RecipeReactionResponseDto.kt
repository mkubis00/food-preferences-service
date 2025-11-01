package com.mkvbs.food_preferences_service.dto.recipeReaction

import io.swagger.v3.oas.annotations.media.Schema
import java.time.OffsetDateTime
import java.util.*

@Schema(
    name = "RecipeReactionResponse",
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
        example = "2025-11-01 23:37:16.519633+01"
    )
    val reactedAt: OffsetDateTime,
)