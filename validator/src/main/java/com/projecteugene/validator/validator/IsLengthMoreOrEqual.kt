package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class IsLengthMoreOrEqual(errorMessage: String?, private val min: Int): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require(textValue.length >= min) { ErrorCode.LENGTH_LESS_THAN_AMOUNT }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}