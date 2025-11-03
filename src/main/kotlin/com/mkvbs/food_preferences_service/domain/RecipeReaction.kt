package com.mkvbs.food_preferences_service.domain

import java.time.Instant
import java.util.*


open class RecipeReaction(
    val id: UUID?,
    val userId: UUID,
    val recipeId: UUID,
    var isLiked: Boolean,
    var reactedAt: Instant?,
)