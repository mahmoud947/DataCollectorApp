package com.mahmoud.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mahmoud.presentation.screens.addUser.AddUserContract
import com.mahmoud.presentation.screens.addUser.AddUserScreen
import com.mahmoud.presentation.screens.addUser.AddUserViewModel
import com.mahmoud.presentation.screens.desplay.DisplayContract
import com.mahmoud.presentation.screens.desplay.DisplayScreen
import com.mahmoud.presentation.screens.desplay.DisplayViewModel
import com.mahmoud.systemdesign.componants.ComposeRootView

@Composable
fun CollectorNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.CollectorGraph
    ) {
        navigation<Route.CollectorGraph>(
            startDestination = Route.AddUser
        ) {
            composable<Route.AddUser>(
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(700)
                    )
                }
            ) {
                ComposeRootView<AddUserContract.State, AddUserContract.Event, AddUserViewModel>(
                    withDefaultLoading = false,
                ) { state, viewModel, isLoading ->
                    AddUserScreen(
                        onNavigateToDisplayScreen = {
                            navController.navigate(Route.DisplayUsers)
                        },
                        state = state.copy(isLoading = isLoading),
                        onEvent = viewModel::setEvent,
                        effect = viewModel.effect
                    )
                }
            }

        }

        composable<Route.DisplayUsers>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(700)
                )
            }
        ) {
            ComposeRootView<DisplayContract.State, DisplayContract.Event, DisplayViewModel>(
                withDefaultLoading = true,
            ) { state, viewModel, isLoading ->
                DisplayScreen(
                    onNavigateToInputScreen = {
                        navController.navigateUp()
                    },
                    state = state,
                    onEvent = viewModel::setEvent,
                )
            }
        }

    }

}
