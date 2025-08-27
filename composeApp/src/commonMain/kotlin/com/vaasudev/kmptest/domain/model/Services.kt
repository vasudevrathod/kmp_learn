package com.vaasudev.kmptest.domain.model

import androidx.compose.ui.graphics.Color
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class Services(
    val id: Int,
    val title: String,
    val icon: DrawableResource,
    val isHavePromo: Boolean = false,
    val personCapacity: Int,
    val servicePromoOffer: ServicesOffer? = null
)

data class ServicesOffer(
    val id: Int,
    val offer: String,
    val image: DrawableResource,
    val bgColor: Color
)

val getServicesData = listOf(
    Services(
        id = 1,
        title = "Moto",
        icon = Res.drawable.ride_moto,
        personCapacity = 1,
        isHavePromo = false,
    ),
    Services(
        id = 2,
        title = "Rickshaw",
        icon = Res.drawable.ride_rickshaw,
        personCapacity = 3,
        isHavePromo = true,
        servicePromoOffer = ServicesOffer(
            id = 1,
            offer = "Ride Smart with 3% Off on All Taxi & Rickshaw Trips!",
            image = Res.drawable.img_rickshaw_offer,
            bgColor = Color(0xFFFAE5D0)
        ),
    ),
    Services(
        id = 3,
        title = "Cab",
        icon = Res.drawable.ride_cab,
        personCapacity = 4,
        isHavePromo = true,
        servicePromoOffer = ServicesOffer(
            id = 2,
            offer = "Ride & Save: Get 5% Off on Your Taxi Fare!",
            image = Res.drawable.img_taxi_offer,
            bgColor = Color(0xFFFFFFFF)
        ),
    ),
    Services(
        id = 4,
        title = "Services",
        icon = Res.drawable.ride_services,
        personCapacity = 20,
        isHavePromo = false,
    ),
    Services(
        id = 5,
        title = "Schedule",
        icon = Res.drawable.ride_schedule,
        personCapacity = 4,
        isHavePromo = false,
    ),
    Services(
        id = 6,
        title = "Intercity",
        icon = Res.drawable.ride_intercity,
        personCapacity = 4,
        isHavePromo = false,
    ),
    Services(
        id = 7,
        title = "Luxury",
        icon = Res.drawable.ride_luxury,
        personCapacity = 2,
        isHavePromo = false,
    ),
    Services(
        id = 8,
        title = "Parcel",
        icon = Res.drawable.ride_parcel,
        personCapacity = 1,
        isHavePromo = true,
        servicePromoOffer = ServicesOffer(
            id = 3,
            offer = "Parcel Now & Enjoy 2% Instant Cashback!",
            image = Res.drawable.img_parcel_offer,
            bgColor = Color(0xFF4ECDC4)
        ),
    ),
)
