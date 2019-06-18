package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projecteugene.validator.util.Validate
import com.projecteugene.validator.util.andShowThat
import com.projecteugene.validator.validator.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Validate
                .showThat(textInputLayout, IsNric("Not a NRIC"))
                .andShowThat(textInputLayout2,
                    HasUppercase("Must have one upper case character"),
                    HasSpecialCharacter("Must have one special character"),
                    HasDigit("Must have one digit"))
        }
    }
}
