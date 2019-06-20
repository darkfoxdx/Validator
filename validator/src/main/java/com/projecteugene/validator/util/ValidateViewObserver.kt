package com.projecteugene.validator.util

import android.view.View
import androidx.lifecycle.Observer

/**
 * Created by Eugene Low
 */
class ValidateViewObserver(private val view: View): Observer<ValidateUnit> {
    override fun onChanged(t: ValidateUnit) {
        view.isEnabled = t.result
    }
}