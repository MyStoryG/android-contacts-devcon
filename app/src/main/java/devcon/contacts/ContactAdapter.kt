package devcon.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import devcon.contacts.data.Contact
import devcon.learn.contacts.R

class ContactAdapter(
    private val context: Context,
    private val contacts: MutableList<Contact>
) : BaseAdapter() {
    override fun getCount(): Int = contacts.count()

    override fun getItem(position: Int): Contact = contacts[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_contact, parent, false)
        val textViewThumbnail = view.findViewById<TextView>(R.id.textview_contact_thumbnail)
        val textViewName = view.findViewById<TextView>(R.id.textview_contact_name)

        textViewThumbnail.text = getItem(position).name.first().toString()
        textViewName.text = getItem(position).name

        return view
    }

    fun addItem(contact: Contact) {
        contacts.add(contact)
        notifyDataSetChanged()
    }
}