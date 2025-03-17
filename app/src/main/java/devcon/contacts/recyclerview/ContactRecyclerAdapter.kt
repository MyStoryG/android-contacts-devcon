package devcon.contacts.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import devcon.contacts.Contact
import devcon.learn.contacts.R

class ContactRecyclerAdapter(
    private val onClick: (contact: Contact) -> Unit
) : RecyclerView.Adapter<ContactRecyclerViewHolder>() {

    var itemList = emptyList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_view_holder, parent, false)
        return ContactRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ContactRecyclerViewHolder, position: Int) {
        val item = itemList[position]
        holder.run {
            setHolder(item.name)
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }
}