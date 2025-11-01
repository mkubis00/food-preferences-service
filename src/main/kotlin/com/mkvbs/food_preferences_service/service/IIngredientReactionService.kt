package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.IngredientReaction

/**
 * Ingredient reaction service
 *
 * @constructor Create empty Ingredient reaction service
 */
interface IIngredientReactionService {

    /**
     * Create
     *
     * @param reaction
     * @return created IngredientReaction
     */
    fun create(reaction: IngredientReaction): IngredientReaction
}