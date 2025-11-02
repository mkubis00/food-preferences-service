package com.mkvbs.food_preferences_service.entity

import jakarta.persistence.*
import java.util.*

@Entity(name = "ingredient_reaction")
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["userId", "ingredientId"])])
class IngredientReactionEntity(
    @Id @GeneratedValue val id: UUID?,
    val userId: UUID,
    val ingredientId: UUID,
    var isLiked: Boolean,
)