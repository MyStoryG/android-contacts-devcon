package devcon.contacts.abstracts

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import devcon.contacts.Contact
import devcon.contacts.objects.MyObjects.contactsList
import devcon.learn.contacts.R

abstract class BaseRegisterActivity:AppCompatActivity() {

    val cancelButton by lazy { findViewById<Button>(R.id.cancelButton) }
    val saveButton by lazy { findViewById<Button>(R.id.saveButton) }
    val nameInputText by lazy { findViewById<TextInputEditText>(R.id.nameInputEditText) }
    val contactInputText by lazy { findViewById<TextInputEditText>(R.id.contactInputEditText) }
    val mailInputText by lazy { findViewById<TextInputEditText>(R.id.mailInputEditText) }
    val birthdayInputText by lazy { findViewById<TextInputEditText>(R.id.birthdayInputEditText) }
    val memoInputText by lazy { findViewById<TextInputEditText>(R.id.memoInputEditText) }
    val moreButton by lazy { findViewById<Button>(R.id.moreButton) }
    val genderInputText by lazy { findViewById<TextInputEditText>(R.id.genderInputEditText) }
    val genderSelection by lazy { findViewById<RadioGroup>(R.id.genderSelection) }
    val optionalLayout by lazy { findViewById<LinearLayout>(R.id.optionalLayout) }

    /** view 내용 처리 **/
    fun setView(){
        saveButton.setOnClickListener { onSave() }
        cancelButton.setOnClickListener { onCancel() }
        moreButton.setOnClickListener {
            moreButton.visibility = GONE
            optionalLayout.visibility = VISIBLE
        }
        birthdayInputText.setOnClickListener { datePicker(birthdayInputText) }
        genderSelection.setOnCheckedChangeListener { _, selected ->
            genderInputText.setText(findViewById<RadioButton>(selected).text)
        }
    }

    abstract fun onSave(
        name:String = nameInputText.text.toString(),
        contact:Long = contactInputText.text.toString().toLong(),
        mail:String = mailInputText.text.toString(),
        birthday:String = birthdayInputText.text.toString(),
        gender:String = genderInputText.text.toString(),
        memo:String = memoInputText.text.toString()
    )
    abstract fun onCancel()
    fun save(){
        contactsList.add(
            Contact(
                nameInputText.text.toString(),
                contactInputText.text.toString().toLong(),
                mailInputText.text.toString(),
                birthdayInputText.text.toString(),
                genderInputText.text.toString(),
                memoInputText.text.toString()
            )
        )
    }
    abstract fun datePicker(birthdayInputText:TextInputEditText)
}