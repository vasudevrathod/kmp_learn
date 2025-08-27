package com.vaasudev.kmptest.domain.utility

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

// Example of converting a specific timestamp from milliseconds
@OptIn(ExperimentalTime::class)
fun formatTimestamp(timestampMillis: Long): String {
    val instant = Instant.fromEpochMilliseconds(timestampMillis)
    val datetimeInUtc = instant.toLocalDateTime(TimeZone.UTC)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault()) // GMT

    val customFormat = LocalDateTime.Format {
        // 'dd': Day of the month with zero padding
        day(padding = Padding.ZERO)
        char(' ')
        // 'MMM': The abbreviated month name (e.g., "Aug")
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        char('-')
        char(' ')
        // 'hh': The hour of the day (00-23) with zero padding
        //hour(padding = Padding.ZERO)
        //char(':')
        // 'mm': The minute of the hour with zero padding
        //minute(padding = Padding.ZERO)
        //char(' ')
        amPmHour(padding = Padding.ZERO)
        char(':')
        minute(padding = Padding.ZERO)
        char(' ')
        amPmMarker("AM", "PM")
    }
    return datetimeInUtc.format(customFormat)
}

@OptIn(ExperimentalTime::class)
fun formatTimestampToDate(timestampMillis: Long, dateFormats: DateFormats): String {
    val instant = Instant.fromEpochMilliseconds(timestampMillis)
    val datetimeInUtc = instant.toLocalDateTime(TimeZone.UTC)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault()) // GMT

    val customFormat = getDateFormator(dateFormats)
    return datetimeInUtc.format(customFormat)
}

fun getDateFormator(dateFormats: DateFormats): DateTimeFormat<LocalDateTime> {
    return when(dateFormats) {
        DateFormats.DD_MMM_HH_MM_AM_PM -> {
            LocalDateTime.Format {
                // 'dd': Day of the month with zero padding
                day(padding = Padding.ZERO)
                char(' ')
                // 'MMM': The abbreviated month name (e.g., "Aug")
                monthName(MonthNames.ENGLISH_ABBREVIATED)
                char(' ')
                char('-')
                char(' ')
                // 'hh': The hour of the day (00-23) with zero padding
                //hour(padding = Padding.ZERO)
                //char(':')
                // 'mm': The minute of the hour with zero padding
                //minute(padding = Padding.ZERO)
                //char(' ')
                amPmHour(padding = Padding.ZERO)
                char(':')
                minute(padding = Padding.ZERO)
                char(' ')
                amPmMarker("AM", "PM")
            }
        }
        DateFormats.DD_MMM_YYYY_HH_MM_AM_PM -> {
            LocalDateTime.Format {
                // 'dd': Day of the month with zero padding
                day(padding = Padding.ZERO)
                char(' ')
                // 'MMM': The abbreviated month name (e.g., "Aug")
                monthName(MonthNames.ENGLISH_ABBREVIATED)
                char(' ')
                year(padding = Padding.ZERO)
                char(',')
                char(' ')
                amPmHour(padding = Padding.ZERO)
                char(':')
                minute(padding = Padding.ZERO)
                char(' ')
                amPmMarker("AM", "PM")
            }
        }
        DateFormats.AM_PM -> {
            LocalDateTime.Format {
                amPmHour(padding = Padding.ZERO)
                char(':')
                minute(padding = Padding.ZERO)
                char(' ')
                amPmMarker("AM", "PM")
            }
        }
    }
}

enum class DateFormats {
    DD_MMM_HH_MM_AM_PM,
    DD_MMM_YYYY_HH_MM_AM_PM,
    AM_PM
}