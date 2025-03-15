package devcon.domain

import android.os.Parcelable
import devcon.learn.contacts.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phoneNumber: String,
    val mail: String,
    val birthday: String,
    val gender: Gender,
    val memo: String,
) : Parcelable {
    fun toDisplayData(): Map<Int, Any?> =
        mapOf(
            R.string.label_name to name,
            R.string.label_phone to phoneNumber,
            R.string.label_mail to mail,
            R.string.label_birthday to birthday,
            R.string.label_gender to gender,
            R.string.label_input_memo to memo,
        )
}
