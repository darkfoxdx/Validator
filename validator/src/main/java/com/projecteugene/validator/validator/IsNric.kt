package com.projecteugene.validator.validator

import com.projecteugene.validator.config.LibConfig
import com.projecteugene.validator.nric.NRICConstant
import com.projecteugene.validator.util.ErrorCode
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.Validator
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by Eugene Low
 */
class IsNric(errorMessage: String?): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        IsNotEmpty(errorMessage).check(textValue)
        IsEqualLength(errorMessage, NRICConstant.LENGTH).check(textValue)
        IsNumeric(errorMessage).check(textValue)
        try {
            val countryCode = textValue.substring(NRICConstant.STATE_START, NRICConstant.STATE_END).toInt()
            require(countryCode !in NRICConstant.State.NONE.code) { ErrorCode.INVALID_CODE }
            val date = textValue.substring(NRICConstant.DOB_START, NRICConstant.DOB_END)
            val formatter = SimpleDateFormat(NRICConstant.DATE_FORMAT, LibConfig.locale)
            formatter.isLenient = false
            formatter.parse(date)
        } catch (error: ParseException) {
            throw ValidateException(ErrorCode.INVALID_DATE, errorMessage)
        } catch (error: IllegalArgumentException) {
            throw ValidateException(error.message, errorMessage)
        }
        return true
    }
}