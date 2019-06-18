package com.projecteugene.validator.util

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Eugene Low
 */
object Validate {
    @Throws(ValidateException::class)
    private fun execute(textValue: String, vararg validators: Validator) {
        for (item in validators) {
            item.check(textValue)
        }
    }

    fun that(textValue: String, vararg validators: Validator): Boolean {
        return try {
            execute(textValue, *validators)
            true
        } catch (error: ValidateException) {
            false
        }
    }
    fun that(editText: EditText?, vararg validators: Validator): Boolean {
        return that(editText?.text.toString(), *validators)
    }
    fun that(textInputLayout: TextInputLayout?, vararg validators: Validator): Boolean {
        return that(textInputLayout?.editText, *validators)
    }

    fun showThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
        try {
            execute(textInputLayout?.editText?.text.toString(), *validators)
            textInputLayout?.error = null
        } catch (error: ValidateException) {
            textInputLayout?.error = error.errorMessage
        }
        return ValidateUnit()
    }

    fun showThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
        try {
            execute(editText?.text.toString(), *validators)
        } catch (error: ValidateException) {
            editText?.error = error.errorMessage
        }
        return ValidateUnit()
    }
}

fun Boolean.andThat(textValue: String, vararg validators: Validator): Boolean {
    return this && Validate.that(textValue, *validators)
}
fun Boolean.andThat(editText: EditText?, vararg validators: Validator): Boolean {
    return this && Validate.that(editText, *validators)
}
fun Boolean.andThat(textInputLayout: TextInputLayout?, vararg validators: Validator): Boolean {
    return this && Validate.that(textInputLayout, *validators)
}

fun ValidateUnit.andShowThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
    return Validate.showThat(textInputLayout, *validators)
}
fun ValidateUnit.andShowThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
    return Validate.showThat(editText, *validators)
}