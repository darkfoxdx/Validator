package com.projecteugene.validator

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.MediatorLiveData
import com.google.android.material.textfield.TextInputLayout
import com.projecteugene.validator.util.ValidateException
import com.projecteugene.validator.util.ValidateUnit
import com.projecteugene.validator.util.Validator
import com.projecteugene.validator.util.livedata.ValidateLiveData
import com.projecteugene.validator.util.textwatcher.ValidateEditTextWatcher
import com.projecteugene.validator.util.textwatcher.ValidateTextInputLayoutWatcher

/**
 * Created by Eugene Low
 */
object Validate {
    fun that(textValue: String, vararg validators: Validator): ValidateUnit {
        return try {
            execute(textValue, *validators)
            ValidateUnit(true)
        } catch (error: ValidateException) {
            ValidateUnit(false).apply {
                errorMessage = error.errorMessage
            }
        }
    }
    fun that(editText: EditText?, vararg validators: Validator): ValidateUnit {
        return that(editText?.text.toString(), *validators)
    }
    fun that(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
        return that(textInputLayout?.editText, *validators)
    }

    fun showThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
        return executeShowThat(false, textInputLayout, *validators)
    }

    fun showThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
        return executeShowThat(false, editText, *validators)
    }

    fun showFocusThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
        return executeShowThat(true, textInputLayout, *validators)
    }

    fun showFocusThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
        return executeShowThat(true, editText, *validators)
    }

    fun watchThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateLiveData {
        return executeAddSource(ValidateLiveData(), true, textInputLayout, *validators)
    }

    fun watchThat(editText: EditText?, vararg validators: Validator): ValidateLiveData {
        return executeAddSource(ValidateLiveData(), true, editText, *validators)
    }
}
@Throws(ValidateException::class)
private fun execute(textValue: String, vararg validators: Validator) {
    for (item in validators) {
        item.check(textValue)
    }
}

private fun executeShowThat(shouldFocus: Boolean,
                            textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
    return try {
        execute(textInputLayout?.editText?.text.toString(), *validators)
        textInputLayout?.error = null
        ValidateUnit(true)
    } catch (error: ValidateException) {
        textInputLayout?.error = error.errorMessage
        if (shouldFocus) textInputLayout?.editText?.requestFocus()
        ValidateUnit(false)
    }
}

private fun executeShowThat(shouldFocus: Boolean,
                            editText: EditText?, vararg validators: Validator): ValidateUnit {
    return try {
        execute(editText?.text.toString(), *validators)
        ValidateUnit(true)
    } catch (error: ValidateException) {
        editText?.error = error.errorMessage
        if (shouldFocus) editText?.requestFocus()
        ValidateUnit(false)
    }
}

private fun executeAddSource(validateLiveData: ValidateLiveData, isLive: Boolean, textInputLayout: TextInputLayout?,
                             vararg validators: Validator
): ValidateLiveData {
    val textWatcher = ValidateTextInputLayoutWatcher(isLive, textInputLayout, *validators)
    textInputLayout?.editText?.addTextChangedListener(textWatcher)
    validateLiveData.addSource(textWatcher.liveData) {
        validateLiveData.set.add(textWatcher.liveData)
        validateLiveData.value = validateLiveData.result
    }
    return validateLiveData
}

private fun executeAddSource(validateLiveData: ValidateLiveData, isLive: Boolean, editText: EditText?,
                             vararg validators: Validator
): ValidateLiveData {
    val textWatcher = ValidateEditTextWatcher(isLive, editText, *validators)
    editText?.addTextChangedListener(textWatcher)
    validateLiveData.addSource(textWatcher.liveData) {
        validateLiveData.set.add(textWatcher.liveData)
        validateLiveData.value = validateLiveData.result
    }
    return validateLiveData
}

fun ValidateUnit.andThat(textValue: String, vararg validators: Validator): ValidateUnit {
    val validate = Validate.that(textValue, *validators)
    val message = errorMessage?:validate.errorMessage
    return ValidateUnit(this.result && validate.result).apply {
        this.errorMessage = message
    }
}
fun ValidateUnit.andThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
    val validate = Validate.that(editText, *validators)
    val message = errorMessage?:validate.errorMessage
    return ValidateUnit(this.result && validate.result).apply {
        this.errorMessage = message
    }
}
fun ValidateUnit.andThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
    val validate = Validate.that(textInputLayout, *validators)
    val message = errorMessage?:validate.errorMessage
    return ValidateUnit(this.result && validate.result).apply {
        this.errorMessage = message
    }
}

fun ValidateUnit.andShowThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
    val result = executeShowThat(false, textInputLayout, *validators).result
    return ValidateUnit(this.result && result)
}
fun ValidateUnit.andShowThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
    val result = executeShowThat(false, editText, *validators).result
    return ValidateUnit(this.result && result)
}

fun ValidateUnit.andShowFocusThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
    val result = executeShowThat(this.result, textInputLayout, *validators).result
    return ValidateUnit(this.result && result)
}
fun ValidateUnit.andShowFocusThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
    val result = executeShowThat(this.result, editText, *validators).result
    return ValidateUnit(this.result && result)
}

fun ValidateLiveData.andWatchThat(textInputLayout: TextInputLayout?,
                                                vararg validators: Validator
): MediatorLiveData<ValidateUnit> {
    return executeAddSource(this, true, textInputLayout, *validators)
}

fun ValidateLiveData.andWatchThat(editText: EditText?,
                                                vararg validators: Validator
): MediatorLiveData<ValidateUnit> {
    return executeAddSource(this, true, editText, *validators)
}