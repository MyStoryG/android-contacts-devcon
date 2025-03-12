package devcon.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import devcon.learn.contacts.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resgister)

        val btnMore = findViewById<Button>(R.id.btnMore)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // 추후 2단계 과제를 위해 사용 예정
        val textInputName = findViewById<EditText>(R.id.textInputName)
        val textInputPhone = findViewById<EditText>(R.id.textInputPhone)
        val textInputMail = findViewById<EditText>(R.id.textInputMail)
        val textInputBirthday = findViewById<EditText>(R.id.textInputBirthday)
        val genderSelectorLayout = findViewById<LinearLayout>(R.id.genderSelectorLayout)
        val textInputMemo = findViewById<EditText>(R.id.textInputMemo)

        btnMore.setOnClickListener {
            textInputBirthday.visibility = View.VISIBLE
            genderSelectorLayout.visibility = View.VISIBLE
            textInputMemo.visibility = View.VISIBLE
            btnMore.visibility = View.GONE
        }

        btnCancel.setOnClickListener {
            val cancelToast = Toast.makeText(this.applicationContext, "취소 되었습니다.", Toast.LENGTH_SHORT)
            cancelToast.show()
            finish()
        }

        btnSave.setOnClickListener {
            val name = textInputName.text
            val phoneNumber = textInputPhone.text

            if (name.isEmpty() || phoneNumber.isEmpty()) {
                val missingFieldToast = Toast.makeText(this.applicationContext, "이름과 전화번호는 필수입니다.", Toast.LENGTH_SHORT)
                missingFieldToast.show()
                return@setOnClickListener
            }

            val successSaveToast = Toast.makeText(this.applicationContext, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT)
            successSaveToast.show()
            finish()
        }
    }
}
