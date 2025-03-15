package devcon.core

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :
    AppCompatActivity(),
    View.OnClickListener {
    protected abstract fun initializeView()

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun showDialog(
        title: String? = null,
        message: String? = null,
        confirmText: String? = null,
        cancelText: String? = null,
        onSuccess: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null,
    ) {
        AlertDialog
            .Builder(this)
            .setMessage(message)
            .apply {
                title?.let { setTitle(title) }
            }.apply {
                message?.let { setMessage(message) }
            }.setPositiveButton(
                confirmText ?: "ok",
            ) { _, _ -> onSuccess?.invoke() }
            .setNegativeButton(
                cancelText ?: "cancel",
            ) { _, _ -> onCancel?.invoke() }
            .create()
            .show()
    }
}
