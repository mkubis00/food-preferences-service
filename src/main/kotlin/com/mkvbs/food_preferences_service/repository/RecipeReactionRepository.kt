package com.mkvbs.food_preferences_service.repository

import com.mkvbs.food_preferences_service.entity.RecipeReactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RecipeReactionRepository : JpaRepository<RecipeReactionEntity, UUID> {
    fun findByUserIdAndRecipeId(userId: UUID, recipeId: UUID): Optional<RecipeReactionEntity>
    fun deleteByUserId(userId: UUID)
}