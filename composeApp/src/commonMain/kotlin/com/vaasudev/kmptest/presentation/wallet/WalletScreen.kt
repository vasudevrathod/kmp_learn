package com.vaasudev.kmptest.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenPaymentInfo
import com.vaasudev.kmptest._global.controller.printLog
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.paymentMethods
import com.vaasudev.kmptest.presentation.sign_in.ButtonCustom
import com.vaasudev.kmptest.presentation.widgets.AuthTitle
import com.vaasudev.kmptest.presentation.widgets.CurrencyText
import com.vaasudev.kmptest.presentation.widgets.HeaderCommon

@Composable
fun WalletScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        WalletScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun WalletScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val paymentMethodsNew = remember { mutableStateOf(paymentMethods) }
    val newNameResult = savedStateHandle?.get<Int>("typeId")
    LaunchedEffect(newNameResult) {
        newNameResult?.let { typeId ->
            printLog("Result", "TypeId - $typeId")
        }
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(15.dp))
                AuthTitle("Wallet")
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .clickable(
                            onClick = {},
                            indication = ripple(),
                            interactionSource = remember { MutableInteractionSource() }
                        )
                        .padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Safar Cash",
                        style = MaterialTheme.typography.titleMedium.copy(fontFamily = fontFamily(font = Fonts.BOLD)),
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    CurrencyText(
                        amount = 30
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                ButtonCustom(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    title = "Credit Wallet",
                    onClick = {

                    },
                    verticalPadding = 10.dp,
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    modifier = Modifier
                        .padding(horizontal = 15.dp),
                    text = "Payment Methods",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = fontFamily(font = Fonts.BOLD),
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                )

                Spacer(modifier = Modifier.height(5.dp))
            }

            items(paymentMethodsNew.value.size) { index ->
                PaymentMethodsItem(
                    data = paymentMethodsNew.value[index],
                    onClick = { data ->
                        navController.navigate(ScreenPaymentInfo(paymentTypeId = data.typeId))
                    }
                )
                if (index < paymentMethodsNew.value.size -1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2F)
                    )
                }
            }
        }
    }
}