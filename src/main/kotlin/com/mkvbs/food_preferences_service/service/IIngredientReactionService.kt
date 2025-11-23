package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.IngredientReaction
import java.util.*

/**
 * Service interface for managing user reactions to ingredients.
 *
 * This interface defines the operations related to retrieving,adding and managing
 * reactions, such as whether a user likes or dislikes specific ingredients.
 */
interface IIngredientReactionService {

    /**
     * Retrieves all ingredients that a given user has marked as **not liked**.
     *
     * This function is **suspendable**, meaning it can be called from within a coroutine
     * without blocking the executing thread. It returns a list of reactions where
     * the user has indicated they do not like the ingredient.
     *
     * @param userId The unique identifier of the user whose unliked reactions are to be retrieved.
     * @return A list of [IngredientReaction] representing the ingredients the user dislikes.
     */
    fun getNegativeReactionsForUser(userId: UUID): List<IngredientReaction>

    /**
     * Creates a new reaction of a user to a specific ingredient.
     *
     * This method persists a new `IngredientReaction` that represents whether
     * the user likes or dislikes a given ingredient.
     * Before creating the reaction, a validation step is performed — a user
     * cannot create a reaction if a reaction for the same (user, ingredient)
     * pair already exists. If such a reaction is found, the method should
     * throw an exception or return an appropriate domain error.
     *
     * @param newIngredientReaction the reaction object to be created.
     * @return the newly created reaction persisted in the database.
     * @throws com.mkvbs.food_preferences_service.exception.AlreadyExistException if a reaction for the
     *         given user and ingredient already exists.
     */
    fun createIngredientReaction(newIngredientReaction: IngredientReaction): IngredientReaction

    /**
     * Deletes ingredient reactions with the given IDs.
     *
     * This function accepts a list of UUIDs representing ingredient reactions
     * and removes all corresponding records from the database.
     *
     * @param ids a list of UUIDs of the ingredient reactions to be deleted
     */
    fun deleteIngredientReactionByIds(ids: List<UUID>)
}
