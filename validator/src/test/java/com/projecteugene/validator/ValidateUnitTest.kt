package com.projecteugene.validator

import com.projecteugene.validator.validator.*
import org.junit.Test

import org.junit.Assert.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ValidateUnitTest {
    @Test
    fun validate_that_isNric_true() {
        val actual = Validate.that("900918105767", IsMalaysianNric(null))
        val expected = true
        assertEquals(expected, actual.result)
    }

    @Test
    fun validate_that_isNric_false() {
        val actual = Validate.that("90091810576", IsMalaysianNric(null))
        val expected = false
        assertEquals(expected, actual.result)
    }

    @Test
    fun validate_that_isNricComposite_true() {
        val actual = Validate
            .that(      "900918105767", IsMalaysianNric(null))
            .andThat(   "101010101010", IsMalaysianNric(null))
        val expected = true
        assertEquals(expected, actual.result)
    }

    @Test
    fun validate_that_isNricComposite_false() {
        val actual = Validate
            .that(  "900918105767", IsMalaysianNric(null))
            .andThat(   "0", IsMalaysianNric(null))
        val expected = false
        assertEquals(expected, actual.result)
    }

    @Test
    fun validate_that_compositeThat_true() {
        val actual = Validate
            .that(  "Password12345!",
                IsNotEmpty(null),
                HasDigit(null),
                HasLowercase(null),
                HasSpecialCharacter(null),
                HasUppercase(null),
                IsEqualLength(null, 14))
        val expected = true
        assertEquals(expected, actual.result)
    }

    @Test
    fun validate_that_compositeThat_false() {
        val actual = Validate
            .that(  "password12345!",
                IsNotEmpty(null),
                HasDigit(null),
                HasLowercase(null),
                HasSpecialCharacter(null),
                HasUppercase(null),
                IsEqualLength(null, 14))
        val expected = false
        assertEquals(expected, actual.result)
    }

    @Test
    fun validate_that_compositeThatAnd_true() {
        val actual = Validate
            .that("Password12345!",
                IsNotEmpty(null),
                HasDigit(null),
                HasLowercase(null),
                HasSpecialCharacter(null),
                HasUppercase(null),
                IsEqualLength(null, 14))
            .andThat("greaT000&Jwi4$",
                IsNotEmpty(null),
                HasDigit(null),
                HasLowercase(null),
                HasSpecialCharacter(null),
                HasUppercase(null),
                IsEqualLength(null, 14))
        val expected = true
        assertEquals(expected, actual.result)
    }

    @Test
    fun validate_that_compositeThatAnd_false() {
        val actual = Validate
            .that("Password12345!",
                IsNotEmpty(null),
                HasDigit(null),
                HasLowercase(null),
                HasSpecialCharacter(null),
                HasUppercase(null),
                IsEqualLength(null, 14))
            .andThat("badpassword",
                IsNotEmpty(null),
                HasDigit(null),
                HasLowercase(null),
                HasSpecialCharacter(null),
                HasUppercase(null),
                IsEqualLength(null, 14))
        val expected = false
        assertEquals(expected, actual.result)
    }
}
