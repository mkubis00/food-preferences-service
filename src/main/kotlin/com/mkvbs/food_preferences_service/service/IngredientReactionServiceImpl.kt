package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.repository.IngredientReactionRepository
import com.mkvbs.food_preferences_service.utils.ingredient.toDomain
import com.mkvbs.food_preferences_service.utils.ingredient.toEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class IngredientReactionServiceImpl(
    val repository: IngredientReactionRepository
) : IIngredientReactionService {

    @Transactional
    override fun create(reaction: IngredientReaction): IngredientReaction {
        val existingReaction = repository
            .findByUserIdAndIngredientId(reaction.userId, reaction.ingredientId)
            .orElse(null)

        return if (existingReaction == null) {
            repository.save(reaction.toEntity())
        } else {
            if (reaction.isLiked != existingReaction.isLiked) {
                existingReaction.isLiked = reaction.isLiked
                repository.save(existingReaction)
            } else {
                existingReaction
            }
        }.toDomain()
    }

}