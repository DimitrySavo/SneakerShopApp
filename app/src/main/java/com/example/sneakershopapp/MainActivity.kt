package com.example.sneakershopapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Path
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.sneakershopapp.model.Shoe
import com.example.sneakershopapp.model.getImagesUrls
import com.example.sneakershopapp.screens.CartScreen
import com.example.sneakershopapp.screens.ForgotPasswordEnterEmailScreen
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import com.example.sneakershopapp.viewmodel.ShopViewModel
import com.example.sneakershopapp.viewmodel.UserViewModel
import com.example.sneakershopapp.screens.HelloScreensPager
import com.example.sneakershopapp.screens.LoginScreen
import com.example.sneakershopapp.screens.NewPasswordScreen
import com.example.sneakershopapp.screens.OTPCodeCheckScreen
import com.example.sneakershopapp.screens.ProfileScreen
import com.example.sneakershopapp.screens.RegisterScreen
import com.example.sneakershopapp.screens.ShoeScreen
import com.example.sneakershopapp.screens.ShopScreen
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


                    shopViewModel.getShoes()
                    val shoes by shopViewModel.shoes.collectAsState()

                    //userViewModel.logoutUser()
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
                                ForgotPasswordEnterEmailScreen(
                                    navController = navController,
                                    userViewModel = userViewModel
                                )
                            }
                            composable(Paths.OTP) {
                                OTPCodeCheckScreen(
                                    userViewModel = userViewModel,
                                    navController = navController
                                )
                            }
                            composable(Paths.NEW_PASSWORD) {
                                Text(
                                    text = "Placeholder for New password"
                                )
                            }
                        }
                        composable(Paths.STORE) {
                            Column {
                                ShopScreen(list = shoes, navController = navController)
                                Button(
                                    onClick = {
                                        navController.navigate(Paths.INFO)
                                    }
                                ) {
                                    Text(
                                        text = "To profile"
                                    )
                                }
                            }
                        }
                        composable(Paths.SHOE_INFO + "/{ItemId}") { backStackEntry ->
                            val itemId = backStackEntry.arguments?.getString("ItemId")
                            ShoeScreen(shoe = shoes.first{it.id == itemId}, images = listOf("https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/05856ac7-0129-4395-bd6e-2fe2669025fb/custom-nike-dunk-low-by-you-su24.png", "https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/8ce97c97-3cb9-4e7f-86f4-6f39eef4b2b2/W+BLAZER+MID+%2777+NEXT+NATURE.png", "https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/b1bcbca4-e853-4df7-b329-5be3c61ee057/NIKE+DUNK+LOW+RETRO.png", "https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/0e42ff41-41ab-42cb-b8f3-8c42d8ae0d79/NIKE+AIR+MAX+PLUS.png"))
                        }
                        composable(Paths.FAVORITES) {

                        }
                        composable(Paths.CART) {
                            CartScreen(listOfShoes = emptyList())
                        }
                        composable(Paths.CHECKOUT) {

                        }
                        composable(Paths.NOTIFICATIONS) {

                        }
                        composable(Paths.DETAILS) {

                        }
                        navigation(startDestination = Paths.INFO, route = "profile") {
                            composable(Paths.INFO) {
                                ProfileScreen(navController = navController, userViewModel = userViewModel)
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
    const val SHOE_INFO = "shoeInfo"
    const val FAVORITES = "favorites"
    const val CART = "cart"
    const val CHECKOUT = "orderCheckout"
    const val NOTIFICATIONS = "notifications"
    const val DETAILS = "orderDetails"
    const val INFO = "userInfo"
    const val SETTINGS = "settings"
    const val OTP = "otpCode"
    const val NEW_PASSWORD = "newPassword"

    fun getShoeScreen(id: String) : String {
        return Paths.SHOE_INFO + "/$id"
    }
}
