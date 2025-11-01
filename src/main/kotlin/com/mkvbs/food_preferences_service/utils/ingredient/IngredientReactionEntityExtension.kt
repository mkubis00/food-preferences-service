package com.mkvbs.food_preferences_service.utils.ingredient

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.entity.IngredientReactionEntity
import com.mkvbs.food_preferences_service.exception.ResourceIdNullException

fun IngredientReactionEntity.toDomain(): IngredientReaction {
    if (id == null) throw ResourceIdNullException("Id of the ingredient reaction is null")
    return IngredientReaction(id, userId, ingredientId, isLiked, reactedAt)
}