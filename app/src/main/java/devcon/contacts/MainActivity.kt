package devcon.contacts

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import devcon.core.BaseActivity
import devcon.domain.Contact
import devcon.learn.contacts.R

class MainActivity : BaseActivity() {
    private lateinit var buttonAddContact: Button
    private lateinit var contactDataLayout: LinearLayout
    private lateinit var contactScrollView: ScrollView
    private lateinit var emptyContactView: TextView

    private lateinit var contactAddResultLauncher: ActivityResultLauncher<Intent>

    private val colorList = listOf(Color.BLUE, Color.RED, Color.RED, Color.GREEN, Color.LTGRAY)

    private var contactList: MutableList<Contact> = mutableListOf()

    private fun setResultContactAdd() {
        contactAddResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.getParcelableExtra<Contact>("CONTACT")?.let {
                        contactList.add(it)
                        updateUI()
                    }
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        setResultContactAdd()
    }

    override fun initializeView() {
        buttonAddContact =
            findViewById<Button>(R.id.buttonAddContact).also { it.setOnClickListener(this) }

        emptyContactView = findViewById(R.id.emptyContactView)
        contactDataLayout = findViewById(R.id.contactDataLayout)
        contactScrollView = findViewById(R.id.contactScrollView)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonAddContact -> {
                val intent =
                    Intent(
                        this,
                        RegisterActivity::class.java,
                    )

                contactAddResultLauncher.launch(intent)
            }

            else -> {}
        }
    }

    private fun updateUI() {
        if (contactList.isEmpty()) {
            emptyContactView.visibility = View.VISIBLE
            contactScrollView.visibility = View.GONE
        } else {
            emptyContactView.visibility = View.GONE
            contactScrollView.visibility = View.VISIBLE
        }

        contactDataLayout.removeAllViews()

        for ((index, contact) in contactList.withIndex()) {
            contactDataLayout.addView(makeContactView(contact, index))
        }
    }

    private fun makeContactView(
        contact: Contact,
        index: Int,
    ): View? {
        val inflater = LayoutInflater.from(this)

        val contactView = inflater.inflate(R.layout.user_contact, contactDataLayout, false)
        val nameFirstText = contactView.findViewById<TextView>(R.id.nameFirstText)
        val userPhoneNumber = contactView.findViewById<TextView>(R.id.userPhoneNumber)

        val drawable = nameFirstText.background as? GradientDrawable
        drawable?.setColor(colorList[index % 5])

        // 이름의 첫 글자와 유저 번호 설정
        nameFirstText.text = contact.name.first().toString()
        userPhoneNumber.text = contact.phoneNumber

        return contactView
    }
}
