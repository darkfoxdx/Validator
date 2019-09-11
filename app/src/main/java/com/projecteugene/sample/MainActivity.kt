package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.projecteugene.validator.*
import com.projecteugene.validator.validator.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        button.setOnClickListener {
//            Validate
//                .showFocusThat(textInputLayout, IsMalaysianNric("Not a NRIC"))
//                .andShowFocusThat(textInputLayout2,
//                    HasDigit("Must have one digit"))
//                .andShowFocusThat(textInputLayout3,
//                    IsNotEmpty("Must not be empty"))
//        }

        button.setOnClickListener {
            val validate = Validate
                .that(textInputLayout, IsMalaysianNric("Not a NRIC"))
                .andThat(textInputLayout2,
                    HasDigit("Must have one digit"))
                .andThat(textInputLayout3,
                    IsNotEmpty("Must not be empty"))
            (it as? Button)?.text = validate.errorMessage
        }
    }
}
