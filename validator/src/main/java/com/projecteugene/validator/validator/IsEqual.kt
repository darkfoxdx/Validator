package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class IsEqual(errorMessage: String?, private val compare: String): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require(textValue == compare) { ErrorCode.NOT_EQUAL }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}