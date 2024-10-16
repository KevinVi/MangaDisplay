package com.kevinvi.common

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(api = VERSION_CODES.S)
fun supportDynamicTheme() = isAtLeastS()

@ChecksSdkIntAtLeast(api = VERSION_CODES.UPSIDE_DOWN_CAKE)
fun isAtLeastU() = SDK_INT compareTo VERSION_CODES.UPSIDE_DOWN_CAKE

@ChecksSdkIntAtLeast(api = VERSION_CODES.TIRAMISU)
fun isAtLeastT() = SDK_INT compareTo VERSION_CODES.TIRAMISU

@ChecksSdkIntAtLeast(api = VERSION_CODES.S)
fun isAtLeastS() = SDK_INT compareTo VERSION_CODES.S

@ChecksSdkIntAtLeast(api = VERSION_CODES.R)
fun isAtLeastR() = SDK_INT compareTo VERSION_CODES.R
