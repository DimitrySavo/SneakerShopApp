package com.example.sneakershopapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Path
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.sneakershopapp.screens.ForgotPasswordEnterEmailScreen
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.ShopViewModel
import com.example.sneakershopapp.viewmodel.UserViewModel
import com.example.sneakershopapp.screens.HelloScreensPager
import com.example.sneakershopapp.screens.LoginScreen
import com.example.sneakershopapp.screens.OTPCodeCheckScreen
import com.example.sneakershopapp.screens.RegisterScreen
import com.example.sneakershopapp.ui.theme.ProvidePadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ProvidePadding {
                SneakerShopAppTheme {
                    Log.i("Before init first viewModel:", "All good")
                    val userViewModel = viewModel<UserViewModel>()
                    val shopViewModel = viewModel<ShopViewModel>()
                    userViewModel.logoutUser()
                    val startDestination =
                        if (userViewModel.isUserRegistered()) Paths.STORE else Paths.HELLO

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(Paths.HELLO) {
                            HelloScreensPager(navController = navController)
                        }
                        navigation(startDestination = Paths.LOGIN, route = "auth") {
                            composable(Paths.LOGIN) {
                                LoginScreen(
                                    navController = navController,
                                    userViewModel = userViewModel
                                )
                            }
                            composable(Paths.REGISTER) {
                                RegisterScreen(
                                    navController = navController,
                                    userViewModel = userViewModel
                                )
                            }
                            composable(Paths.FORGOT_PASSWORD) {
                                ForgotPasswordEnterEmailScreen(navController = navController, userViewModel = userViewModel)
                            }
                            composable(Paths.OTP) {
                                OTPCodeCheckScreen()
                            }
                        }
                        composable(Paths.STORE) {
                            Text(
                                text = "Store placeholder for now"
                            )
                        }
                        composable(Paths.FAVORITES) {

                        }
                        composable(Paths.CART) {

                        }
                        composable(Paths.CHECKOUT) {

                        }
                        composable(Paths.NOTIFICATIONS) {

                        }
                        composable(Paths.DETAILS) {

                        }
                        navigation(startDestination = Paths.INFO, route = "profile") {
                            composable(Paths.INFO) {

                            }
                            composable(Paths.SETTINGS) {

                            }
                        }
                    }
                }
            }
        }
    }
}

object Paths {
    const val HELLO = "hello"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGOT_PASSWORD = "forgotPassword"
    const val STORE = "store"
    const val FAVORITES = "favorites"
    const val CART = "cart"
    const val CHECKOUT = "orderCheckout"
    const val NOTIFICATIONS = "notifications"
    const val DETAILS = "orderDetails"
    const val INFO = "userInfo"
    const val SETTINGS = "settings"
    const val OTP = "otpCode"
}
