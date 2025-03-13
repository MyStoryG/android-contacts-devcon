package devcon.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import devcon.contacts.utils.showToast
import devcon.learn.contacts.R

/*
    TODO
     1. 구성 변경시 UI 상태 관리
     2. 생일 입력 방식 변경(EditText -> DatePicker)
     3. 디자인 시스탬 추가(Typography, Color, Etc)
 */
class MainActivity : AppCompatActivity() {
    private val editTextName: EditText by lazy { findViewById(R.id.edittext_contact_name) }
    private val editTextPhone: EditText by lazy { findViewById(R.id.edittext_contact_phone) }
    private val buttonMore: Button by lazy { findViewById(R.id.button_more) }
    private val buttonCancel: Button by lazy { findViewById(R.id.button_cancel) }
    private val buttonSave: Button by lazy { findViewById(R.id.button_save) }
    private val groupContactProperties: Group by lazy { findViewById(R.id.group_contact_properties) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonMore.setOnClickListener {
            groupContactProperties.visibility = View.VISIBLE
            it.visibility = View.GONE
        }
        buttonCancel.setOnClickListener {
            showToast(R.string.toast_cancel_contact)
        }
        buttonSave.setOnClickListener {
            @StringRes val message = when {
                editTextName.text.isEmpty() -> R.string.toast_required_name
                editTextPhone.text.isEmpty() -> R.string.toast_required_phone
                else -> R.string.toast_save_contact
            }

            showToast(message)
        }
    }
}
