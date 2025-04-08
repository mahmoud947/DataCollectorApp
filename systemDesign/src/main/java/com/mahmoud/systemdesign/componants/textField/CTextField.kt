package com.mahmoud.systemdesign.componants.textField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoud.systemdesign.componants.text.CText

@Composable
fun CTextField(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFE8ECF4),
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor: Color = Color(0xFFE8ECF4),
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    errorMessage: String? = "",
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isPassword: Boolean = false,
    trailingIcon: ImageVector? = null,
    leadingIcon: ImageVector? = null,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = modifier.height(56.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
            ),
            placeholder = {
                if (placeholder.isNotEmpty()) {
                    CText(
                        text = placeholder,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
            },
            value = value,
            readOnly = readOnly,
            onValueChange = onValueChange,
            isError = isError,
            singleLine = singleLine,
            maxLines = maxLines,
            enabled = enabled,
            visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPassword) {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null,
                        )
                    }
                } else {
                    if (trailingIcon != null) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                        )
                    }
                }
            },
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            } else null,
            shape = RoundedCornerShape(8.dp),
        )
        if (isError) {
            CText(
                modifier = Modifier.padding(top = 4.dp),
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }

}