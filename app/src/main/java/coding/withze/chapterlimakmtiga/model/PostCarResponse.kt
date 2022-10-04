package coding.withze.chapterlimakmtiga.model

import com.google.gson.annotations.SerializedName

data class PostCarResponse(

	@field:SerializedName("finish_rent_at")
	val finishRentAt: Any? = null,

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("start_rent_at")
	val startRentAt: Any? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
