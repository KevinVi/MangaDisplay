package com.kevinvi.common.extensions

import android.content.Intent
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent.getParcelableCompat(key: String): T? = when {
    isAtLeastT() -> getParcelableExtra(key, T::class.java)
    else -> {
        @Suppress("DEPRECATION")
        getParcelableExtra(key)
    }
}
