package com.mkvbs.food_preferences_service

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition( // TO CHANGE
    info = Info(
        title = "Food Preferences Service",
        version = "1.0.0",
        description = "Food Preferences Service API which provides management of recipes reactions",
        contact = Contact(
            name = "Maciej Kubis",
            url = "https://github.com/mkvbs/recipe_service",
            email = "maciej.k-2000@outlook.com"
        ),
        license = License(
            name = "Open Source Licenses",
        )
    )
)
class FoodPreferencesServiceApplication

fun main(args: Array<String>) {
	runApplication<FoodPreferencesServiceApplication>(*args)
}
