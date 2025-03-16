package devcon.contacts

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object ProfileSharedPreferences {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        if (!::sharedPreferences.isInitialized) {
            sharedPreferences = context.applicationContext.getSharedPreferences(
                "ProfilePrefs",
                Context.MODE_PRIVATE
            )
        }
    }

    fun saveProfile(profile: Profile) {
        sharedPreferences.edit {
            putString(profile.name, profile.toJsonString())
        }
    }

    fun getAllProfiles(): List<Profile> {
        return sharedPreferences.all.mapNotNull { (_, value) ->
            (value as? String)?.toProfile()
        }
    }
}