package com.mkvbs.food_preferences_service.utils.ingredient

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.entity.IngredientReactionEntity

fun IngredientReactionEntity.toDomain(): IngredientReaction {
    return IngredientReaction(id, userId, ingredientId, isLiked, reactedAt)
}