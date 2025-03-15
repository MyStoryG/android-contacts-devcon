package devcon.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import devcon.core.BaseActivity
import devcon.learn.contacts.R

class MainActivity : BaseActivity() {
    private lateinit var buttonAddContact: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
    }

    override fun initializeView() {
        buttonAddContact = findViewById(R.id.buttonAddContact)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonAddContact -> {
                val intent =
                    Intent(
                        this@MainActivity,
                        RegisterActivity::class.java,
                    )

                startActivity(intent)
            }

            else -> {}
        }
    }
}
