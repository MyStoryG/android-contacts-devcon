package devcon.contacts.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import devcon.learn.contacts.R

class ContactRecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    private val nameText = itemView.findViewById<TextView>(R.id.contactName)

    fun setHolder(name:String){
        nameText.text = name
    }
}