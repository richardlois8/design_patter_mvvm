package coding.withze.chapterlimakmtiga.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coding.withze.chapterlimakmtiga.model.CarResponseItem
import coding.withze.chapterlimakmtiga.model.PostCarResponse
import coding.withze.chapterlimakmtiga.service.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelCar : ViewModel() {
    lateinit var livedataCar : MutableLiveData<List<CarResponseItem>>
    lateinit var livedataGetCarById : MutableLiveData<PostCarResponse>
    lateinit var addLiveDataCar : MutableLiveData<PostCarResponse>
    lateinit var updateLiveDataCar : MutableLiveData<PostCarResponse>

    init {
        livedataCar = MutableLiveData()
        livedataGetCarById = MutableLiveData()
        addLiveDataCar = MutableLiveData()
        updateLiveDataCar = MutableLiveData()
    }

    fun getLiveDataCar() : MutableLiveData<List<CarResponseItem>>{
        return livedataCar
    }

    fun getLiveDataGetCarById() : MutableLiveData<PostCarResponse>{
        return livedataGetCarById
    }

    fun postLiveDataCar() : MutableLiveData<PostCarResponse>{
        return addLiveDataCar
    }

    fun updateLiveDataCar() : MutableLiveData<PostCarResponse>{
        return updateLiveDataCar
    }

    fun getCarApi(){
        val client = APIClient.instance.getAllCar()
        client.enqueue(object : Callback<List<CarResponseItem>> {
            override fun onResponse(
                call: Call<List<CarResponseItem>>,
                response: Response<List<CarResponseItem>>
            ) {
                if(response.isSuccessful){
                    val data = response.body()
                    if(data != null){
                        livedataCar.postValue(data)
                    }
                }else{
                    Log.e("Error", response.message())
                    livedataCar.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<CarResponseItem>>, t: Throwable) {
                Log.e("Error", t.message.toString())
                livedataCar.postValue(null)
            }

        })
    }

    fun getCarById(id : Int){
        val client = APIClient.instance.getCarById(id)
        client.enqueue(object : Callback<PostCarResponse> {
            override fun onResponse(
                call: Call<PostCarResponse>,
                response: Response<PostCarResponse>
            ) {
                if(response.isSuccessful){
                    val data = response.body()
                    if(data != null){
                        livedataGetCarById.postValue(data)
                    }
                }else{
                    Log.e("Error", response.message())
                    livedataGetCarById.postValue(null)
                }
            }

            override fun onFailure(call: Call<PostCarResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
                livedataGetCarById.postValue(null)
            }

        })
    }

    fun postApiCar(name : String, category : String,price : Int, status : Boolean,image : String){
        val client = APIClient.instance.addCar(name, category, price, status, image)
        client.enqueue(object : Callback<PostCarResponse> {
            override fun onResponse(
                call: Call<PostCarResponse>,
                response: Response<PostCarResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        addLiveDataCar.postValue(data)
                    }
                } else {
                    Log.d("Error", response.message())
                    addLiveDataCar.postValue(null)
                }
            }

            override fun onFailure(call: Call<PostCarResponse>, t: Throwable) {
                Log.d("Error", t.message.toString())
                addLiveDataCar.postValue(null)
            }

        })
    }

    fun updateCarApi(id : Int,name : String, category : String,price : Int, status : Boolean,image : String){
        val client = APIClient.instance.updateCar(id,name, category, price, status, image)
        client.enqueue(object : Callback<PostCarResponse> {
            override fun onResponse(
                call: Call<PostCarResponse>,
                response: Response<PostCarResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        updateLiveDataCar.postValue(data)
                    }
                } else {
                    Log.d("Error", response.message())
                    updateLiveDataCar.postValue(null)
                }
            }

            override fun onFailure(call: Call<PostCarResponse>, t: Throwable) {
                Log.d("Error", t.message.toString())
                updateLiveDataCar.postValue(null)
            }

        })
    }
}