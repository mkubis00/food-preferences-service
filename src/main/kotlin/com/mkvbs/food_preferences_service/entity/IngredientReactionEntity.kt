package com.mkvbs.food_preferences_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.ReadOnlyProperty
import java.time.Instant
import java.util.*

/**
 * Persistent entity representing a stored reaction of a user to a specific ingredient.
 *
 * This entity reflects how a reaction is stored in the database. It is used
 * exclusively in the persistence layer and should not contain business logic.
 *
 * Persistence details:
 * - `id` is generated automatically when the entity is first saved.
 * - `userId`, `ingredientId` and `isLiked` are read-only once persisted and reflect
 *   the state of the reaction at the moment it was created.
 * - `reactedAt` is automatically filled with the timestamp of the first insert,
 *   using Spring Data auditing (`@CreatedDate`).
 *
 * @property id
 *   Unique identifier of the reaction entry. Generated automatically unless provided.
 *
 * @property userId
 *   Identifier of the user who submitted the reaction. Marked as read-only because
 *   once a reaction is created, it is immutable with respect to the user.
 *
 * @property ingredientId
 *   Identifier of the ingredient being evaluated. Also read-only after creation.
 *
 * @property isLiked
 *   Indicates whether the ingredient was liked (`true`) or disliked (`false`)
 *   at the moment of reaction creation. Read-only to preserve historical integrity.
 *
 * @property reactedAt
 *   Timestamp indicating when the reaction was initially stored in the database.
 *   Automatically set on entity creation and not meant to be updated manually.
 *   Internal field, used for service-level metadata only.
 */
@Entity(name = "ingredient_reaction")
data class IngredientReactionEntity(
    @Id
    @GeneratedValue
    val id: UUID? = UUID.randomUUID(),

    @ReadOnlyProperty
    val userId: UUID,

    @ReadOnlyProperty
    val ingredientId: UUID,

    @ReadOnlyProperty
    val isLiked: Boolean,

    @CreatedDate
    @ReadOnlyProperty
    val reactedAt: Instant?
)
