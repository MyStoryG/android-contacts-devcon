package devcon.contacts

data class Profile(
    val name: String,
    val phone: String,
    val mail: String,
    val birthday: String,
    val gender: String,
    val note: String
)

fun String.toProfile(): Profile {
    val parts = this.split("|")
    return Profile(
        name = parts[0],
        phone = parts[1],
        mail = parts[2],
        birthday = parts[3],
        gender = parts[4],
        note = parts[5]
    )
}

fun Profile.toJsonString(): String {
    return "$name|$phone|$mail|$birthday|$gender|$note"
}
