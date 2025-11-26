package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import com.mkvbs.food_preferences_service.exception.AlreadyExistException
import com.mkvbs.food_preferences_service.repository.IngredientReactionRepository
import com.mkvbs.food_preferences_service.utils.ingredient.toDomain
import com.mkvbs.food_preferences_service.utils.ingredient.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class IngredientReactionServiceImpl(
    private val ingredientReactionRepo: IngredientReactionRepository,
) : IIngredientReactionService {

    override fun getNegativeReactionsForUser(userId: UUID): List<IngredientReaction> {
        return ingredientReactionRepo
            .findByUserIdAndIsLikedFalse(userId)
            .map { it.toDomain() }
    }

    @Transactional
    override fun createIngredientReaction(newIngredientReaction: IngredientReaction): IngredientReaction {
        val isReactionExists = ingredientReactionRepo.existsByUserIdAndIngredientId(
            newIngredientReaction.ingredientId,
            newIngredientReaction.ingredientId
        )
        if (isReactionExists) throw AlreadyExistException("Ingredient reaction for user ${newIngredientReaction.userId} with ingredient ${newIngredientReaction.ingredientId} already exists")
        println(newIngredientReaction.toEntity())
        val savedReaction = ingredientReactionRepo.save(newIngredientReaction.toEntity())
        return savedReaction.toDomain()
    }

    @Transactional
    override fun deleteIngredientReactionsByIds(ids: List<UUID>) {
        ingredientReactionRepo.deleteAllById(ids)
    }

    override fun deleteIngredientReactionById(id: UUID) {
        ingredientReactionRepo.deleteById(id)
    }
}