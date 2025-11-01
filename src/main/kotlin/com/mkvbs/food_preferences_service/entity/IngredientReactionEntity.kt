package com.mkvbs.food_preferences_service.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.UUID

@Entity(name = "ingredient_reaction")
class IngredientReactionEntity(
    @Id @GeneratedValue val id: UUID?,
    val userId: UUID,
    val ingredientId: UUID,
    val isLiked: Boolean,

    @CreationTimestamp
    @Column(updatable = false)
    val reactedAt: LocalDateTime?,
)