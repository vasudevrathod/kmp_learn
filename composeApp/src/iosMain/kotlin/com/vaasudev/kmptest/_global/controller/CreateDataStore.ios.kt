@file:OptIn(ExperimentalForeignApi::class)

package com.vaasudev.kmptest._global.controller

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.vaasudev.kmptest.data.data_store.DATA_STORE_FILE_NAME
import com.vaasudev.kmptest.data.data_store.createDataStoreWithPath
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    createDataStoreWithPath(producePath)

fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$DATA_STORE_FILE_NAME"
    }
)