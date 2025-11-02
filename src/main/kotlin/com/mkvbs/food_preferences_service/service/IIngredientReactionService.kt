package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.IngredientReaction

/**
 * Ingredient reaction service
 *
 * @constructor Create empty Ingredient reaction service
 */
interface IIngredientReactionService {

    /**
     * Create ingredient reaction. It's not possible to create reaction with user and ingredient ids that already exists.
     * However, if reaction with same ids and different attitude to ingredient (like, dislike) will be sent,
     * isLiked attribute will be updated.
     *
     * @param reaction
     * @return created IngredientReaction or updated IngredientReaction or founded IngredientReaction
     *
     */
    fun create(reaction: IngredientReaction): IngredientReaction
}