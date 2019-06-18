package com.projecteugene.validator.util

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Eugene Low
 */
object Validate {
    fun that(textValue: String, validator: Validator): Boolean {
        return try {
            validator.check(textValue)
        } catch (error: ValidateException) {
            false
        }
    }
    fun that(editText: EditText?, validator: Validator): Boolean {
        return that(editText?.text.toString(), validator)
    }
    fun that(textInputLayout: TextInputLayout?, validator: Validator): Boolean {
        return that(textInputLayout?.editText, validator)
    }
}

fun Boolean.andThat(textValue: String, validator: Validator): Boolean {
    return this && Validate.that(textValue, validator)
}
fun Boolean.andThat(editText: EditText?, validator: Validator): Boolean {
    return this && Validate.that(editText, validator)
}
fun Boolean.andThat(textInputLayout: TextInputLayout?, validator: Validator): Boolean {
    return this && Validate.that(textInputLayout, validator)
}