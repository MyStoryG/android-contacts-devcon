package devcon.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import devcon.learn.contacts.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resgister)

        val btnMore = findViewById<Button>(R.id.btnMore)
        val editText4 = findViewById<EditText>(R.id.textInputBirthday)
        val editText5 = findViewById<EditText>(R.id.textInputGender)
        val editText6 = findViewById<EditText>(R.id.textInputMemo)

        btnMore.setOnClickListener {
            editText4.visibility = View.VISIBLE
            editText5.visibility = View.VISIBLE
            editText6.visibility = View.VISIBLE
            btnMore.visibility = View.GONE
        }
    }
}
