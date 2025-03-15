package devcon.core

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :
    AppCompatActivity(),
    View.OnClickListener {
    protected abstract fun initializeView()

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
