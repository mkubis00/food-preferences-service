package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.RecipeReaction
import com.mkvbs.food_preferences_service.exception.ResourceAlreadyExistsException
import com.mkvbs.food_preferences_service.repository.RecipeReactionRepository
import com.mkvbs.food_preferences_service.utils.recipe.toDomain
import com.mkvbs.food_preferences_service.utils.recipe.toEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Service
class RecipeReactionServiceImpl(
    private val repository: RecipeReactionRepository,
) : IRecipeService {

    @Transactional
    override fun create(reaction: RecipeReaction): RecipeReaction {
        val isReactionExist = repository.existsByUserIdAndRecipeId(reaction.userId, reaction.recipeId)
        if (isReactionExist) throw ResourceAlreadyExistsException("Recipe reaction with ${reaction.userId} already exists for user ${reaction.userId}")
        reaction.reactedAt = OffsetDateTime.now(ZoneOffset.UTC)
        val savedReaction = repository.save(reaction.toEntity())
        return savedReaction.toDomain()
    }
}