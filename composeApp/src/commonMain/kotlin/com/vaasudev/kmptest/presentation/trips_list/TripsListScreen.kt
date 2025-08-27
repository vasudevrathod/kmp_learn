package com.vaasudev.kmptest.presentation.trips_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenTripDetails
import com.vaasudev.kmptest.domain.model.tripsListData
import com.vaasudev.kmptest.presentation.trip_details.TripDetailsScreenContent
import com.vaasudev.kmptest.presentation.widgets.HeaderCommon

@Composable
fun TripsListScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        TripsListScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun TripsListScreenContent(
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
            title = "Trips List",
            onBackClick = {
                navController.popBackStack()
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn {
            items(tripsListData.size) { index ->
                TripsItem(data = tripsListData[index], onClick = { data ->
                    navController.navigate(ScreenTripDetails(tripId = data.id))
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}