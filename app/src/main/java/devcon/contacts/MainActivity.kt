package devcon.contacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import devcon.core.BaseActivity
import devcon.domain.Contact
import devcon.learn.contacts.R

class MainActivity : BaseActivity() {
    private lateinit var buttonAddContact: Button
    private lateinit var contactAddResultLauncher: ActivityResultLauncher<Intent>

    private fun setResultContactAdd() {
        contactAddResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val contact = result.data?.getParcelableExtra<Contact>("CONTACT")
                    Log.v("asdf", "contact = $contact")
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
            findViewById<Button?>(R.id.buttonAddContact).also { it.setOnClickListener(this) }
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
}
