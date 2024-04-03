package com.krillinator.bonus_3_compose_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.core.content.withStyledAttributes
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.krillinator.bonus_3_compose_navigation.destinations.SignUpPageDestination
import com.krillinator.bonus_3_compose_navigation.ui.theme.Bonus_3_Compose_NavigationTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

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

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(text = stringResource(id = R.string.home_screen_name)) // TODO - Add to Slide
        LoginCredentials(
            username = username,
            password = password,
            onChangeUsername = { username = it },
            onChangePassword = { password = it }
            )

        Button(onClick = {
            navigator.navigate(
                SignUpPageDestination(
                    username = username,
                    password = password
                )
            )
        }) {
            Text(text = "Navigate")
        }
        
    }
}

// TODO - username == val?
// TODO - set username param == not allowed?

// TODO - ADD TO SLIDE

@Composable
fun LoginCredentials(
    username: String,
    password: String,
    onChangeUsername: (String) -> Unit,
    onChangePassword: (String) -> Unit
) {

    Column {
        OutlinedTextField(
            value = username,
            onValueChange = onChangeUsername,
            label = { Text(text = "Username") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = onChangePassword,
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )
    }
}


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
