package coding.withze.chapterlimakmtiga.model

import com.google.gson.annotations.SerializedName

data class CarResponse(
	@field:SerializedName("CarResponse")
	val carResponse: List<CarResponseItem>
)

data class CarResponseItem(

	@field:SerializedName("finish_rent_at")
	val finishRentAt: Any,

	@field:SerializedName("start_rent_at")
	val startRentAt: Any,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("category")
	val category: Any,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
