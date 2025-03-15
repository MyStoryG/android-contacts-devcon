package devcon.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import devcon.learn.contacts.R

class ContactListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
    }
}