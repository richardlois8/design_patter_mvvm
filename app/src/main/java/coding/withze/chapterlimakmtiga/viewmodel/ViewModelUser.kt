package coding.withze.chapterlimakmtiga.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coding.withze.chapterlimakmtiga.model.UserResponseItem
import coding.withze.chapterlimakmtiga.service.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser : ViewModel() {
    lateinit var livedataUser : MutableLiveData<List<UserResponseItem>>

    init {
        livedataUser = MutableLiveData()
    }

    fun getAllUser() : MutableLiveData<List<UserResponseItem>>{
        return livedataUser
    }

    fun getAllUserAPI(){
        val client = APIClient.instance.getUser()
        client.enqueue(object : Callback<List<UserResponseItem>>{
            override fun onResponse(
                call: Call<List<UserResponseItem>>,
                response: Response<List<UserResponseItem>>
            ) {
                if(response.isSuccessful){
                    val data = response.body()
                    if(data != null){
                        livedataUser.postValue(data)
                    }
                }else{
                    livedataUser.postValue(null)
                    Log.e("Error", response.message())
                }
            }

            override fun onFailure(call: Call<List<UserResponseItem>>, t: Throwable) {
                livedataUser.postValue(null)
                Log.e("Error", t.message.toString())
            }

        })
    }
}