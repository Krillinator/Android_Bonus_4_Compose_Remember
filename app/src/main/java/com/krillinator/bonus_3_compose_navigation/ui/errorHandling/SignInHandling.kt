package com.krillinator.bonus_3_compose_navigation.ui.errorHandling

import com.krillinator.bonus_3_compose_navigation.destinations.SignUpPageDestination
import com.krillinator.bonus_3_compose_navigation.ui.models.User
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class SignInHandling {

    fun userExists(user: User, registeredUserList: List<User>, navigator: DestinationsNavigator): Boolean {

        for (i in registeredUserList) {

            // If User Was Found
            if (user == i) {
                navigator.navigate(
                    SignUpPageDestination(
                        username = user.username,
                        password = user.password
                    )
                )
            }
        }

        return false

    }

}