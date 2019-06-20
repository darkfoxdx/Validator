package com.projecteugene.validator

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.projecteugene.validator.util.Validator
import com.projecteugene.validator.util.textwatcher.ValidateTextInputLayoutWatcher

/**
 * Created by Eugene Low
 */
class ValidateTextInputLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    TextInputLayout(context, attrs, defStyleAttr) {

    private val validators: ArrayList<Validator> = ArrayList()
    private val validatorsArray: Array<out Validator>
        get() = validators.toTypedArray()
    private var textWatcher: ValidateTextInputLayoutWatcher? = null

    private fun configureValidationTextWatcher() {
        editText?.removeTextChangedListener(textWatcher)
        textWatcher = ValidateTextInputLayoutWatcher(true, this, *validatorsArray)
        editText?.addTextChangedListener(textWatcher)
    }

    val isValid: Boolean
        get() = Validate.that(this, *validatorsArray).result

    fun addValidators(vararg values: Validator) {
        this.validators.addAll(values)
        configureValidationTextWatcher()
    }

    fun removeValidators(vararg values: Validator) {
        this.validators.removeAll(values)
        configureValidationTextWatcher()
    }

    fun setHasLiveValidation(value: Boolean) {
        if (value) {
            configureValidationTextWatcher()
        } else {
            editText?.removeTextChangedListener(textWatcher)
        }
    }
}