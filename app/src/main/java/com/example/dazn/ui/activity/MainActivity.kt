package com.example.dazn.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dazn.ui.navigation.Screen
import com.example.dazn.ui.screens.event.EventsScreen
import com.example.dazn.ui.screens.player.VideoPlayer
import com.example.dazn.ui.screens.schedule.SchedulesScreen
import com.example.dazn.ui.theme.DAZNTheme

@OptIn(ExperimentalMaterial3Api::class)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DAZNTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val items = listOf(
                        Screen.Events,
                        Screen.Schedules,
                    )

                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
                                val navBackStackEntry =
                                    navController.currentBackStackEntryAsState().value
                                val currentDestination = navBackStackEntry?.destination
                                items.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(
                                                screen.image,
                                                contentDescription = null
                                            )
                                        },
                                        label = { Text(stringResource(screen.resourceId)) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id)
                                                launchSingleTop = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = Screen.Events.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.Events.route) { EventsScreen(navController = navController) }
                            composable(Screen.Schedules.route) { SchedulesScreen() }
                            composable(
                                "video/{url}",
                                arguments = listOf(navArgument("url") { type = NavType.StringType })
                            ) { navBackStackEntry ->
                                val urlValue = navBackStackEntry.arguments?.getString("url")
                                urlValue?.let { url -> VideoPlayer(url) }
                            }
                        }
                    }
                }
            }
        }
    }
}
