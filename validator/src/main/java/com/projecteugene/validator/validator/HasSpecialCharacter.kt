package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class HasSpecialCharacter(errorMessage: String?): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require(!"[A-Za-z0-9 ]*".toRegex().matches(textValue)) { ErrorCode.NO_SPECIAL_CHARACTER }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}