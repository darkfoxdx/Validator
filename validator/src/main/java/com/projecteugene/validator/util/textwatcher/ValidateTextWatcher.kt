package com.projecteugene.validator.util.textwatcher

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import com.projecteugene.validator.util.ValidateUnit

/**
 * Created by Eugene Low
 */
abstract class ValidateTextWatcher : TextWatcher {
    val liveData = MutableLiveData<ValidateUnit>()
    protected lateinit var unit: ValidateUnit
    abstract fun update()
    init {
        liveData.value = ValidateUnit(false)
    }

    override fun afterTextChanged(s: Editable?) {
        update()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}