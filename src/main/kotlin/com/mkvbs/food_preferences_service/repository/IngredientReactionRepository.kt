package com.mkvbs.food_preferences_service.repository

import com.mkvbs.food_preferences_service.entity.IngredientReactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IngredientReactionRepository : JpaRepository<IngredientReactionEntity, UUID> {

    fun findByUserIdAndIsLikedFalse(userId: UUID): List<IngredientReactionEntity>
    fun existsByUserIdAndIngredientId(userId: UUID, ingredientId: UUID): Boolean
}
