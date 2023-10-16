package com.example.dazn.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dazn.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val image: ImageVector) {
    data object Events : Screen("events", R.string.events_screen_name, Icons.Filled.List)
    data object Schedules :
        Screen("schedules", R.string.schedules_screen_name, Icons.Filled.DateRange)
}
