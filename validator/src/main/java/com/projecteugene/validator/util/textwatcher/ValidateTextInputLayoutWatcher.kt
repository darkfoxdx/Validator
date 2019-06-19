package com.projecteugene.validator.util.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import com.projecteugene.validator.util.Validate
import com.projecteugene.validator.util.ValidateUnit
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class ValidateTextInputLayoutWatcher(private val textInputLayout: TextInputLayout?,
                                     private vararg val validators: Validator) : ValidateTextWatcher() {
    override fun update() {
        unit = Validate.showThat(textInputLayout, *validators)
        liveData.value = unit
    }
}