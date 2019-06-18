package com.projecteugene.validator.util

import com.projecteugene.validator.nric.NRICConstant
import com.projecteugene.validator.config.LibConfig
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by Eugene Low
 */

object ValidatorUtil {
    @Throws(ValidateException::class)
    fun validateIsNric(text: String, message: String? = null): Boolean {
        validateIsNotEmpty(text, message)
        validateIsEqualLength(text, NRICConstant.LENGTH, message)
        validateIsNumeric(text, message)
        try {
            val countryCode = text.substring(NRICConstant.STATE_START, NRICConstant.STATE_END).toInt()
            require(countryCode !in NRICConstant.State.NONE.code) { ErrorCode.INVALID_CODE }
            val date = text.substring(NRICConstant.DOB_START, NRICConstant.DOB_END)
            val formatter = SimpleDateFormat(NRICConstant.DATE_FORMAT, LibConfig.locale)
            formatter.isLenient = false
            formatter.parse(date)
        } catch (error: ParseException) {
            throw ValidateException(ErrorCode.INVALID_DATE, message)
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateEmail(text: String, message: String? = null): Boolean {
        try {
            require(android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()) { ErrorCode.NOT_EMAIL }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateIsNotEmpty(text: String, message: String? = null): Boolean {
        try {
            require(text.isNotEmpty()) { ErrorCode.IS_EMPTY }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateIsEqual(text: String, compare: String?, message: String? = null): Boolean {
        try {
            require(text == compare) { ErrorCode.NOT_EQUAL }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateIsEqualLength(text: String, length: Int, message: String? = null): Boolean {
        try {
            require(text.length == length) { ErrorCode.NOT_EQUAL_LENGTH }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateIsLessOrEqual(amount:Int, max: Int, message: String? = null): Boolean {
        try {
            require(amount <= max) { ErrorCode.MORE_THAN_AMOUNT }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateIsMoreOrEqual(amount:Int, min: Int, message: String? = null): Boolean {
        try {
            require(amount >= min) { ErrorCode.LESS_THAN_AMOUNT }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateIsNumeric(text: String, message: String? = null): Boolean {
        try {
            require(text.toLongOrNull() != null) { ErrorCode.NOT_NUMERIC }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateHasDigit(text: String, message: String? = null): Boolean {
        try {
            require(text.any { it in "1234567890" }) { ErrorCode.NO_DIGITS }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }

    @Throws(ValidateException::class)
    fun validateHasUppercase(text: String, message: String? = null): Boolean {
        try {
            require(text != text.toLowerCase()) { ErrorCode.NO_UPPERCASE }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }
    @Throws(ValidateException::class)
    fun validateHasLowercase(text: String, message: String? = null): Boolean {
        try {
            require(text != text.toUpperCase()) { ErrorCode.NO_UPPERCASE }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }
    @Throws(ValidateException::class)
    fun validateHasSpecialCharacter(text: String, message: String? = null): Boolean {
        try {
            require(!"[A-Za-z0-9 ]*".toRegex().matches(text)) { ErrorCode.NO_SPECIAL_CHARACTER }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }
    @Throws(ValidateException::class)
    fun validateIsMalaysianPhone(text: String, message: String? = null): Boolean {
        try {
            require("^(\\+?6?01)[0-9]-*[1-9][0-9]{6,7}\$".toRegex().matches(text)) { ErrorCode.NOT_MALAYSIAN_PHONE_NUMBER }
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, message)
        }
        return true
    }
}