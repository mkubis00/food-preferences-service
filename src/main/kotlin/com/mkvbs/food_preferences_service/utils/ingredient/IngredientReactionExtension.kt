package com.mkvbs.food_preferences_service.utils.ingredient

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.dto.ingredientReaction.IngredientReactionResponseDto
import com.mkvbs.food_preferences_service.entity.IngredientReactionEntity
import com.mkvbs.food_preferences_service.exception.ResourceAttributeNullException
import com.mkvbs.food_preferences_service.exception.ResourceIdNullException

fun IngredientReaction.toEntity(): IngredientReactionEntity {
    return IngredientReactionEntity(id, userId, ingredientId, isLiked, reactedAt)
}

fun IngredientReaction.toResponse(): IngredientReactionResponseDto {
    if (id == null) throw ResourceIdNullException("ID of ingredient reaction can not be null")
    if (reactedAt == null) throw ResourceAttributeNullException("Attribute reactedAt for ingredient reaction with ID '$id' can not be null")
    return IngredientReactionResponseDto(id, userId, ingredientId, isLiked, reactedAt)
}