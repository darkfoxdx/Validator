package com.projecteugene.validator.util.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.projecteugene.validator.util.ValidateUnit

/**
 * Created by Eugene Low
 */
class ValidateLiveData: MediatorLiveData<ValidateUnit>() {
    val map: MutableMap<LiveData<ValidateUnit>, Boolean> = HashMap()
    val result: ValidateUnit
        get() {
            var total = true
            for(item in map) {
                total = total && item.value
            }
            return ValidateUnit(total)
        }

}