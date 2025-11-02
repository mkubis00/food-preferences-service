package com.mkvbs.food_preferences_service.domain

import java.util.*

open class IngredientReaction(
    val id: UUID?,
    val userId: UUID,
    val ingredientId: UUID,
    val isLiked: Boolean,
)
