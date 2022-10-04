package coding.withze.chapterlimakmtiga.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("UserResponse")
	val userResponse: List<UserResponseItem?>? = null
)

data class UserResponseItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("username")
	val username: String? = null
)
