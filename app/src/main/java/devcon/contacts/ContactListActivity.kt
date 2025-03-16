package devcon.contacts

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import devcon.contacts.data.Contact
import devcon.learn.contacts.R

class ContactListActivity : AppCompatActivity() {
    private val listviewContacts by lazy { findViewById<ListView>(R.id.listview_contacts) }

    private val textviewEmptyContactList by lazy { findViewById<TextView>(R.id.textview_empty_contact_list) }

    private val fabContactAddition by lazy { findViewById<FloatingActionButton>(R.id.fab_contact_addition) }

    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            val contact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(Constants.EXTRA_CONTACT, Contact::class.java)
            } else {
                result.data?.getParcelableExtra(Constants.EXTRA_CONTACT)
            }
            contactAdapter.addItem(contact!!)

            if (contactAdapter.count > 0) {
                textviewEmptyContactList.visibility = View.GONE
            }
        }
    }

    private val contactAdapter: ContactAdapter by lazy { ContactAdapter(this, mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        listviewContacts.apply {
            adapter = contactAdapter
            setOnItemClickListener { _, _, position, _ ->
                val contact = contactAdapter.getItem(position)
                Log.d(TAG, "$contact")
                // TODO: ContactDetailActivity 로 이동
            }
        }
        fabContactAddition.setOnClickListener {
            val intent = Intent(this, ContactAdditionActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    companion object {
        private const val TAG = "ContactListActivity"

    }
}