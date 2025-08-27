package com.vaasudev.kmptest._global.controller

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect fun createDataStore(producePath: () -> String): DataStore<Preferences>