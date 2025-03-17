package devcon.contacts

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import devcon.contacts.data.Contact
import devcon.learn.contacts.R

class ContactDetailActivity : AppCompatActivity() {
    private val textViewName: TextView by lazy { findViewById(R.id.textview_contact_name) }
    private val textViewPhone: TextView by lazy { findViewById(R.id.textview_contact_phone) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Constants.EXTRA_CONTACT, Contact::class.java)
        } else {
            intent?.getParcelableExtra(Constants.EXTRA_CONTACT)
        }

        textViewName.text = contact?.name
        textViewPhone.text = contact?.phone
    }
}