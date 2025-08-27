package com.vaasudev.kmptest.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenRideBook
import com.vaasudev.kmptest.ScreenServices
import com.vaasudev.kmptest.ScreenSignIn
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.getServicesData
import com.vaasudev.kmptest.presentation.services.ServiceOffersItem
import com.vaasudev.kmptest.presentation.widgets.RainAnimation
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(
    navController: NavController,
    onSeeAllClick: () -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        HomeScreenContent(
            navController = navController,
            onSeeAllClick = onSeeAllClick,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun HomeScreenContent(
    navController: NavController,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    var isRaining by remember { mutableStateOf(false) }
    val suggestionsList = getServicesData.subList(0, 4)

    val servicesOffers =  getServicesData.filter{it.isHavePromo}.mapNotNull{it.servicePromoOffer}
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f, pageCount = { servicesOffers.size })

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
        if (isRaining) {
            RainAnimation()
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = "SafarCab",
                style = MaterialTheme.typography.headlineLarge.copy(fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD)),
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(15.dp))
            SearchTextField()
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(2) {
                    RecentLocationItem(onClick = {

                    })
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Suggestions(onClick = onSeeAllClick)
            Spacer(modifier = Modifier.height(8.dp))
            ServiceType(rideTypeList = suggestionsList, onClick = {
                navController.navigate(ScreenRideBook)
            })
            Spacer(modifier = Modifier.height(15.dp))

            Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    pageSpacing = 10.dp,
                    contentPadding = PaddingValues(horizontal = 20.dp),
                ) { virtualPage ->
                    ServiceOffersItem(servicesOffers[virtualPage])
                }
            }
        }
    }
}