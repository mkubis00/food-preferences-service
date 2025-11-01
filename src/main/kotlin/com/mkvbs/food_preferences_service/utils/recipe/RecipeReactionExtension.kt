package com.mkvbs.food_preferences_service.utils.recipe

import com.mkvbs.food_preferences_service.domain.RecipeReaction
import com.mkvbs.food_preferences_service.dto.recipeReaction.RecipeReactionResponseDto
import com.mkvbs.food_preferences_service.entity.RecipeReactionEntity
import com.mkvbs.food_preferences_service.exception.ResourceAttributeNullException
import com.mkvbs.food_preferences_service.exception.ResourceIdNullException

fun RecipeReaction.toEntity(): RecipeReactionEntity {
    return RecipeReactionEntity(id, userId, recipeId, isLiked, reactedAt)
}

fun RecipeReaction.toResponse(): RecipeReactionResponseDto {
    if (id == null) throw ResourceIdNullException("ID of recipe reaction can not be null")
    if (reactedAt == null) throw ResourceAttributeNullException("Attribute reactedAt for recipe reaction with ID '$id' can not be null")
    return RecipeReactionResponseDto(id, userId, recipeId, isLiked, reactedAt!!)
}