package devcon.contacts.activity

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
import devcon.contacts.Contact
import devcon.contacts.objects.MyObjects.model
import devcon.learn.contacts.R

class RegisterActivity : AppCompatActivity() {

    private val cancelButton by lazy { findViewById<Button>(R.id.cancelButton) }
    private val saveButton by lazy { findViewById<Button>(R.id.saveButton) }
    private val nameInputText by lazy { findViewById<TextInputEditText>(R.id.nameInputEditText) }
    private val contactInputText by lazy { findViewById<TextInputEditText>(R.id.contactInputEditText) }
    private val mailInputText by lazy { findViewById<TextInputEditText>(R.id.mailInputEditText) }
    private val birthdayInputText by lazy { findViewById<TextInputEditText>(R.id.birthdayInputEditText) }
    private val memoInputText by lazy { findViewById<TextInputEditText>(R.id.memoInputEditText) }
    private val moreButton by lazy { findViewById<Button>(R.id.moreButton) }
    private val genderInputText by lazy { findViewById<TextInputEditText>(R.id.genderInputEditText) }
    private val genderSelection by lazy { findViewById<RadioGroup>(R.id.genderSelection) }
    private val optionalLayout by lazy { findViewById<LinearLayout>(R.id.optionalLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        saveButton.setOnClickListener { onSave() }
        cancelButton.setOnClickListener { toast(getString(R.string.onCancel)) }
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
            save()
            toast(getString(R.string.onSave))
            finish()
        } else {
            toast(getString(R.string.onSaveFail))
        }
    }

    fun save(){
        model.contactsList.add(
            Contact(
                nameInputText.text.toString(),
                contactInputText.text.toString().toLong(),
                mailInputText.text?.toString(),
                birthdayInputText.text?.toString(),
                genderInputText.text?.toString(),
                memoInputText.text?.toString()
            )
        )
    }

    fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}