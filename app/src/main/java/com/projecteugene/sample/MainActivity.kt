package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.projecteugene.validator.util.Validate
import com.projecteugene.validator.util.andShowThat
import com.projecteugene.validator.util.andWatchThat
import com.projecteugene.validator.validator.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val validateUnit = Validate
            .watchThat(textInputLayout, IsMalaysianNric("Not a NRIC"))
            .andWatchThat(textInputLayout2,
                HasUppercase("Must have one upper case character"),
                HasSpecialCharacter("Must have one special character"),
                HasDigit("Must have one digit"))

        validateUnit.observeForever {
            button.text = it.result.toString()
        }

        button.setOnClickListener {
            if (validateUnit.value?.result == true) {
                Toast.makeText(this, "Validated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
