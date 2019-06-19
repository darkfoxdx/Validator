package com.projecteugene.validator.util.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.projecteugene.validator.util.ValidateUnit

/**
 * Created by Eugene Low
 */
class ValidateLiveData: MediatorLiveData<ValidateUnit>() {
    val set: MutableSet<LiveData<ValidateUnit>> = HashSet()
    val result: ValidateUnit
        get() {
            var total = true
            for(item in set) {
                total = total && item.value?.result?:false
            }
            return ValidateUnit(total)
        }

}