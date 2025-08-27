package com.vaasudev.kmptest.presentation.ride_book

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.domain.enum_classes.LocationType
import com.vaasudev.kmptest.domain.enum_classes.RideState
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideBookScreenOld(
    navController: NavController
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        RideBookScreenContentOld(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideBookScreenContentOld(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val density = LocalDensity.current

    var pickupValue by remember { mutableStateOf("") }
    var dropOffValue by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    var rideState by remember { mutableStateOf(RideState.GetLocationFromSearch) }
    var chooseLocationType by remember { mutableStateOf(LocationType.DropOff) }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            //initialValue = SheetValue.PartiallyExpanded,
            initialValue = SheetValue.Expanded,
            skipHiddenState = true, // Set to true if you don't want a hidden state
            confirmValueChange = { it != SheetValue.Hidden }
        )
    )

    LaunchedEffect(bottomSheetScaffoldState.bottomSheetState.currentValue) {
        println("Current Value: ${bottomSheetScaffoldState.bottomSheetState.currentValue}")
        if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded) {
            localFocusManager.clearFocus()
            if (rideState != RideState.ChooseRideVehicle) {
                rideState = RideState.GetLocationFromDragMap
            }
        } else if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
            rideState = if (pickupValue.isNotEmpty() && dropOffValue.isNotEmpty()) {
                RideState.ChooseRideVehicle
            } else {
                RideState.GetLocationFromSearch
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.img_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.95F)),
        )

        AnimatedVisibility(
            visible = rideState == RideState.ChooseRideVehicle,
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 15.dp)
                    .size(35.dp)
                    .clip(shape = CircleShape)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .clickable(
                        onClick = {
                            rideState = RideState.GetLocationFromSearch
                        },
                        indication = ripple(),
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .padding(5.dp),
                painter = painterResource(Res.drawable.ic_arrow_back),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { localFocusManager.clearFocus() }
                    )
                },
            horizontalAlignment = Alignment.Start
        ) {
            // Example button to show the sheet if hidden
            if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Hidden) {
                Button(onClick = {
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.partialExpand() }
                }, modifier = Modifier.padding(bottom = 16.dp)) {
                    Text("Show Sheet")
                }
            }
        }
    }

    CustomBottomSheetWithStickyFooter()
}