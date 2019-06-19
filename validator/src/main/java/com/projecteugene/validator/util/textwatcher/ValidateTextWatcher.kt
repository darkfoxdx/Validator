package com.projecteugene.validator.util.textwatcher

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import com.projecteugene.validator.util.Validate
import com.projecteugene.validator.util.ValidateUnit
import com.projecteugene.validator.util.Validator

/**
 * Created by Eugene Low
 */
abstract class ValidateTextWatcher : TextWatcher {
    val liveData = MutableLiveData<ValidateUnit>()
    protected lateinit var unit: ValidateUnit
    abstract fun update()

    override fun afterTextChanged(s: Editable?) {
        update()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}