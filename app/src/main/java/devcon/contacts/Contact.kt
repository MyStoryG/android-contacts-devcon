package devcon.contacts

data class Contact(
    val name:String,
    val contact:Long,
    val mail:String? = null,
    val birthday:String? = null,
    val gender:String? = null,
    val memo:String? = null
)
