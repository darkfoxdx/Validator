package com.projecteugene.validator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.projecteugene.validator.util.ValidateUnit
import com.projecteugene.validator.util.ValidateViewObserver
import com.projecteugene.validator.util.Validator
import com.projecteugene.validator.util.livedata.ValidateLiveData
import com.projecteugene.validator.util.textwatcher.ValidateTextInputLayoutWatcher

/**
 * Created by Eugene Low
 */
class ValidateButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatButton(context, attrs, defStyleAttr) {

    private var validateLiveData: ValidateLiveData = ValidateLiveData()
    private val observer = ValidateViewObserver(this)

    init {
        validateLiveData.observeForever(observer)
    }

    fun addWatcher(textInputLayout: TextInputLayout?, vararg validators: Validator): ValidateButton {
        validateLiveData.andWatchThat(textInputLayout, *validators)
        return this
    }

    override fun onDetachedFromWindow() {
        validateLiveData.removeObserver(observer)
        super.onDetachedFromWindow()
    }
}