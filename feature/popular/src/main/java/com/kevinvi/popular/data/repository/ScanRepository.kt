package com.kevinvi.scan.data.repository

import androidx.paging.PagingData
import com.kevinvi.scan.data.model.ScanData
import com.kevinvi.scan.data.model.ScanDetailItem
import com.kevinvi.scan.data.model.ScanItem
import com.kevinvi.scan.data.model.ScanItemSingle
import kotlinx.coroutines.flow.Flow

interface ScanRepository {
	suspend fun getMangaByName(name:String): ScanItem
	suspend fun getMangaById(id:String): ScanItemSingle

	suspend fun getLastestChapter(id: String): ScanDetailItem
	suspend fun getLastUpdateList(offset:Int): ScanItem

    suspend fun getPopulare(): Flow<PagingData<ScanData>>

	fun getImage(id:String, covertArt: String)
}