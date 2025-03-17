package devcon.contacts

import android.content.Context
import android.content.Intent
import android.widget.Toast
import devcon.contacts.activity.ContactActivity

fun toContactActivity(context: Context, targetItem: Contact) {
    ContactActivity.contactToView = targetItem
    context.startActivity(Intent(context, ContactActivity::class.java))
}

fun toast(context: Context, text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()