package com.vaasudev.kmptest.presentation.payment_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.vaasudev.kmptest.domain.model.paymentMethods
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.ic_payment
import com.vaasudev.kmptest.presentation.sign_in.ButtonCustom
import com.vaasudev.kmptest.presentation.widgets.AuthTitle
import com.vaasudev.kmptest.presentation.widgets.HeaderCommon
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun PaymentInfoScreen(
    navController: NavController,
    paymentTypeId: Int
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        PaymentInfoScreenContent(
            navController = navController,
            paymentTypeId = paymentTypeId,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun PaymentInfoScreenContent(
    navController: NavController,
    paymentTypeId: Int,
    modifier: Modifier
) {

    val data = paymentMethods.find { it.typeId == paymentTypeId }
    var isReadyToSave by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1000)
        isReadyToSave = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.Start
    ) {
        HeaderCommon(
            title = "SafarCab Payment",
            onBackClick = {
                navController.popBackStack()
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AuthTitle(data?.name ?: "")
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(data?.icon ?: Res.drawable.ic_payment),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                text = data?.title ?: "",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = fontFamily(font = Fonts.BOLD),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                text = data?.description ?: "",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = fontFamily(font = Fonts.REGULAR),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )

            data?.let {
                if (!it.isDefault) {
                    Spacer(modifier = Modifier.height(30.dp))
                    ButtonCustom(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        title = "Make Default",
                        onClick = {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("typeId", paymentTypeId)
                            navController.popBackStack()
                        },
                        verticalPadding = 10.dp,
                        enabled = isReadyToSave
                    )
                }
            }


        }
    }
}