package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.RecipeReaction
import com.mkvbs.food_preferences_service.repository.RecipeReactionRepository
import com.mkvbs.food_preferences_service.utils.recipe.toDomain
import com.mkvbs.food_preferences_service.utils.recipe.toEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*


@Service
class RecipeReactionServiceImpl(
    private val repository: RecipeReactionRepository,
) : IRecipeService {

    @Transactional
    override fun create(reaction: RecipeReaction): RecipeReaction {
        val existingReaction = repository.findByUserIdAndRecipeId(reaction.userId, reaction.recipeId).orElse(null)

        return if (existingReaction == null) {
            repository.save(reaction.toEntity())
        } else {
            if (existingReaction.isLiked != reaction.isLiked) {
                existingReaction.isLiked = reaction.isLiked
                repository.save(existingReaction)
            } else {
                existingReaction
            }
        }.toDomain()
    }

    @Transactional
    override fun deleteAllByUser(userId: UUID) {
        // In future maybe check is user with this ID exists
        repository.deleteByUserId(userId)
    }
}