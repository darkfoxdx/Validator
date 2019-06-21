package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.projecteugene.validator.Validate
import com.projecteugene.validator.andShowFocusThat
import com.projecteugene.validator.andShowThat
import com.projecteugene.validator.andWatchThat
import com.projecteugene.validator.validator.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Validate
                .showFocusThat(textInputLayout, IsMalaysianNric("Not a NRIC"))
                .andShowFocusThat(textInputLayout2,
                    HasDigit("Must have one digit"))
                .andShowFocusThat(textInputLayout3,
                    IsNotEmpty("Must not be empty"))
        }
    }
}
