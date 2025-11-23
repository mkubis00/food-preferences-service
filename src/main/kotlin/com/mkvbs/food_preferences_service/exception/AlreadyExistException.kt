package com.mkvbs.food_preferences_service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class AlreadyExistException(msg: String) : RuntimeException(msg)