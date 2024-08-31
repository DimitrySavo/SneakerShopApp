package com.example.sneakershopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.ShopViewModel
import com.example.sneakershopapp.viewmodel.UserViewModel
import com.example.sneakershopapp.screens.HelloScreensPager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            SneakerShopAppTheme {
                val userViewModel = viewModel<UserViewModel>()
                val shopViewModel = viewModel<ShopViewModel>()
                val startDestination = if(userViewModel.isUserRegistered()) "store" else "hello"

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = startDestination){
                    composable("hello"){
                        HelloScreensPager(navController = navController)
                    }
                    navigation(startDestination = "login", route = "auth"){
                        composable("login"){

                        }
                        composable("register"){

                        }
                        composable("forgotPassword"){

                        }
                    }
                    composable("store"){

                    }
                    composable("favorites"){

                    }
                    composable("cart"){

                    }
                    composable("orderCheckout"){

                    }
                    composable("notifications"){

                    }
                    composable("orderDetails"){

                    }
                    navigation(startDestination = "userInfo", route = "profile"){
                        composable("userInfo"){

                        }
                        composable("settings"){

                        }
                        composable("changeUserInfo"){

                        }
                    }
                }
            }
        }
    }
}
