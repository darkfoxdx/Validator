package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class IsMoreOrEqual(errorMessage: String?, private val min: Int): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        IsNumeric(errorMessage).check(textValue)
        try {
            require(textValue.toLong() >= min) { ErrorCode.LESS_THAN_AMOUNT }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}