package com.projecteugene.validator.util

/**
 * Created by Eugene Low
 */
abstract class Validator(val errorMessage: String?) {
    abstract fun check(textValue: String): Boolean
}