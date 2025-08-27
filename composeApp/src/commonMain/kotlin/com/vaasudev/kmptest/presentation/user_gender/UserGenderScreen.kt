package com.vaasudev.kmptest.presentation.user_gender

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.genders
import com.vaasudev.kmptest.presentation.sign_in.ButtonCustom
import com.vaasudev.kmptest.presentation.widgets.AuthTitle
import com.vaasudev.kmptest.presentation.widgets.HeaderCommon

@Composable
fun UserGenderScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        UserGenderScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun UserGenderScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    var selectedIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.Start
    ) {
        HeaderCommon(
            title = "SafarCab Account",
            onBackClick = {
                navController.popBackStack()
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        AuthTitle("Choose your gender")
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 5.dp),
            text = "Select the option that best represent your gender.",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontFamily(font = Fonts.REGULAR),
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            items(genders.size) { index ->
                val isSelected = index == selectedIndex
                GenderItem(
                    gender = genders[index],
                    isSelected = isSelected,
                    onClick = {
                        selectedIndex = index
                    }
                )

                if (index != (genders.size - 1)) {
                    HorizontalDivider(
                        modifier = Modifier,
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2F)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(
            thickness = 3.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1F)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 5.dp),
            text = "How we use your gender data",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontFamily(font = Fonts.BOLD),
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 5.dp),
            text = "Your gender information will not be shared with driver and may be used for safety features, personalisation of ads and marketing, and user experience research, which can be managed in Account Settings.",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontFamily(font = Fonts.REGULAR),
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )

        Spacer(modifier = Modifier.height(50.dp))
        ButtonCustom(
            modifier = Modifier.padding(horizontal = 15.dp),
            title = "Submit",
            onClick = {
                navController.popBackStack()
            },
            verticalPadding = 10.dp
        )
    }
}