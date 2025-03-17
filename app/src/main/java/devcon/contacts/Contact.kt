package devcon.contacts

data class Contact(
    val name: String,
    val contact: Long,
    val mail: String = "",
    val birthday: String = "",
    val gender: String = "",
    val memo: String = ""
)
