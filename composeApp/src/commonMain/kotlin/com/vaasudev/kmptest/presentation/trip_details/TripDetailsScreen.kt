package com.vaasudev.kmptest.presentation.trip_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.domain.model.TripsListData
import com.vaasudev.kmptest.domain.model.tripsListData
import com.vaasudev.kmptest.domain.utility.DateFormats
import com.vaasudev.kmptest.domain.utility.formatTimestampToDate
import com.vaasudev.kmptest.presentation.widgets.HeaderCommon

@Composable
fun TripDetailsScreen(
    navController: NavController,
    tripId: Int
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        TripDetailsScreenContent(
            navController = navController,
            tripId = tripId,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailsScreenContent(
    navController: NavController,
    tripId: Int,
    modifier: Modifier = Modifier,
) {

    val tripData: TripsListData = tripsListData.first { it.id == tripId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.Start
    ) {
        HeaderCommon(
            title = "Trip details",
            onBackClick = {
                navController.popBackStack()
            }
        )
        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn {
            item {

                RiderTypeName(
                    rideIcon = tripData.serviceData.icon,
                    rideType = tripData.serviceData.title,
                    driverName = tripData.driverName,
                    rideStatusId = tripData.statusId,
                    cancelTripFee = tripData.cancelTripFee
                )

                Spacer(modifier = Modifier.height(15.dp))

                DateTimePrice(value =formatTimestampToDate(
                    timestampMillis = tripData.bookDateTime,
                    dateFormats = DateFormats.DD_MMM_YYYY_HH_MM_AM_PM))
                DateTimePrice(value = "$ ${tripData.tripPrice}")

                if (tripData.statusId == 4) {
                    Spacer(modifier = Modifier.height(15.dp))
                    CancelledTag()
                }

                Spacer(modifier = Modifier.height(15.dp))

                ReceiptInvoice()

                Spacer(modifier = Modifier.height(15.dp))

                LocationDetails(
                    pickUpLocation = "${tripData.pickUpLocation}, ${tripData.pickUpAddress}",
                    pickUpTime = formatTimestampToDate(
                        timestampMillis = tripData.startDateTime,
                        dateFormats = DateFormats.AM_PM),
                    dropOffLocation = "${tripData.dropOffLocation}, ${tripData.dropOffAddress}",
                    dropOffTime = formatTimestampToDate(
                        timestampMillis = tripData.endDateTime,
                        dateFormats = DateFormats.AM_PM)
                )

                Spacer(modifier = Modifier.height(15.dp))

                TipsRating(
                    tipped = tripData.tipped,
                    rating = tripData.rating
                )

                Spacer(modifier = Modifier.height(15.dp))

                CustomerSupport()
            }
        }
    }
}