package com.mkvbs.food_preferences_service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ResourceIdNullException(message: String) : RuntimeException(message)