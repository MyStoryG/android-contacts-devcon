package devcon.common

fun Int.padZero(totalDigits: Int): String = this.toString().padStart(totalDigits, '0')
