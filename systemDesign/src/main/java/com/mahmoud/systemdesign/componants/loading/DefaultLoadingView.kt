package com.mahmoud.systemdesign.componants.loading

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultLoadingView(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(
                width = 250.dp
            )
            .height(
                height = 100.dp
            )
    ) {
        CircularProgressIndicator()
    }

}