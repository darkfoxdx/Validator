package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class IsLengthLessOrEqual(errorMessage: String?, private val max: Int): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require(textValue.length <= max) { ErrorCode.LENGTH_MORE_THAN_AMOUNT }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}