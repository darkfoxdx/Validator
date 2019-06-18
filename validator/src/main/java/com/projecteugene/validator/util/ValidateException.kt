package com.projecteugene.validator.util

/**
 * Created by Eugene Low
 */
class ValidateException(errorCode: String?, val errorMessage: String? = null):
        Exception(errorCode) {
        constructor(errorCode: ErrorCode?, errorMessage: String? = null): this(errorCode?.name, errorMessage)
}