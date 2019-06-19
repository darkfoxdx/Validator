package com.projecteugene.validator.util.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.projecteugene.validator.util.Validate
import com.projecteugene.validator.util.ValidateUnit
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
class ValidateEditTextWatcher(private val isLive: Boolean,
                              private val editText: EditText?,
                              private vararg val validators: Validator) : ValidateTextWatcher() {
    override fun update() {
        unit = if (isLive) {
            Validate.showThat(editText, *validators)
        } else {
            Validate.that(editText, *validators)
        }
        liveData.value = unit
    }
}