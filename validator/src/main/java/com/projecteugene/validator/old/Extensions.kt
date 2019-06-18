package com.projecteugene.validator.old

import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.projecteugene.validator.util.ValidateException

/**
 * Created by Eugene Low
 */
//fun EditText.validate(tag: String = "", condition: (String)-> Boolean): Boolean {
//    return try {
//        condition(this.text?.toString()?:"")
//        (this.context as? ValidatorListener)?.onValidatorSuccess(this, tag)
//        true
//    } catch (exception: ValidateException) {
//        (this.context as? ValidatorListener)?.onValidatorError(this, exception, tag)
//        false
//    }
//}
//
//fun TextInputLayout.validate(tag: String = "", condition: (String)-> Boolean): Boolean {
//    return try {
//        condition(this.editText?.text?.toString()?:"")
//        (this.context as? ValidatorListener)?.onValidatorSuccess(this, tag)
//        true
//    } catch (exception: ValidateException) {
//        (this.context as? ValidatorListener)?.onValidatorError(this, exception, tag)
//        false
//    }
//}
//
//fun TextInputLayout.validate(fragment: Fragment, tag: String = "", condition: (String)-> Boolean): Boolean {
//    return try {
//        condition(this.editText?.text?.toString()?:"")
//        (fragment as? ValidatorListener)?.onValidatorSuccess(this, tag)
//        true
//    } catch (exception: ValidateException) {
//        (fragment as? ValidatorListener)?.onValidatorError(this, exception, tag)
//        false
//    }
//}