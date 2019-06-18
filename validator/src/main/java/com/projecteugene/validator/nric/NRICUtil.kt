package com.projecteugene.validator.nric

import com.projecteugene.validator.nric.NRICConstant.Country
import com.projecteugene.validator.nric.NRICConstant.Gender
import com.projecteugene.validator.nric.NRICConstant.State
import com.projecteugene.validator.util.ValidatorUtil
import com.projecteugene.validator.config.LibConfig
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Eugene Low
 */
object NRICUtil {
    fun parse(number: String): NRIC {
        val nric = NRIC(number)
        try {
            ValidatorUtil.validateIsNric(number)
            nric.apply {
                date = parseDate(number)
                country = parseCountry(number)
                state = parseState(number)
                gender = parseGender(number)
            }
        } finally {
            return nric
        }
    }

    /**
     * MinYear would be current year minus age minus 99 so that 06 = 2006 in the year 2018
     * 07 = 1907 in the year 2018 but 07 = 2007 in the year 2019
     */
    private fun parseDate(number: String): Date? {
        return try {
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val minYear = currentYear - NRICConstant.YOUNGEST_AGE - 99
            val minDate = GregorianCalendar(minYear, Calendar.JANUARY, 1).time
            val date = number.substring(0, 6)
            val formatter = SimpleDateFormat(NRICConstant.DATE_FORMAT, LibConfig.locale)
            formatter.isLenient = false
            formatter.set2DigitYearStart(minDate)
            formatter.parse(date)
        } catch (exception: ParseException) {
            null
        }
    }

    private fun parseCountry(number: String): Country? {
        val countryCode = number.substring(6, 8).toInt()
        return enumValues<Country>().find { it.code.contains(countryCode) }
    }

    private fun parseState(number: String): State? {
        val stateCode = number.substring(6, 8).toInt()
        return enumValues<State>().find { it.code.contains(stateCode) }
    }

    private fun parseGender(number: String): Gender {
        val genderCode = number.substring(11).toInt()
        return if (genderCode % 2 == 0) Gender.FEMALE else Gender.MALE
    }
}