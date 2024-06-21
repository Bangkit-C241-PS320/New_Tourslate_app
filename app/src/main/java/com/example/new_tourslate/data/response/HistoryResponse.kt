package com.example.new_tourslate.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)


data class History(

	@field:SerializedName("originalText")
	val originalText: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("translatedText")
	val translatedText: String? = null,

	@field:SerializedName("id")
	val id: String
)
	@Entity(tableName = "history")
	data class DataItem(
		@PrimaryKey
		@field:SerializedName("id")
		val id: String,

		@field:SerializedName("history")
		val history: History? = null
	)
