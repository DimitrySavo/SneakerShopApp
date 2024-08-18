package com.example.sneakershopapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sneakershopapp.model.DataService
import com.google.android.gms.common.logging.Logger
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {
    @Test
    fun getShoesTestFirstItem() = runBlocking {
        val shoes = DataService().getShoes()
        assertNotNull(shoes)
        assertTrue(shoes.isNotEmpty())
        assertEquals("Nike", shoes.first().brandName)
    }
}