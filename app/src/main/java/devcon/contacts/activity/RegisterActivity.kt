package devcon.contacts.activity

import android.app.DatePickerDialog
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import devcon.contacts.abstracts.BaseRegisterActivity
import devcon.contacts.toast
import devcon.learn.contacts.R

class RegisterActivity : BaseRegisterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setView()
    }

    override fun onSave(
        name: String,
        contact: Long,
        mail: String,
        birthday: String,
        gender: String,
        memo: String
    ) {
        if (name.isNotEmpty() && contact.toString().isNotEmpty()) {
            save()
            toast(this, getString(R.string.onSave))
            finish()
        } else {
            toast(this, getString(R.string.onSaveFail))
        }
    }

    override fun onCancel() {
        toast(this, getString(R.string.onCancel))
        finish()
    }

    override fun datePicker(birthdayInputText:TextInputEditText) {
        val listener = DatePickerDialog.OnDateSetListener { picker, y, m, d ->
            birthdayInputText.setText("${y}년 ${m}월 ${d}일")
        }
        DatePickerDialog(this, listener, 2025, 1, 1).show()
    }
}