package devcon.contacts.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phone: String,
    val mail: String,
    val birthday: String,
    val gender: String,
    val memo: String,
) : Parcelable
