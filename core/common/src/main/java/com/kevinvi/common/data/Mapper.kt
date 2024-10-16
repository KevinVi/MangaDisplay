package com.kevinvi.common.data

interface Mapper<T, U : UiModel> {
    fun mapToUi(item: T): U

    fun mapToData(item: U): T
}
