package com.vaasudev.kmptest.domain.model

import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.img_payment_card
import com.vaasudev.kmptest.library.resources.img_payment_cash
import com.vaasudev.kmptest.library.resources.img_payment_scan
import org.jetbrains.compose.resources.DrawableResource

data class PaymentMethods(
    val id: Int,
    val name: String,
    val title: String,
    val description: String,
    val typeId: Int,
    var isDefault: Boolean,
    val icon: DrawableResource,
)

var paymentMethods = listOf(
    PaymentMethods(
        id = 1,
        name = "UPI Scan and Pay",
        title = "Pay for trips scanning QR code",
        description = "At the end of a trip, the driver will use their phone to show you a QR code from SafarCab. Scan it to pay with any UPI app.",
        typeId = 1,
        isDefault = false,
        icon = Res.drawable.img_payment_scan,
    ),
    PaymentMethods(
        id = 1,
        name = "Cash",
        title = "Pay for trip with cash",
        description = "Your drive's phone will show you the amount to pay at the end of the trip.",
        typeId = 2,
        isDefault = true,
        icon = Res.drawable.img_payment_cash,
    ),
    PaymentMethods(
        id = 1,
        name = "Credit or Debit Card",
        title = "Pay for trip with credit or debit card",
        description = "At the end of your trip, the app will automatically show your saved card as the default payment method. You can confirm the payment with a single tap.",
        typeId = 3,
        isDefault = false,
        icon = Res.drawable.img_payment_card,
    ),
)
