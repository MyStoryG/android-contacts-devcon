package devcon.contacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import devcon.core.BaseActivity
import devcon.domain.Contact
import devcon.domain.Gender
import devcon.learn.contacts.R

class RegisterActivity : BaseActivity() {
    private lateinit var buttonMore: Button
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button

    private lateinit var textInputName: EditText
    private lateinit var textInputPhone: EditText
    private lateinit var textInputMail: EditText
    private lateinit var additionalDataLayout: LinearLayout
    private lateinit var textInputBirthday: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var textInputMemo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resgister)

        initializeView()
        addBackPressedDispatcher()
    }

    override fun initializeView() {
        buttonMore = findViewById(R.id.buttonMore)
        buttonCancel = findViewById(R.id.buttonCancel)
        buttonSave = findViewById(R.id.buttonSave)

        textInputName = findViewById(R.id.textInputName)
        textInputPhone = findViewById(R.id.textInputPhone)
        textInputMail = findViewById(R.id.textInputMail)
        textInputBirthday = findViewById(R.id.textInputBirthday)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        textInputMemo = findViewById(R.id.textInputMemo)

        additionalDataLayout = findViewById(R.id.additionalDataLayout)

        listOf(buttonMore, buttonCancel, buttonSave).forEach { it.setOnClickListener(this) }
    }

    private fun addBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    cancelAddContact()
                }
            },
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonMore -> {
                additionalDataLayout.visibility = View.VISIBLE
                buttonMore.visibility = View.GONE
            }

            R.id.buttonCancel -> {
                cancelAddContact()
            }

            R.id.buttonSave -> {
                if (!areRequiredFieldsFilled()) {
                    showToast("이름과 전화번호는 필수입니다.")
                    return
                }

                showToast("저장이 완료 되었습니다.")
                val contact =
                    Contact(
                        name = textInputName.text.toString(),
                        phoneNumber = textInputPhone.text.toString(),
                        mail = textInputMail.text.toString(),
                        birthday = textInputMail.text.toString(),
                        gender =
                            when (genderRadioGroup.checkedRadioButtonId) {
                                R.id.genderMale -> Gender.MALE
                                R.id.genderFemale -> Gender.FEMALE
                                else -> Gender.NONE
                            },
                        memo = textInputMemo.text.toString(),
                    )

                val resultIntent =
                    Intent().apply {
                        putExtra("CONTACT", contact)
                    }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }

            else -> {}
        }
    }

    private fun cancelAddContact() {
        if (anyFieldsFilled()) {
            showDialog(
                message = "작성 중인 내용이 있습니다.\n정말 나가시겠습니까?",
                confirmText = "나가기",
                cancelText = "작성하기",
                onCancel = { return@showDialog },
                onSuccess = { finishAndShowToast() },
            )
        } else {
            finishAndShowToast()
        }
    }

    private fun finishAndShowToast() {
        val cancelToast = Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT)
        cancelToast.show()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun anyFieldsFilled(): Boolean =
        textInputName.text
            .toString()
            .trim()
            .isNotEmpty() ||
            textInputPhone.text
                .toString()
                .trim()
                .isNotEmpty() ||
            textInputMail.text
                .toString()
                .trim()
                .isNotEmpty() ||
            textInputBirthday.text
                .toString()
                .trim()
                .isNotEmpty() ||
            genderRadioGroup.checkedRadioButtonId != -1 ||
            textInputMemo.text
                .toString()
                .trim()
                .isNotEmpty()

    private fun areRequiredFieldsFilled(): Boolean {
        val name = textInputName.text.toString().trim()
        val phone = textInputPhone.text.toString().trim()
        return name.isNotEmpty() && phone.isNotEmpty()
    }
}
