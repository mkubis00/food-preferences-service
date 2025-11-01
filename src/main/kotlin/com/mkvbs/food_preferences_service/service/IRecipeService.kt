package com.mkvbs.food_preferences_service.service

import com.mkvbs.food_preferences_service.domain.RecipeReaction

interface IRecipeService {
    fun create(reaction: RecipeReaction): RecipeReaction
}