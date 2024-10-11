package com.example.sneakershopapp

import android.app.Application
import android.content.Context
import android.util.Log
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
        Log.i("Get into init block:", "instance is $this")
        instance = this
    }

    companion object{
        private var instance: SneakerApplication? = null

        fun getInstance(): SneakerApplication{
            Log.i("Application init: ", "get to the method. instance is $instance")
            return instance ?: throw IllegalStateException("Application not initialized yet")
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("get into application onCreate:", "instance is $this")
        FirebaseApp.initializeApp(this)
    }
}