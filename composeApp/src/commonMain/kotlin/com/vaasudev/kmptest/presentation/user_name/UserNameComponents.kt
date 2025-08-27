package com.vaasudev.kmptest.presentation.user_name

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily

@Composable
fun UserNameTextField(
    textValue: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null
) {


    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
        /*.focusRequester(focusRequester)
        .onFocusChanged { focusState ->
            isFocused = focusState.isFocused
        }*/,
        value = textValue,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = fontFamily(
                font = Fonts.REGULAR
            )
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = onDone
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(
                            color = if (isFocused) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.surfaceContainer,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textValue.isEmpty()) {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = fontFamily(
                                    font = Fonts.REGULAR
                                )
                            )
                        )
                    }
                    innerTextField() // This draws the actual text field
                }
            }

        }
    )
}