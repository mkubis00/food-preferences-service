package com.mkvbs.food_preferences_service.utils.ingredient

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.dto.ingredientReaction.IngredientReactionResponseDto
import com.mkvbs.food_preferences_service.entity.IngredientReactionEntity

fun IngredientReaction.toEntity(): IngredientReactionEntity {
    return IngredientReactionEntity(id, userId, ingredientId, isLiked)
}

fun IngredientReaction.toResponse(): IngredientReactionResponseDto {
    return IngredientReactionResponseDto(userId, ingredientId, isLiked)
}