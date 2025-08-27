package com.vaasudev.kmptest.presentation.ride_book

import androidx.compose.animation.Animatable
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaasudev.kmptest._global.controller.getScreenHeightDp
import com.vaasudev.kmptest._global.controller.printLog
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.enum_classes.LocationType
import com.vaasudev.kmptest.domain.enum_classes.RideState
import com.vaasudev.kmptest.domain.model.Services
import com.vaasudev.kmptest.domain.model.getServicesData
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.ic_arrow_back
import com.vaasudev.kmptest.library.resources.ic_person
import com.vaasudev.kmptest.library.resources.ride_cab
import com.vaasudev.kmptest.presentation.home.RecentLocationItem
import com.vaasudev.kmptest.presentation.sign_in.ButtonCustom
import com.vaasudev.kmptest.presentation.widgets.DragMapLocationTextField
import com.vaasudev.kmptest.presentation.widgets.LocationTextField
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetChooseLocation(
    title: String = "Plan your ride",
    onBackClick: () -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null
) {

    var pickupValue by remember { mutableStateOf("") }
    var dropOffValue by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { it != SheetValue.Hidden }
    )

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 40.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { localFocusManager.clearFocus() }
                )
            },
        onDismissRequest = {},
        properties = ModalBottomSheetProperties(
            shouldDismissOnBackPress = true,
        ),
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .clip(shape = CircleShape)
                    .clickable(
                        onClick = onBackClick,
                        indication = ripple(),
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .padding(5.dp),
                painter = painterResource(Res.drawable.ic_arrow_back),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .size(35.dp)
            )
        }

        LocationTextField(
            hint = "Enter pick-up location",
            textValue = pickupValue,
            onValueChange = { newText ->
                if (newText.length <= 40) {
                    pickupValue = newText
                }
            },
            onDone = onDone,
            isFocusedValue = {
                if (it) {
                    keyboardController?.hide()
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        LocationTextField(
            hint = "Enter drop-off location",
            textValue = dropOffValue,
            onValueChange = { newText ->
                if (newText.length <= 40) {
                    dropOffValue = newText
                }
            },
            onDone = onDone,
            isFocusedValue = {
                if (it) {
                    keyboardController?.hide()
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(2) {
                RecentLocationItem(onClick = {

                })
            }
        }
    }
}

fun sheetContent(
    chooseLocationType: LocationType,
    title: String = "Plan your ride",
    pickupValue: String,
    pickUpOnValueChange: (String) -> Unit,
    dropOffValue: String,
    dropOffOnValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    rideState: RideState,
    isFocusedLocationType: (LocationType) -> Unit,
    onSelectLocationClick: () -> Unit,
): @Composable ColumnScope.() -> Unit = {
    printLog("Size", "Height -> ${getScreenHeightDp()}")
    if (rideState == RideState.ChooseRideVehicle) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9F),
            content = sheetContentChooseRideVehicle()
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9F),
            content = when (rideState) {
                RideState.GetLocationFromDragMap -> {
                    sheetContentGetLocationFromDragMap(
                        locationType = chooseLocationType,
                        value = if (chooseLocationType == LocationType.PickUp) pickupValue else dropOffValue,
                        onValueChange = if (chooseLocationType == LocationType.PickUp) pickUpOnValueChange else dropOffOnValueChange,
                        onSelectLocationClick = onSelectLocationClick
                    )
                }

                RideState.GetLocationFromSearch -> {
                    sheetContentGetLocationFromSearch(
                        title = title,
                        pickupValue = pickupValue,
                        pickUpOnValueChange = pickUpOnValueChange,
                        dropOffValue = dropOffValue,
                        dropOffOnValueChange = dropOffOnValueChange,
                        onBackClick = onBackClick,
                        onDone = onDone,
                        isFocusedLocationType = isFocusedLocationType
                    )
                }
                else -> {
                    sheetContentChooseRideVehicle()
                }
            }
        )
    }
}

fun sheetContentGetLocationFromDragMap(
    locationType: LocationType,
    value: String,
    onValueChange: (String) -> Unit,
    onSelectLocationClick: () -> Unit,
): @Composable ColumnScope.() -> Unit = {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = if (locationType == LocationType.PickUp) "Set your pickup spot" else "Set your destination",
        style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
            color = MaterialTheme.colorScheme.onSurface,
        ),
        maxLines = 1,
        overflow = TextOverflow.Clip,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Drag map to move pin",
        style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = fontFamily(font = Fonts.LIGHT),
            color = MaterialTheme.colorScheme.onSurface,
        ),
        maxLines = 1,
        overflow = TextOverflow.Clip,
        textAlign = TextAlign.Center
    )
    HorizontalDivider(
        modifier = Modifier
            .padding(vertical = 10.dp),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.surfaceContainer
    )
    DragMapLocationTextField(
        hint = if (locationType == LocationType.PickUp) "Enter pick-up location" else "Enter drop-off location",
        textValue = value,
        onValueChange = onValueChange /*{ newText ->
                if (newText.length <= 40) {
                    pickupValue = newText
                }
            }*/,
    )
    Spacer(modifier = Modifier.height(10.dp))
    ButtonCustom(
        modifier = Modifier.padding(horizontal = 15.dp),
        title = if (locationType == LocationType.PickUp) "Set pickup spot" else "Set destination",
        onClick = {
            onSelectLocationClick.invoke()
        },
        verticalPadding = 10.dp
    )
}

fun sheetContentGetLocationFromSearch(
    title: String = "Plan your ride",
    pickupValue: String,
    pickUpOnValueChange: (String) -> Unit,
    dropOffValue: String,
    dropOffOnValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    isFocusedLocationType: (LocationType) -> Unit
): @Composable ColumnScope.() -> Unit = {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(35.dp)
                .clip(shape = CircleShape)
                .clickable(
                    onClick = onBackClick,
                    indication = ripple(),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(5.dp),
            painter = painterResource(Res.drawable.ic_arrow_back),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
                color = MaterialTheme.colorScheme.onSurface,
            ),
            maxLines = 1,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .size(35.dp)
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    LocationTextField(
        hint = "Enter pick-up location",
        textValue = pickupValue,
        onValueChange = pickUpOnValueChange /*{ newText ->
            if (newText.length <= 40) {
                pickupValue = newText
            }
        }*/,
        onDone = onDone,
        isFocusedValue = {
            if (it) {
                isFocusedLocationType.invoke(LocationType.PickUp)
            }
        }
    )

    Spacer(modifier = Modifier.height(10.dp))

    LocationTextField(
        hint = "Enter drop-off location",
        textValue = dropOffValue,
        onValueChange = dropOffOnValueChange /*{ newText ->
            if (newText.length <= 40) {
                dropOffValue = newText
            }
        }*/,
        onDone = onDone,
        isFocusedValue = {
            if (it) {
                isFocusedLocationType.invoke(LocationType.DropOff)
            }
        }
    )

    Spacer(modifier = Modifier.height(15.dp))

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(2) {
            RecentLocationItem(onClick = {

            })
        }
    }
}

fun sheetContentChooseRideVehicle(

): @Composable ColumnScope.() -> Unit = {
    val rideTypes =  getServicesData.subList(0, 7)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(rideTypes.size) { index ->
                RideTypeListItem(rideTypes[index])
                if (index < rideTypes.size-1) {
                    HorizontalDivider(color = Color.LightGray)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 20.dp)
        ) {
            ButtonCustom(
                modifier = Modifier.padding(horizontal = 15.dp),
                title =  "Choose Cab" ,
                onClick = {

                },
                verticalPadding = 10.dp
            )
        }
    }
}

@Composable
fun RideTypeListItem(data: Services) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = {},
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.surfaceContainer),
            painter = painterResource(data.icon),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(horizontal = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(Res.drawable.ic_person),
                    contentDescription = null
                )
                Text(
                    text = data.personCapacity.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = fontFamily(font = Fonts.REGULAR),
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = "1 Min",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = fontFamily(font = Fonts.REGULAR),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "$ 20.00",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontFamily(font = Fonts.BOLD),
                color = MaterialTheme.colorScheme.onSurface,
            ),
            maxLines = 1,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheetWithStickyFooter() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    Button(onClick = { showBottomSheet = true }) {
        Text("Show Bottom Sheet")
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                // Scrollable content
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 80.dp) // Padding to avoid overlap with sticky footer
                ) {
                    items(50) {
                        ListItem(
                            headlineContent = { Text("Item $it") },
                            leadingContent = {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_person),
                                    contentDescription = "Favorite"
                                )
                            }
                        )
                    }
                }

                // Sticky Footer
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { /* Handle footer button click */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Sticky Action")
                    }
                }
            }
        }
    }
}

@Composable
fun DraggableBottomSheetWithAnimation(
    chooseLocationType: LocationType,
    title: String = "Plan your ride",
    pickupValue: String,
    pickUpOnValueChange: (String) -> Unit,
    dropOffValue: String,
    dropOffOnValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    isFocusedLocationType: (LocationType) -> Unit,
    onSelectLocationClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val screenHeight = getScreenHeightDp()
    val density = LocalDensity.current

    val sheetHeightDp = getScreenHeightDp()*0.9F
    val sheetHeightPx = with(density) { sheetHeightDp.toPx() }
    val screenHeightPx = with(density) { screenHeight.toPx() }

    val expandedOffset = screenHeightPx - sheetHeightPx
    val collapsedOffset = screenHeightPx - (sheetHeightPx / 3)

    val offsetY = remember { Animatable(collapsedOffset) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.9F)
                .fillMaxWidth()
                .offset { IntOffset(x = 0, y = offsetY.value.toInt()) }
                .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            scope.launch {
                                val target =
                                    if (offsetY.value < (collapsedOffset + expandedOffset) / 2) {
                                        expandedOffset
                                    } else {
                                        collapsedOffset
                                    }
                                offsetY.animateTo(
                                    target,
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        easing = LinearOutSlowInEasing
                                    )
                                )
                            }
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            val newOffset = (offsetY.value + dragAmount.y)
                                .coerceIn(expandedOffset, collapsedOffset)
                            scope.launch {
                                offsetY.snapTo(newOffset)
                            }
                        }
                    )
                }
        ) {
            val progress =
                ((collapsedOffset - offsetY.value) / (collapsedOffset - expandedOffset)).coerceIn(
                    0f,
                    1f
                )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .height(5.dp)
                        .width(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colorScheme.primary)
                )

                Box{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(1f - progress)
                            .scale(1f - 0.1f * progress),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            content = sheetContentGetLocationFromDragMap(
                                locationType = chooseLocationType,
                                value = if (chooseLocationType == LocationType.PickUp) pickupValue else dropOffValue,
                                onValueChange = if (chooseLocationType == LocationType.PickUp) pickUpOnValueChange else dropOffOnValueChange,
                                onSelectLocationClick = onSelectLocationClick
                            )
                        )
                    }


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(progress)
                            .scale(0.9f + 0.1f * progress)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            content = sheetContentGetLocationFromSearch(
                                title = title,
                                pickupValue = pickupValue,
                                pickUpOnValueChange = pickUpOnValueChange,
                                dropOffValue = dropOffValue,
                                dropOffOnValueChange = dropOffOnValueChange,
                                onBackClick = onBackClick,
                                onDone = onDone,
                                isFocusedLocationType = isFocusedLocationType
                            )
                        )
                    }
                }
            }


        }
    }
}



