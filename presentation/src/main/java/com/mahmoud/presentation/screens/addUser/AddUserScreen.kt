package com.mahmoud.presentation.screens.addUser

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahmoud.domain.enums.Gender
import com.mahmoud.presentation.R
import com.mahmoud.systemdesign.componants.buttons.CButton
import com.mahmoud.systemdesign.componants.dropdown.CDropdownList
import com.mahmoud.systemdesign.componants.dropdown.DropdownItem
import com.mahmoud.systemdesign.componants.textField.CTextField
import com.mahmoud.systemdesign.utils.Margin

@Composable
fun AddUserScreen(
    onNavigateToDisplayScreen: () -> Unit,

) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToDisplayScreen,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "View Users List"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.user_information),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CTextField(
                        value = "",
                        onValueChange = {  },
                        placeholder = stringResource(R.string.full_name)
                    )

                    CTextField(
                        value = "12",
                        onValueChange = { },
                        placeholder = stringResource(R.string.age),
                        keyboardType = KeyboardType.Number
                    )

                    CTextField(
                        value = "",
                        onValueChange = {  },
                        placeholder = stringResource(R.string.job_title)
                    )


                    CDropdownList(
                        items = listOf(GenderItem(label = "Male"), GenderItem(label = "Female")),
                        selectedItem = GenderItem(label = "Male"),
                        onItemSelected = {}
                    )

                    Margin(8.dp)

                    CButton(
                        text = stringResource(R.string.save_user),
                        onClick = { },
                        enabled = true,
                        isLoading = false
                    )
                }
            }

            AnimatedVisibility (
                visible = false,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = "User saved successfully!",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}

data class GenderItem(
    override val label: String
): DropdownItem


@Preview
@Composable
private fun AddUserScreePrev() {
    AddUserScreen {  }
}