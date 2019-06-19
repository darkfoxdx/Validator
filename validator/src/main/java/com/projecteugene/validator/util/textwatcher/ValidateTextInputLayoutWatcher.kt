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
class ValidateTextInputLayoutWatcher(private val isLive: Boolean,
                                     private val textInputLayout: TextInputLayout?,
                                     private vararg val validators: Validator) : ValidateTextWatcher() {
    override fun update() {
        unit = if (isLive) {
            Validate.showThat(textInputLayout, *validators)
        } else {
            Validate.that(textInputLayout, *validators)
        }
        liveData.value = unit
    }
}