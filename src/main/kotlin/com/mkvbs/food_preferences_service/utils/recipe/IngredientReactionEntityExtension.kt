package com.mkvbs.food_preferences_service.utils.recipe

import com.mkvbs.food_preferences_service.domain.RecipeReaction
import com.mkvbs.food_preferences_service.entity.RecipeReactionEntity
import com.mkvbs.food_preferences_service.exception.ResourceIdNullException

fun RecipeReactionEntity.toDomain(): RecipeReaction {
    if (id == null) throw ResourceIdNullException("Id of the recipe reaction is null")
    return RecipeReaction(id, userId, recipeId, isLiked, reactedAt)
}