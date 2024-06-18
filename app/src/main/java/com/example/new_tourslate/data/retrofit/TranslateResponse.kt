package com.example.new_tourslate.data.retrofit

import com.google.gson.annotations.SerializedName

data class TranslateResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("originalText")
	val originalText: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("translatedText")
	val translatedText: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
