package com.krillinator.bonus_3_compose_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.krillinator.bonus_3_compose_navigation.ui.composables.LoginCredentials
import com.krillinator.bonus_3_compose_navigation.ui.errorHandling.SignInHandling
import com.krillinator.bonus_3_compose_navigation.ui.models.User
import com.krillinator.bonus_3_compose_navigation.ui.theme.Bonus_3_Compose_NavigationTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainActivity : ComponentActivity() {

    /* TODO - Compose Destinations
    *   --> PDF --> Compose Destinations
    *   --> Gradle setup --> Latest versions
    *   --> SKIP old version
    *   --> PreviewParameter
    *
    *   --> PROPS
    *   --> TextInput and sending data!
    *   --> User.kt EXAMPLE
    *
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bonus_3_Compose_NavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        // withStyledAttributes(ProfileTransitions::class) // TODO - ProfileTransitions
                    )

                }
            }
        }
    }
}

@RootNavGraph(start = true)
@Destination(style = ProfileTransitions::class)
@Composable
fun Home(navigator: DestinationsNavigator) {
    Column {

        var user by remember { mutableStateOf( User("","")) }
        var userExists by remember { mutableStateOf(true) }
        val registeredUserList by remember { mutableStateOf(listOf(
            User("Benny","123"),
            User("Frida","321")
        )) }

        Text(text = stringResource(id = R.string.home_screen_name)) // TODO - Add to Slide
        LoginCredentials(
            user = user,
            onChangeUser = { user = it },
            )

        // TODO - Runs as soon as composable is run
        if (!userExists) {
            Text(text = "Bad Credentials, try again...")
        }

        Button(onClick = {
            if (!SignInHandling().userExists(user, registeredUserList, navigator)) {
                userExists = false
            }
            SignInHandling().userExists(user, registeredUserList, navigator)
        }) {
            Text(text = "Navigate")
        }
        
    }
}

// TODO - username == val?
// TODO - set username param == not allowed?

// TODO - ADD USER IMPLEMENTATION TO SLIDE

@Destination(style = ProfileTransitions::class)
@Composable
fun SignUpPage(username: String, password: String) {

    // Sending Data to --> LoginPage
    val user = User(username, password)

    Column {
        Text(text = "SignUpPage")
        Text(text = "$user")
        Button(onClick = { }) {
            Text(text = "Navigate -> loginPage ")
        }
    }
}

@Destination(style = ProfileTransitions::class)
@Composable
fun LoginPage() {

    Column {
        Text(text = "LoginPage")
        Text(text = "User data: ")
        Button(onClick = { }) {

        }
    }
    
}

@Destination(style = ProfileTransitions::class)
@Composable
fun ThemePage(navController: NavController) {
    Column {
        Text(text = "ThemePage")
        Button(onClick = { /*TODO*/ }) {

        }
    }
}

@Destination
@Composable
fun AboutPage(navController: NavController) {
    Column {
        Text(text = "AboutPage")
        Button(onClick = { /*TODO*/ }) {
        }
    }
}
