package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.RecipeReaction
import com.mkvbs.food_preferences_service.repository.RecipeReactionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class RecipeReactionServiceImpl(
    private val recipeReactionRepository: RecipeReactionRepository,
) : IRecipeService {

    @Transactional
    override fun create(reaction: RecipeReaction): RecipeReaction {
        TODO("Not yet implemented")
    }
}