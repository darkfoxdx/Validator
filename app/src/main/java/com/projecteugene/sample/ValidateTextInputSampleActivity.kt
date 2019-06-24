package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.projecteugene.validator.Validate
import com.projecteugene.validator.andWatchThat
import com.projecteugene.validator.validator.*
import kotlinx.android.synthetic.main.activity_validate_textinput_sample.*

class ValidateTextInputSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_textinput_sample)

        validateTextInputLayout.addValidators(IsEqualLength("Must be 4 digits", 4))
        validateTextInputLayout.setHasLiveValidation(true)
        validateTextInputLayout.addValidators(
            IsMoreOrEqual("Must be more than 10", 11),
            IsLessOrEqual("Must be less than 2000", 1999))
        validateTextInputLayout.setHasLiveValidation(false)

        button.setOnClickListener {
            if (validateTextInputLayout.isValid) {
                Toast.makeText(this, "Validated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
