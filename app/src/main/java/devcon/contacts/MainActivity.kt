package devcon.contacts

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import devcon.learn.contacts.R

class MainActivity : AppCompatActivity() {

    private val cancelButton by lazy { findViewById<Button>(R.id.cancelButton) }
    private val saveButton by lazy { findViewById<Button>(R.id.saveButton) }
    private val nameInputText by lazy { findViewById<TextInputEditText>(R.id.nameInputEditText) }
    private val contactInputText by lazy { findViewById<TextInputEditText>(R.id.contactInputEditText) }
    private val moreButton by lazy { findViewById<Button>(R.id.moreButton) }
    private val genderInputText by lazy { findViewById<TextInputEditText>(R.id.genderInputEditText) }
    private val genderSelection by lazy { findViewById<RadioGroup>(R.id.genderSelection) }
    private val optionalLayout by lazy { findViewById<LinearLayout>(R.id.optionalLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveButton.setOnClickListener { onSave() }
        cancelButton.setOnClickListener { toast("취소 되었습니다") }
        moreButton.setOnClickListener {
            moreButton.visibility = GONE
            optionalLayout.visibility = VISIBLE
        }

        genderSelection.setOnCheckedChangeListener { _, selected ->
            genderInputText.setText(findViewById<RadioButton>(selected).text)
        }
    }

    fun onSave() {
        val name = nameInputText.text.isNullOrEmpty()
        val contact = contactInputText.text.isNullOrEmpty()
        if (!name && !contact) {
            toast("저장이 완료 되었습니다")
        } else {
            toast("이름, 전화번호는 필수 항목입니다")
        }
    }

    fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}