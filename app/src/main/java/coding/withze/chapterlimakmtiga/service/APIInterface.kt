package coding.withze.chapterlimakmtiga.service

import coding.withze.chapterlimakmtiga.model.CarResponseItem
import coding.withze.chapterlimakmtiga.model.PostCarResponse
import coding.withze.chapterlimakmtiga.model.UserResponseItem
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @GET("admin/car")
    fun getAllCar() : Call<List<CarResponseItem>>

    @GET("admin/car/{id}")
    fun getCarById(@Path("id") id : Int) : Call<PostCarResponse>

    @FormUrlEncoded
    @POST("admin/car")
    fun addCar(
        @Field("name") name: String,
        @Field("category") stock: String,
        @Field("price") price: Int,
        @Field("status") status : Boolean,
        @Field("image") image: String
    ) : Call<PostCarResponse>

    @FormUrlEncoded
    @PUT("admin/car/{id}")
    fun updateCar(@Path("id") id: Int,
                  @Field("name") name: String,
                  @Field("category") stock: String,
                  @Field("price") price: Int,
                  @Field("status") status : Boolean,
                  @Field("image") image: String) : Call<PostCarResponse>

    @GET("user")
    fun getUser() : Call<List<UserResponseItem>>
}