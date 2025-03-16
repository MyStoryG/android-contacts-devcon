package devcon.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle

object Utils {

    fun navigateToActivity(context: Context, destinationClass: Class<*>) {
        val intent = Intent(context, destinationClass)
        context.startActivity(intent)
    }

    fun navigateToActivity(context: Context, destinationClass: Class<*>, data: Profile) {
        val intent = Intent(context, destinationClass)
        intent.putExtra(Constants.INTENT_KEY_PROFILE_NAME, data.name)
        intent.putExtra(Constants.INTENT_KEY_PROFILE_PHONE, data.phone)
        context.startActivity(intent)
    }
}
