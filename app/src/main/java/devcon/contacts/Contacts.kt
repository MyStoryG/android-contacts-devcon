package devcon.contacts

import android.app.Application

class Contacts : Application() {
    override fun onCreate() {
        super.onCreate()
        ProfileSharedPreferences.init(applicationContext)
    }
}