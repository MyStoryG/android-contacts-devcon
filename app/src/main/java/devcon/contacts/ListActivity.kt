package devcon.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import devcon.learn.contacts.R
import org.w3c.dom.Text

class ListActivity : AppCompatActivity() {

    private lateinit var btnAdd: ImageView
    private lateinit var scrollView: ScrollView
    private lateinit var scrollViewContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initView()
        initData()
    }

    private fun initData() {
        val getData = ProfileSharedPreferences.getAllProfiles()

        getData.forEach {
            profileAddView(it)
        }
    }

    private fun initView() {
        btnAdd = findViewById(R.id.btn_add)
        scrollView = findViewById(R.id.scroll_view)
        scrollViewContainer = findViewById(R.id.scroll_view_container)

        initOnClickListener()
    }

    private fun initOnClickListener() {
        btnAdd.setOnClickListener {
            Utils.navigateToActivity(this, MainActivity::class.java)
        }
    }

    private fun profileAddView(profile: Profile) {
        val itemView = LayoutInflater.from(this)
            .inflate(R.layout.item_profile_list, scrollViewContainer, false)

        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvImg = itemView.findViewById<TextView>(R.id.tv_profile_img)

        tvName.text = profile.name
        tvImg.text = profile.name.first().toString()

        scrollViewContainer.addView(itemView)

        itemView.setOnClickListener {
            Utils.navigateToActivity(this, DetailActivity::class.java, profile)
        }
    }
}