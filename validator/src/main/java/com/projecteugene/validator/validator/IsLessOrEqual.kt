package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class IsLessOrEqual(errorMessage: String?, private val max: Int): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        IsNumeric(errorMessage).check(textValue)
        try {
            require(textValue.toLong() <= max) { ErrorCode.MORE_THAN_AMOUNT }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}