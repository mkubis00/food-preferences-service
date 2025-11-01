package com.mkvbs.food_preferences_service.domain

import java.time.LocalDateTime
import java.util.*

open class RecipeReaction(
    val id: UUID?,
    val userId: UUID,
    val recipeId: UUID,
    val isLiked: Boolean,
    val reactedAt: LocalDateTime?,
)