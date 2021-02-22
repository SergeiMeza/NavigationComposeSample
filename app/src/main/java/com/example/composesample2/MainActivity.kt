package com.example.composesample2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.composesample2.ui.theme.ComposeSample2Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSample2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController) },
        bodyContent = { AppBodyContent(navController) }
    )
}

@Composable
fun AppBottomNavigation(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.arguments?.getString(KEY_ROUTE) ?: ""

    if (currentRoute.contains("Team/{teamId}", false)) {
        val teamId = backStackEntry?.arguments?.getString("teamId")
        BottomNavigation {
            AppDataProvider.teamBottomBarList.forEach { screen ->
                BottomNavigationItem(
                    icon = { BottomBarIcon( screen) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            val route = screen.route.replace("{teamId}", teamId ?: "")
                            navController.navigate(route)
                        }
                    },
                    label = {
                        Text(text = screen.title, fontSize = 8.sp)
                    }
                )
            }
        }
    } else {
        BottomNavigation {
            AppDataProvider.mainBottomBarList.forEach { screen ->
                BottomNavigationItem(
                    icon = { BottomBarIcon( screen) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route)
                        }
                    },
                    label = {
                        Text(text = screen.title, fontSize = 8.sp)
                    }
                )
            }
        }
    }

}

@Composable
fun BottomBarIcon(screen: AppScreen) {
    when (screen) {
        AppScreen.Home -> Icon(imageVector = Icons.Filled.Home, contentDescription = null)
        AppScreen.Teams -> Icon(imageVector = Icons.Filled.List, contentDescription = null)
        AppScreen.Me -> Icon(imageVector = Icons.Filled.Person, contentDescription = null)
        AppScreen.Calendar -> Icon(imageVector = Icons.Outlined.Star, contentDescription = null)
        AppScreen.Notes -> Icon(imageVector = Icons.Filled.Notifications, contentDescription = null)
        AppScreen.Homeworks -> Icon(imageVector = Icons.Filled.List, contentDescription = null)
        AppScreen.PracticeSessions -> Icon(imageVector = Icons.Filled.DateRange, contentDescription = null)
        AppScreen.Workplace -> Icon(imageVector = Icons.Filled.Info, contentDescription = null)
    }

}

@Composable
fun AppBodyContent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreen.Home.route) {
        composable(AppScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(AppScreen.Teams.route) {
            TeamsScreen(navController)
        }
        composable(AppScreen.Me.route) {
            Text("Me")
        }
        composable("${AppScreen.Profile.route}/{profileId}") { backStackEntry ->
            val profileId = backStackEntry.arguments?.getString("profileId")!!
            Text(profileId)
        }
        composable("${AppScreen.Team.route}/{teamId}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")!!
            Text(teamId)
        }
        composable("${AppScreen.Calendar.route}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            Text("$teamId/Calendar")
        }
        composable("${AppScreen.Notes.route}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            Text("$teamId/Notes")
        }
        composable("${AppScreen.Homeworks.route}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            Text("$teamId/Homeworks")
        }
        composable("${AppScreen.PracticeSessions.route}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            Text("$teamId/PracticeSessions")
        }
        composable("${AppScreen.Workplace.route}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            Text("$teamId/Workplace")
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val homeScreenData = listOf("${AppScreen.Profile.route}/profile_001", "${AppScreen.Profile.route}/profile_002")
    LazyColumn {
        itemsIndexed(homeScreenData) { _, route ->
            TextButton(onClick = { /*TODO*/ }) {
            Text(text = route)
            }

        }
    }
}


@Composable
fun TeamsScreen(navController: NavHostController) {
    val homeScreenData = listOf("${AppScreen.Team.route}/team_001", "${AppScreen.Team.route}/team_002")
    LazyColumn {
        itemsIndexed(homeScreenData) { _, route ->
            TextButton(onClick = {
                navController.navigate(route)
            }) {
                Text(text = route)
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSample2Theme {
        AppContent()
    }
}