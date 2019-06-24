package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.projecteugene.validator.Validate
import com.projecteugene.validator.andWatchThat
import com.projecteugene.validator.validator.*
import kotlinx.android.synthetic.main.activity_validate_button_sample.*

class ValidateButtonSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_button_sample)

        validateButton.addWatcher(textInputLayout, IsMalaysianNric("Not a NRIC"))
            .addWatcher(textInputLayout2,
                HasUppercase("Must have one upper case character"),
                HasSpecialCharacter("Must have one special character"),
                HasDigit("Must have one digit"))
    }
}
