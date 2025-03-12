package devcon.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import devcon.learn.contacts.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMore = findViewById<Button>(R.id.btnAddContact)

        btnMore.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                RegisterActivity::class.java
            )

            startActivity(intent)
        }
    }
}
