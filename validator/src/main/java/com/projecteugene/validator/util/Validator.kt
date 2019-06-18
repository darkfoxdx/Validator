package com.projecteugene.validator.util

/**
 * Created by Eugene Low
 */
abstract class Validator(val errorMessage: String?) {
    @Throws(ValidateException::class)
    abstract fun check(textValue: String): Boolean
}