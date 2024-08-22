package com.example.sneakershopapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import com.google.firebase.FirebaseApp
import java.util.prefs.Preferences

class SneakerApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}