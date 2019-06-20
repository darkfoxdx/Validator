package com.projecteugene.validator.util.textwatcher

import android.widget.EditText
import com.projecteugene.validator.Validate
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