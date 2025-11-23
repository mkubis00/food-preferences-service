package com.mkvbs.food_preferences_service.utils.ingredient

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.dto.ingredientReaction.IngredientReactionResponseDto
import com.mkvbs.food_preferences_service.entity.IngredientReactionEntity
import com.mkvbs.food_preferences_service.exception.MissingIdException

fun IngredientReaction.toResponseDto(): IngredientReactionResponseDto {
    if (id == null) throw MissingIdException("IngredientReactionResponseDto ID can not be null")
    return IngredientReactionResponseDto(id, userId, ingredientId, isLiked)
}

fun IngredientReaction.toEntity(): IngredientReactionEntity {
    return IngredientReactionEntity(id, userId, ingredientId, isLiked, reactedAt)
}