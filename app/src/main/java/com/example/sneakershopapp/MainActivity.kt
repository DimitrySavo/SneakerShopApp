package com.example.sneakershopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.sneakershopapp.model.getPreviewUrl
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SneakerShopAppTheme {
                val viewModel = viewModel<MyViewModel>()
                val shoesList by viewModel.shoes.collectAsState()
                val user by viewModel.user.collectAsState()
                val password by viewModel.password.collectAsState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn {
                        if(shoesList.isNotEmpty()){
                            items(shoesList, key = { it.id }) { shoe ->
                                Row{
                                    Text(text = shoe.modelName)

                                    val imageUrl by produceState<String?>(initialValue = null) { // прикольная фишка, которая позволяет выполять асинхронные операции внутри Composable и обновлять значение в зависимости от изменений ключей
                                        value = shoe.getPreviewUrl()
                                    }

                                    imageUrl?.let{
                                        AsyncImage(
                                            model = it,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        } else {
                            item {
                                Text(text = "No shoes available")
                            }
                        }
                    }

                    Button(onClick = { viewModel.getShoes() }) {
                        Text(text = "Get Shoes")
                    }

                    TextField(value = user.name, onValueChange = { viewModel.updateUser(name = it) })
                    TextField(value = user.email, onValueChange = { viewModel.updateUser(email = it) })
                    TextField(value = user.surname, onValueChange = { viewModel.updateUser(surname = it) })
                    TextField(value = password, onValueChange = { viewModel.updatePassword(it) })

                    Button(onClick = { viewModel.addUser() }) {
                        Text(text = "Add User")
                    }
                    
                    Button(onClick = { viewModel.getUserUid() }) {
                        Text(text = "Get current user uid")
                    }
                }
            }
        }
    }
}
