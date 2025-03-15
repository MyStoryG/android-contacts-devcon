package devcon.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phoneNumber: String,
    val mail: String? = null,
    val birthday: String? = null,
    val gender: Gender? = null,
    val memo: String? = null,
) : Parcelable {
    fun toDisplayData(): Map<String, Any?> =
        mapOf(
            "이름" to name,
            "전화번호" to phoneNumber,
            "메일" to mail,
            "생일" to birthday,
            "성별" to gender,
            "메모" to memo,
        )
}
