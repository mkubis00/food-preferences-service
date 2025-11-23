package com.mkvbs.food_preferences_service.domain

import java.time.Instant
import java.util.*

/**
 * Domain model representing a user's reaction to an ingredient.
 *
 * A reaction expresses whether a user likes or dislikes a specific ingredient.
 * A reaction instance may exist before it is fully persisted, therefore some
 * system-generated fields (such as `id` or `reactedAt`) can be null until the
 * reaction is first stored.
 *
 * Business rules:
 * - A user can react to an ingredient, marking it as liked or disliked.
 * - A reaction is immutable in terms of identity (`id`, `userId`, `ingredientId`, 'isLiked').
 * - The moment of the first reaction (`reactedAt`) is recorded when the reaction
 *   enters the system for the first time.
 *
 * @property id
 *   Unique identifier of the reaction. Null when the reaction is created in memory
 *   and not yet persisted.
 *
 * @property userId
 *   Identifier of the user expressing the reaction.
 *
 * @property ingredientId
 *   Identifier of the ingredient being evaluated.
 *
 * @property isLiked
 *   Indicates whether the ingredient is liked (`true`) or disliked (`false`).
 *
 * @property reactedAt
 *   Timestamp of when the reaction was first registered. Null for new, not-yet-persisted reactions.
 */
open class IngredientReaction(
    val id: UUID?,
    val userId: UUID,
    val ingredientId: UUID,
    val isLiked: Boolean,
    val reactedAt: Instant?,
)
