package com.projecteugene.validator.validator

import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class IsMalaysianPhoneNumber(errorMessage: String?): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        try {
            require("^(\\+?6?01)[0-9]-*[1-9][0-9]{6,7}\$".toRegex().matches(textValue)) { ErrorCode.NOT_MALAYSIAN_PHONE_NUMBER }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}