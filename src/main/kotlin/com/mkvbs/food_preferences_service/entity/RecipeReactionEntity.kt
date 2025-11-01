package com.mkvbs.food_preferences_service.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "recipe_reaction")
class RecipeReactionEntity(
    @Id @GeneratedValue val id: UUID?,
    val userId: UUID,
    val recipeId: UUID,
    val isLiked: Boolean,

    @Column(updatable = false)
    val reactedAt: OffsetDateTime?,
)