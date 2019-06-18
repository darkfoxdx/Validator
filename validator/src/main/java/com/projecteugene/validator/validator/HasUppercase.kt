package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class HasUppercase(errorMessage: String?): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require(textValue != textValue.toLowerCase()) { ErrorCode.NO_UPPERCASE }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}