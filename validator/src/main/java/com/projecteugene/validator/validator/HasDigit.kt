package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class HasDigit(errorMessage: String?): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require(textValue.any { it in "1234567890" }) { ErrorCode.NO_DIGITS }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}