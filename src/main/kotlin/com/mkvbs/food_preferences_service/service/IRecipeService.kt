package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.RecipeReaction
import java.util.*

/**
 * Recipe service
 *
 * @constructor Create empty Recipe service
 */
interface IRecipeService {

    /**
     * Create reaction for the recipe. It is not possible to create new reaction with user ID
     * and recipe ID that exists together in already existing reactions. The attempt to create
     * reaction with already existing user and recipe ID could only override isLiked attribute.
     * Time of reaction is created by JPA.
     *
     * @param reaction
     * @return created/updated/already existing recipe reaction
     */
    fun create(reaction: RecipeReaction): RecipeReaction


    /**
     * Delete all recipe reaction for specific user.
     *
     * @param userId
     * @return Void
     */
    fun deleteAllByUser(userId: UUID)
}