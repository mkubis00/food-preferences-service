package com.mkvbs.food_preferences_service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class NoResourceFoundException(message: String) : RuntimeException(message)