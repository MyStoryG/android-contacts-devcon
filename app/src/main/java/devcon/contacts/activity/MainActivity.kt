package devcon.contacts.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import devcon.contacts.objects.MyObjects.model
import devcon.contacts.recyclerview.ContactRecyclerAdapter
import devcon.learn.contacts.R

class MainActivity : AppCompatActivity() {

    private val registerButton by lazy{findViewById<FloatingActionButton>(R.id.registerButton)}
    private val recyclerView by lazy{findViewById<RecyclerView>(R.id.contactsRecyclerView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.run{
            adapter = ContactRecyclerAdapter(
                onClick = {
                    model.onClick(it)
                    startActivity(Intent(this@MainActivity,ContactActivity::class.java))
                }
            ).apply { itemList = model.contactsList }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        registerButton.setOnClickListener { startActivity(Intent(this, RegisterActivity::class.java)) }
    }
}