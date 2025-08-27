package com.vaasudev.kmptest._global.controller

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.vaasudev.kmptest.data.data_store.DATA_STORE_FILE_NAME
import com.vaasudev.kmptest.data.data_store.createDataStoreWithPath

actual fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    createDataStoreWithPath(producePath)

fun createDataStore(context: Context): DataStore<Preferences> =
    createDataStore(producePath = { context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath })