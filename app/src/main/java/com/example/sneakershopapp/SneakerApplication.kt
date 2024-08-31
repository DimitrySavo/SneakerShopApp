package com.example.sneakershopapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import com.example.sneakershopapp.model.DataService
import com.google.firebase.FirebaseApp
import java.lang.IllegalStateException
import java.util.prefs.Preferences

class SneakerApplication() : Application() {
    val dataService by lazy {
        DataService()
    }

    init {
        instance = this
    }

    companion object{
        private var instance: SneakerApplication? = null

        fun getInstance(): SneakerApplication{
            return instance ?: throw IllegalStateException("Application not initialized yet")
        }
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}