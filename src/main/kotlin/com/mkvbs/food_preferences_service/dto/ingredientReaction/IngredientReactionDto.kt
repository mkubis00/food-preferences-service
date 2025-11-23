package com.mkvbs.food_preferences_service.dto.ingredientReaction

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

/**
 * DTO representing a user's reaction to a specific ingredient which is going to be created.
 *
 * This object is used when creating information about how a user
 * rated or marked a particular ingredient — whether they like it or not.
 *
 * @property userId Unique identifier of the user who performed the reaction.
 * @property ingredientId Unique identifier of the ingredient being evaluated.
 * @property isLiked Indicates whether the ingredient is liked (`true`) or disliked (`false`).
 */
@Schema(
    name = "IngredientReaction",
    description = "Represents a user's reaction to a specific ingredient. Use when you what to add new reaction." +
            " Indicating whether it is liked or not."
)
data class IngredientReactionDto(
    @field:Schema(
        description = "Unique identifier of the user who provided the reaction.",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val userId: UUID,

    @field:Schema(
        description = "Unique identifier of the ingredient being evaluated.",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val ingredientId: UUID,

    @field:Schema(
        description = "Defines whether the ingredient is liked (`true`) or disliked (`false`).",
        example = "false"
    )
    val isLiked: Boolean,
)