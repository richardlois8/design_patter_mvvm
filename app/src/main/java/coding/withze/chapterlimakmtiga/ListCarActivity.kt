package coding.withze.chapterlimakmtiga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coding.withze.chapterlimakmtiga.adapter.CarAdapter
import coding.withze.chapterlimakmtiga.databinding.ActivityListCarBinding
import coding.withze.chapterlimakmtiga.model.CarResponseItem
import coding.withze.chapterlimakmtiga.service.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCarActivity : AppCompatActivity() {

    lateinit var binding : ActivityListCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCarFromAPI()

        binding.addButton.setOnClickListener {
            addCar()
        }
    }

    private fun getCarFromAPI(){
        isLoading(true)
        val client = APIClient.instance.getAllCar()
        client.enqueue(object : Callback<List<CarResponseItem>>{
            override fun onResponse(
                call: Call<List<CarResponseItem>>,
                response: Response<List<CarResponseItem>>
            ) {
                if(response.isSuccessful){
                    isLoading(false)
                    val data = response.body()
                    if(data != null){
                        Log.d("RESULT", data.toString())
                        showListCar(data)
                    }
                }else{
                    Log.d("Error", response.message())
                }
            }

            override fun onFailure(call: Call<List<CarResponseItem>>, t: Throwable) {
                isLoading(false)
                Log.d("Error", t.message.toString())
            }

        })
    }

    private fun showListCar(data: List<CarResponseItem>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvCar.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvCar.addItemDecoration(itemDecoration)
        val adapter = CarAdapter(data)
        binding.rvCar.adapter = adapter
    }

    private fun addCar(){
        startActivity(Intent(this, AddCarActivity::class.java))
    }

    private fun isLoading(state : Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}