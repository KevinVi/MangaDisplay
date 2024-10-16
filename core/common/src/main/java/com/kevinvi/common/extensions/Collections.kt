package com.kevinvi.common.extensions

fun <T> Iterable<T>?.toListOrEmpty(): List<T> = this?.toList() ?: emptyList()