package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.repository.IngredientReactionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class IngredientReactionServiceImpl(
    val repository: IngredientReactionRepository
) : IIngredientReactionService {

    @Transactional
    override fun create(reaction: IngredientReaction): IngredientReaction {
        TODO("Not yet implemented")
    }
}