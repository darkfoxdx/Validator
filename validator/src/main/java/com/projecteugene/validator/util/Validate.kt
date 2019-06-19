package com.projecteugene.validator.util

import android.widget.EditText
import androidx.lifecycle.MediatorLiveData
import com.google.android.material.textfield.TextInputLayout
import com.projecteugene.validator.util.livedata.ValidateLiveData
import com.projecteugene.validator.util.textwatcher.ValidateEditTextWatcher
import com.projecteugene.validator.util.textwatcher.ValidateTextInputLayoutWatcher
import com.projecteugene.validator.util.textwatcher.ValidateTextWatcher

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

    fun that(textValue: String, vararg validators: Validator): ValidateUnit {
        return try {
            execute(textValue, *validators)
            ValidateUnit(true)
        } catch (error: ValidateException) {
            ValidateUnit(false)
        }
    }
    fun that(editText: EditText?, vararg validators: Validator): ValidateUnit {
        return that(editText?.text.toString(), *validators)
    }
    fun that(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
        return that(textInputLayout?.editText, *validators)
    }

    fun showThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
        return try {
            execute(textInputLayout?.editText?.text.toString(), *validators)
            textInputLayout?.error = null
            ValidateUnit(true)
        } catch (error: ValidateException) {
            textInputLayout?.error = error.errorMessage
            ValidateUnit(false)
        }
    }

    fun showThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
        return try {
            execute(editText?.text.toString(), *validators)
            ValidateUnit(true)
        } catch (error: ValidateException) {
            editText?.error = error.errorMessage
            ValidateUnit(false)
        }
    }

    fun executeAddSource(validateLiveData: ValidateLiveData,
                         textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateLiveData {
        val textWatcher = ValidateTextInputLayoutWatcher(textInputLayout, *validators)
        textInputLayout?.editText?.addTextChangedListener(textWatcher)
        validateLiveData.addSource(textWatcher.liveData) {
            validateLiveData.map[textWatcher.liveData] = it.result
            validateLiveData.value = validateLiveData.result
        }
        return validateLiveData
    }

    fun executeAddSource(validateLiveData: ValidateLiveData, editText: EditText?, vararg validators: Validator): ValidateLiveData {
        val textWatcher = ValidateEditTextWatcher(editText, *validators)
        editText?.addTextChangedListener(textWatcher)
        validateLiveData.addSource(textWatcher.liveData) {
            validateLiveData.map[textWatcher.liveData] = it.result
            validateLiveData.value = validateLiveData.result
        }
        return validateLiveData
    }

    fun watchThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateLiveData {
        return executeAddSource(ValidateLiveData(), textInputLayout, *validators)
    }

    fun watchThat(editText: EditText?, vararg validators: Validator): ValidateLiveData {
        return executeAddSource(ValidateLiveData(), editText, *validators)
    }
}

fun ValidateUnit.andThat(textValue: String, vararg validators: Validator): ValidateUnit {
    val result = this.result && Validate.that(textValue, *validators).result
    return ValidateUnit(result)
}
fun ValidateUnit.andThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
    val result = this.result && Validate.that(editText, *validators).result
    return ValidateUnit(result)
}
fun ValidateUnit.andThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
    val result = this.result && Validate.that(textInputLayout, *validators).result
    return ValidateUnit(result)
}

fun ValidateUnit.andShowThat(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateUnit {
    val result = this.result && Validate.showThat(textInputLayout, *validators).result
    return ValidateUnit(result)
}
fun ValidateUnit.andShowThat(editText: EditText?, vararg validators: Validator): ValidateUnit {
    val result = this.result && Validate.showThat(editText, *validators).result
    return ValidateUnit(result)
}

fun ValidateLiveData.andWatchThat(textInputLayout: TextInputLayout?,
                                                vararg validators: Validator): MediatorLiveData<ValidateUnit> {
    return Validate.executeAddSource(this, textInputLayout, *validators)
}

fun ValidateLiveData.andWatchThat(editText: EditText?,
                                                vararg validators: Validator): MediatorLiveData<ValidateUnit> {
    return Validate.executeAddSource(this, editText, *validators)
}