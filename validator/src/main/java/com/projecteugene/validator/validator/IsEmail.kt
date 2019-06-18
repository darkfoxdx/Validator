package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class IsEmail(errorMessage: String?): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require(android.util.Patterns.EMAIL_ADDRESS.matcher(textValue).matches()) { ErrorCode.NOT_EMAIL }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}