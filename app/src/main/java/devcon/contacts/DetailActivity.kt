package devcon.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import devcon.core.BaseActivity
import devcon.domain.Contact
import devcon.domain.Gender
import devcon.learn.contacts.R

class DetailActivity : BaseActivity() {
    private lateinit var contact: Contact

    private lateinit var contactDataLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        contact = intent.getParcelableExtra(CONTACT)!!

        initializeView()
    }

    override fun initializeView() {
        contactDataLayout = findViewById(R.id.contactDataLayout)
        updateUI()
    }

    private fun updateUI() {
        val inflater = LayoutInflater.from(this)

        contact.toDisplayData().forEach { (key, value) ->
            value?.let {
                val hasValue =
                    when (value) {
                        is Gender -> value != Gender.NONE
                        is String -> value.isNotEmpty()
                        else -> false
                    }

                if (hasValue) {
                    val contactDetailData =
                        inflater.inflate(R.layout.user_contact_detail, contactDataLayout, false)
                    val label = contactDetailData.findViewById<TextView>(R.id.label)
                    val data = contactDetailData.findViewById<TextView>(R.id.data)

                    label.text = key
                    data.text = value.toString()

                    contactDataLayout.addView(contactDetailData)
                }
            }
        }
    }

    override fun onClick(v: View?) {
    }

    companion object {
        const val CONTACT = "CONTACT"

        fun newIntent(
            context: Context,
            contact: Contact,
        ): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(CONTACT, contact)
            }
    }
}
