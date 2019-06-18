package com.projecteugene.validator.old

import android.view.View
import com.projecteugene.validator.util.ValidateException

/**
 * Created by Eugene Low
 */

interface ValidatorListener {
    fun onValidatorSuccess(layout: View, tag: String = "")
    fun onValidatorError(layout: View, error: ValidateException, tag: String = "")
}