package com.projecteugene.validator.nric

import android.os.Parcel
import com.projecteugene.parcelable.KParcelable
import com.projecteugene.parcelable.parcelableCreator
import com.projecteugene.parcelable.readEnum
import com.projecteugene.parcelable.writeEnum
import com.projecteugene.validator.nric.NRICConstant.Country
import com.projecteugene.validator.nric.NRICConstant.State
import com.projecteugene.validator.nric.NRICConstant.Gender
import java.util.*

/**
 * Created by Eugene Low
 */
data class NRIC(val number: String,
                var date: Date? = null,
                var country: Country? = null,
                var state: State? = null,
                var gender: Gender? = null): KParcelable {
    private constructor(p: Parcel) : this(
            p.readString()?:"",
            p.readSerializable() as Date?,
            p.readEnum<Country>(),
            p.readEnum<State>(),
            p.readEnum<Gender>())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(number)
        dest.writeSerializable(date)
        dest.writeEnum(country)
        dest.writeEnum(state)
        dest.writeEnum(gender)
    }
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::NRIC)
    }
}
