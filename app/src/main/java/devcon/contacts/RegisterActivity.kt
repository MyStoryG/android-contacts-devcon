package devcon.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import devcon.learn.contacts.R

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var buttonMore: Button
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button

    private lateinit var textInputName: EditText
    private lateinit var textInputPhone: EditText
    private lateinit var additionalDataLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resgister)
        initializeView()
    }

    private fun initializeView() {
        buttonMore = findViewById(R.id.buttonMore)
        buttonCancel = findViewById(R.id.buttonCancel)
        buttonSave = findViewById(R.id.buttonSave)

        textInputName = findViewById(R.id.textInputName)
        textInputPhone = findViewById(R.id.textInputPhone)
        additionalDataLayout = findViewById(R.id.additionalDataLayout)

        listOf(buttonMore, buttonCancel, buttonSave).forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonMore -> {
                additionalDataLayout.visibility = View.VISIBLE
                buttonMore.visibility = View.GONE
            }

            R.id.buttonCancel -> {
                val cancelToast = Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT)
                cancelToast.show()
                finish()
            }

            R.id.buttonSave -> {
                if (!areRequiredFieldsFilled()) {
                    val missingFieldToast = Toast.makeText(
                        this, "이름과 전화번호는 필수입니다.", Toast.LENGTH_SHORT
                    )
                    missingFieldToast.show()
                    return
                }

                val successSaveToast = Toast.makeText(this, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT)
                successSaveToast.show()
                finish()
            }

            else -> {}
        }
    }

    private fun areRequiredFieldsFilled(): Boolean {
        val name = textInputName.text.toString().trim()
        val phone = textInputPhone.text.toString().trim()
        return name.isNotEmpty() && phone.isNotEmpty()
    }
}
