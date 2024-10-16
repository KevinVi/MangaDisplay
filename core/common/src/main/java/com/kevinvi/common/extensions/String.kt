package com.kevinvi.common.extensions

val String.Companion.empty: String
    get() = ""

fun String?.takeIfNotNullOrBlank() = takeUnless { this.isNullOrBlank() }
