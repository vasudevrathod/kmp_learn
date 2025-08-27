package com.vaasudev.kmptest.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun NumberTextField(
    textValue: String,
    onValueChange: (String) -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null
) {

    BasicTextField(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        value = textValue,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = fontFamily(
                font = Fonts.REGULAR
            )
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
                Text(
                    modifier = Modifier
                        .width(80.dp)
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceContainer
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    text = "+91",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = fontFamily(
                            font = Fonts.REGULAR
                        )
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceContainer
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textValue.isEmpty()) {
                        Text(
                            text = "Mobile Number",
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

@Composable
fun EmailTextField(
    textValue: String,
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = fontFamily(
                font = Fonts.REGULAR
            ),),
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
                            text = "user@example.com",
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

@Composable
fun LocationTextField(
    hint: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    isFocusedValue: (Boolean) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
        .focusRequester(focusRequester)
        .onFocusChanged { focusState ->
            isFocused = focusState.isFocused
            isFocusedValue.invoke(isFocused)
        },
        value = textValue,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = fontFamily(
                font = Fonts.REGULAR
            ),),
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

@Composable
fun DragMapLocationTextField(
    hint: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        enabled = true,
        value = textValue,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = fontFamily(
                font = Fonts.REGULAR
            ),),
        keyboardActions = KeyboardActions(
            onDone = onDone
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(Res.drawable.ic_radio_check),
                    contentDescription = null,
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1F)
                        .padding(horizontal = 15.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textValue.isEmpty()) {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = fontFamily(
                                    font = Fonts.REGULAR
                                ),
                            )
                        )
                    }
                    innerTextField() // This draws the actual text field
                }
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = null,
                )
            }

        }
    )
}