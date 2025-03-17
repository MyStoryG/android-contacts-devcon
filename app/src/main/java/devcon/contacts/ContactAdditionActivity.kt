package devcon.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.google.android.material.datepicker.MaterialDatePicker
import devcon.contacts.data.Contact
import devcon.contacts.utils.showSoftKeyboard
import devcon.contacts.utils.showToast
import devcon.learn.contacts.R
import java.text.SimpleDateFormat
import java.util.Locale

/*
    TODO
     1. 구성 변경시 UI 상태 관리
 */
class ContactAdditionActivity : AppCompatActivity() {
    private val editTextName: EditText by lazy { findViewById(R.id.edittext_contact_name) }
    private val editTextPhone: EditText by lazy { findViewById(R.id.edittext_contact_phone) }
    private val editTextMail: EditText by lazy { findViewById(R.id.edittext_contact_mail) }
    private val editTextMemo: EditText by lazy { findViewById(R.id.edittext_contact_memo) }

    private val textViewBirthday: TextView by lazy { findViewById(R.id.textview_contact_birthday) }

    private val radioGroupGender: RadioGroup by lazy { findViewById(R.id.radiogroup_gender) }
    private val radioButtonFemale: RadioButton by lazy { findViewById(R.id.radiobutton_female) }
    private val radioButtonMale: RadioButton by lazy { findViewById(R.id.radiobutton_male) }

    private val buttonMore: Button by lazy { findViewById(R.id.button_more) }
    private val buttonCancel: Button by lazy { findViewById(R.id.button_cancel) }
    private val buttonSave: Button by lazy { findViewById(R.id.button_save) }

    private val groupContactProperties: Group by lazy { findViewById(R.id.group_contact_properties) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_addition)

        textViewBirthday.setOnClickListener {
            showDatePicker {
                val simpleDateFormat = SimpleDateFormat(
                    Constants.DATE_FORMAT_BIRTHDAY,
                    Locale.getDefault()
                )
                textViewBirthday.text = simpleDateFormat.format(it)
            }
        }
        buttonMore.setOnClickListener {
            groupContactProperties.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
        buttonCancel.setOnClickListener {
            if (hasPendingEdits()) {
                showPendingEditsDialog()
            } else {
                showToast(R.string.toast_cancel_contact)
                finish()
            }
        }
        buttonSave.setOnClickListener {
            when {
                editTextName.text.isEmpty() -> {
                    showToast(R.string.toast_required_name)
                    editTextName.showSoftKeyboard()
                }

                editTextPhone.text.isEmpty() -> {
                    showToast(R.string.toast_required_phone)
                    editTextPhone.showSoftKeyboard()
                }

                else -> {
                    showToast(R.string.toast_save_contact)
                    saveContact()
                }
            }
        }
    }

    private fun showDatePicker(onPositiveClickCallback: (Long) -> Unit) {
        MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
            .apply {
                addOnPositiveButtonClickListener { selection ->
                    onPositiveClickCallback(selection)
                }
                show(supportFragmentManager, "DatePicker")
            }
    }

    private fun hasPendingEdits(): Boolean {
        val isNameNotEmpty = editTextName.text.isNotEmpty()
        val isPhoneNotEmpty = editTextPhone.text.isNotEmpty()
        val isMailNotEmpty = editTextMail.text.isNotEmpty()
        val isMemoNotEmpty = editTextMemo.text.isNotEmpty()
        val isBirthdayNotEmpty = textViewBirthday.text.isNotEmpty()
        val isGenderChecked = radioGroupGender.checkedRadioButtonId != View.NO_ID

        return isNameNotEmpty || isPhoneNotEmpty || isMailNotEmpty || isMemoNotEmpty || isBirthdayNotEmpty || isGenderChecked
    }

    private fun showPendingEditsDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.dialog_message_has_pending_edits)
            .setPositiveButton(R.string.exit) { dialog, _ ->
                dialog.dismiss()
                showToast(R.string.toast_cancel_contact)
                finish()
            }
            .setNegativeButton(R.string.edit) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun saveContact() {
        val name = editTextName.text.toString()
        val phone = editTextPhone.text.toString()
        val mail = editTextMail.text.toString()
        val birthday = textViewBirthday.text.toString()
        val gender = when (radioGroupGender.checkedRadioButtonId) {
            radioButtonFemale.id -> radioButtonFemale.text.toString()
            radioButtonMale.id -> radioButtonMale.text.toString()
            else -> ""
        }
        val memo = editTextMemo.text.toString()

        val contact = Contact(name, phone, mail, birthday, gender, memo)
        val intent = Intent().apply { putExtra(Constants.EXTRA_CONTACT, contact) }

        setResult(RESULT_OK, intent)
        finish()
    }
}