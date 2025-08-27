package com.vaasudev.kmptest.presentation.services

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenRideBook
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.getServicesData
import com.vaasudev.kmptest.presentation.home.RecentLocationItem
import com.vaasudev.kmptest.presentation.home.ServiceType

@Composable
fun ServicesScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ServicesScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun ServicesScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val servicesOffers =  getServicesData.filter{it.isHavePromo}.mapNotNull{it.servicePromoOffer}
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f, pageCount = { servicesOffers.size })

    val promoRideTypes = getServicesData.filter { it.isHavePromo  }
    val withoutPromoRideTypes = getServicesData.filter { !it.isHavePromo  }
    val subPartNonPromoRideTypes = withoutPromoRideTypes.chunked(4)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = "Services",
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD)),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 15.dp),
            text = "Go anywhere, get anything",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD),
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )

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

        Spacer(modifier = Modifier.height(15.dp))

        ServiceType(rideTypeList = promoRideTypes, onClick = {})

        Spacer(modifier = Modifier.height(15.dp))

        repeat(subPartNonPromoRideTypes.size) { index ->
            ServiceType(rideTypeList = subPartNonPromoRideTypes[index], onClick = {
                navController.navigate(ScreenRideBook)
            }, isAutoManage = false)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}