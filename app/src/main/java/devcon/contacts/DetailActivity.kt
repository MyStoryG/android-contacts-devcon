package devcon.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devcon.learn.contacts.R
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {

    private lateinit var tvPhoneContent: TextView
    private lateinit var tvNameContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvNameContent = findViewById(R.id.tv_name_content)
        tvPhoneContent = findViewById(R.id.tv_phone_content)

        tvNameContent.text = intent.getStringExtra(Constants.INTENT_KEY_PROFILE_NAME)
        tvPhoneContent.text = intent.getStringExtra(Constants.INTENT_KEY_PROFILE_PHONE)
    }
}