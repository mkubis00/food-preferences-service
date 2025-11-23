package com.mkvbs.food_preferences_service.utils.ingredient

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.dto.ingredientReaction.IngredientReactionDto

fun IngredientReactionDto.toDomain(): IngredientReaction {
    return IngredientReaction(null, userId, ingredientId, isLiked, null)
}