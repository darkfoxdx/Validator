package com.projecteugene.validator.validator

import android.widget.EditText
import com.projecteugene.validator.util.Validator
import com.projecteugene.validator.util.ValidatorUtil

/**
 * Created by Eugene Low
 */
class IsNric(errorMessage: String?): Validator(errorMessage) {
    override fun check(textValue: String): Boolean {
        return ValidatorUtil.validateIsNric(textValue, errorMessage)
    }
}