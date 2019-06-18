package com.projecteugene.validator

import com.projecteugene.validator.util.Validate
import com.projecteugene.validator.util.andThat
import com.projecteugene.validator.validator.IsNric
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ValidateUnitTest {
    @Test
    fun validate_that_isNric_true() {
        val actual = Validate.that("900918105767", IsNric(null))
        val expected = true
        assertEquals(actual, expected)
    }

    @Test
    fun validate_that_isNric_false() {
        val actual = Validate.that("90091810576", IsNric(null))
        val expected = false
        assertEquals(actual, expected)
    }

    @Test
    fun validate_that_isNricComposite_true() {
        val actual = Validate
            .that(      "900918105767", IsNric(null))
            .andThat(   "101010101010", IsNric(null))
        val expected = true
        assertEquals(actual, expected)
    }

    @Test
    fun validate_that_isNricComposite_false() {
        val actual = Validate
            .that(  "900918105767", IsNric(null))
            .andThat(   "0", IsNric(null))
        val expected = false
        assertEquals(actual, expected)
    }
}
