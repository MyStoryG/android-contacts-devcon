package devcon.contacts

import android.app.DatePickerDialog
import android.content.Context
import android.content.SyncInfo
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import devcon.learn.contacts.R

class MainActivity : AppCompatActivity() {

    private lateinit var etMail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etName: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var groupMoreBtn: Group
    private lateinit var groupMoreView: Group
    private lateinit var viewMore: View
    private lateinit var etBirthday: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var etNote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        etName = findViewById(R.id.et_name)
        etPhone = findViewById(R.id.et_phone)
        etMail = findViewById(R.id.et_mail)
        btnSave = findViewById(R.id.btn_save)
        btnCancel = findViewById(R.id.btn_cancel)
        groupMoreBtn = findViewById(R.id.group_more_btn)
        groupMoreView = findViewById(R.id.group_more_view)
        viewMore = findViewById(R.id.view_more)
        etBirthday = findViewById(R.id.et_birthday)
        radioGroupGender = findViewById(R.id.radio_group_gender)
        etNote = findViewById(R.id.et_note)

        initOnClickListener()
    }

    private fun saveProfile() {
        val profile = Profile(
            name = etName.text.toString(),
            phone = etPhone.text.toString(),
            mail = etMail.text.toString(),
            birthday = etBirthday.text.toString(),
            gender = getGenderTest(),
            note = etNote.text.toString()
        )
        ProfileSharedPreferences.saveProfile(profile)
    }

    private fun getGenderTest(): String {
        val selectedBtnId = radioGroupGender.checkedRadioButtonId
        if (selectedBtnId != -1) {
            val selectedBtn = findViewById<RadioButton>(selectedBtnId)
            return selectedBtn.text.toString()
        }
        return "" // 선택된 버튼이 없음
    }

    private fun initOnClickListener() {

        btnSave.setOnClickListener {
            if (isNameEmpty()) {
                showToast(resources.getString(R.string.toast_name_empty))
                showKeyboard(etName)
            } else if (isPhoneEmpty()) {
                showToast(resources.getString(R.string.toast_phone_empty))
                showKeyboard(etPhone)
            } else {
                showToast(resources.getString(R.string.toast_save))
                saveProfile()
                Utils.navigateToActivity(this, ListActivity::class.java)
            }
        }

        btnCancel.setOnClickListener {
            showToast(resources.getString(R.string.toast_cancel))
            Utils.navigateToActivity(this, ListActivity::class.java)
        }

        viewMore.setOnClickListener {
            groupMoreBtn.visibility = View.GONE
            groupMoreView.visibility = View.VISIBLE
        }

        etBirthday.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun isNameEmpty(): Boolean {
        return etName.text.isNullOrEmpty()
    }

    private fun isPhoneEmpty(): Boolean {
        return etPhone.text.isNullOrEmpty()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showKeyboard(focusEditText: EditText) {
        val inputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        focusEditText.requestFocus()
        inputMethodManager.showSoftInput(focusEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val result = "${selectedYear}.${selectedMonth}.${selectedDay}"
            etBirthday.setText(result)
        }, year, month, day).show()

    }
}
