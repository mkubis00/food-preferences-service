package com.mkvbs.food_preferences_service.utils.recipe

import com.mkvbs.food_preferences_service.domain.RecipeReaction
import com.mkvbs.food_preferences_service.dto.recipeReaction.RecipeReactionDto

fun RecipeReactionDto.toDomain(): RecipeReaction {
    return RecipeReaction(
        null, userId, recipeId, isLiked, null
    )
}