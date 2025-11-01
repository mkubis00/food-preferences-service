package com.mkvbs.food_preferences_service.domain

import java.time.LocalDateTime
import java.util.UUID

open class IngredientReaction(
    val id: UUID?,
    val userId: UUID,
    val ingredientId: UUID,
    val isLiked: Boolean,
    val reactedAt: LocalDateTime?
)
