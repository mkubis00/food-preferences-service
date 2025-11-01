package com.mkvbs.food_preferences_service.domain

import java.time.OffsetDateTime
import java.util.*

open class RecipeReaction(
    val id: UUID?,
    val userId: UUID,
    val recipeId: UUID,
    val isLiked: Boolean,
    var reactedAt: OffsetDateTime?,
)