package com.mkvbs.food_preferences_service.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*


@Entity(name = "recipe_reaction")
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["userId", "recipeId"])])
@EntityListeners(AuditingEntityListener::class)
class RecipeReactionEntity(
    @Id @GeneratedValue val id: UUID?,
    val userId: UUID,
    val recipeId: UUID,
    var isLiked: Boolean,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var reactedAt: Instant?,
)