package com.projecteugene.validator.nric

/**
 * Created by Eugene Low
 */
object NRICConstant {
    const val LENGTH = 12
    const val YOUNGEST_AGE = 12
    const val DATE_FORMAT = "yyMMdd"
    const val DOB_START = 0
    const val DOB_END = 6
    const val STATE_START = 6
    const val STATE_END = 8

    enum class Gender {
        MALE, FEMALE
    }
    enum class State(vararg value: Int) {
        NONE(0, 17, 18, 19, 20, 69, 70, 73, 80, 81, 94, 95, 96, 97),
        JOHOR(1, 21, 22, 23, 24),
        KEDAH(2, 25, 26, 27),
        KELANTAN(3, 28, 29),
        MELAKA(4, 30),
        NEGERI_SEMBILAN(5, 31, 59),
        PAHANG(6, 32, 33),
        PULAU_PINANG(7, 34, 35),
        PERAK(8, 36, 37, 38, 39),
        PERLIS(9, 40),
        SELANGOR(10, 41, 42, 43, 44),
        TERENGGANU(11, 45, 46),
        SABAH(12, 47, 48, 49),
        SARAWAK(13, 50, 51, 52, 53),
        KUALA_LUMPUR(14, 54, 55, 56, 57),
        LABUAN(15, 58),
        PUTRAJAYA(16),
        UNKNOWN(82),
        OTHER(60, 61, 62, 63, 64, 65, 66, 67, 69, 71, 72, 74, 75,
                76, 77, 78, 79, 83, 84, 85, 86, 87, 88, 89, 90,
                91, 92, 93, 98, 99);
        val code: IntArray = value
    }

    enum class Country(vararg value: Int) {
        NONE(0, 17, 18, 19, 20, 69, 70, 73, 80, 81, 94, 95, 96, 97),
        MALAYSIA(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 21, 22, 23, 24, 25,
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
                47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 82),
        BRUNEI(60),
        INDONESIA(61),
        CAMBODIA(62),
        LAOS(63),
        MYANMAR(64),
        PHILIPPINES(65),
        SINGAPORE(66),
        THAILAND(67),
        VIETNAM(68),
        OVERSEA(71, 72),
        CHINA(74),
        INDIA(75),
        PAKISTAN(76),
        SAUDI_ARABIA(77),
        SRI_LANKA(78),
        BANGLADESH(79),
        OTHER(83, 84, 85, 86, 87, 88, 89, 90,
                91, 92, 93, 98, 99),
        STATELESS(98);
        val code: IntArray = value
    }
}
