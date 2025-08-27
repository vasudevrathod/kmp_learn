package com.vaasudev.kmptest.presentation.user_info

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenUserEmail
import com.vaasudev.kmptest.ScreenUserGender
import com.vaasudev.kmptest.ScreenUserName
import com.vaasudev.kmptest.ScreenUserPhoneNumber
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.personalInfo
import com.vaasudev.kmptest.presentation.widgets.AuthTitle
import com.vaasudev.kmptest.presentation.widgets.HeaderCommon

@Composable
fun UserInfoScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        UserInfoScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun UserInfoScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
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

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            AuthTitle("Personal Info")
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 5.dp),
                text = "We use your information to enhance safety, improve marketing efforts, and perform analytics.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = fontFamily(font = Fonts.REGULAR),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
            ) {
                items(personalInfo.size) { index ->
                    DetailsItem(
                        personalInfo = personalInfo[index],
                        onClick = {
                            redirectionsProfileInfo(
                                navController = navController,
                                profileInfoId = personalInfo[index].id
                            )
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2F)
                    )
                }
            }

        }

    }
}

fun redirectionsProfileInfo(
    navController: NavController,
    profileInfoId: Int,
) {
    when (profileInfoId) {
        1 -> {navController.navigate(ScreenUserName)}
        2 -> {navController.navigate(ScreenUserGender)}
        3 -> {navController.navigate(ScreenUserPhoneNumber)}
        4 -> {navController.navigate(ScreenUserEmail)}
    }
}