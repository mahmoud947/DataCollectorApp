package com.mahmoud.systemdesign.componants.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoud.systemdesign.componants.text.CText
import com.mahmoud.systemdesign.utils.horizontalGradientBG


@Composable
fun CButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .height(TextFieldDefaults.MinHeight)
            .width(TextFieldDefaults.MinWidth)
            .clip(RoundedCornerShape(8.dp))
            .horizontalGradientBG(
                alpha = 1f,
                enabled = enabled,
            )
            .clickable(
                enabled = !isLoading && enabled,
            ) {
                if (!isLoading && enabled) {
                    onClick()
                }
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CText(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            AnimatedVisibility(
                visible = isLoading,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .width(24.dp)
                        .height(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp,
                )
            }

        }
    }
}