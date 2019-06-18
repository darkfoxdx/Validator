package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class IsEqualLength(errorMessage: String?, private val length: Int): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require(textValue.length == length) { ErrorCode.NOT_EQUAL_LENGTH }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}