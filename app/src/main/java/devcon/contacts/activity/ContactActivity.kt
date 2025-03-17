package devcon.contacts.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import devcon.contacts.Contact
import devcon.learn.contacts.R

/** must be started with [devcon.contacts.toContactActivity] **/
class ContactActivity : AppCompatActivity() {

    companion object {
        lateinit var contactToView: Contact
    }

    private val contactName by lazy { findViewById<TextView>(R.id.contact_name) }
    private val contactContact by lazy { findViewById<TextView>(R.id.contact_contact) }
    private val contactMail by lazy { findViewById<TextView>(R.id.contact_mail) }
    private val contactBirthday by lazy { findViewById<TextView>(R.id.contact_birthday) }
    private val contactGender by lazy { findViewById<TextView>(R.id.contact_gender) }
    private val contactMemo by lazy { findViewById<TextView>(R.id.contact_memo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        contactName.text = contactToView.name
        contactContact.text = contactToView.contact.toString()
        contactMail.text = contactToView.mail
        contactBirthday.text = contactToView.birthday
        contactGender.text = contactToView.gender
        contactMemo.text = contactToView.memo
    }
}