package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.RecipeReaction

/**
 * Recipe service
 *
 * @constructor Create empty Recipe service
 */
interface IRecipeService {
    /**
     * Create reaction for the recipe.
     * It is not possible to create reaction with user ID and recipe ID that exists together in already existing reaction.
     * Time of reaction is created in database during saving.
     *
     * @param reaction
     * @return recipe reaction
     */
    fun create(reaction: RecipeReaction): RecipeReaction
}