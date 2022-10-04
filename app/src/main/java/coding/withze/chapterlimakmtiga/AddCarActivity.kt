package coding.withze.chapterlimakmtiga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coding.withze.chapterlimakmtiga.databinding.ActivityAddCarBinding
import coding.withze.chapterlimakmtiga.model.PostCarResponse
import coding.withze.chapterlimakmtiga.service.APIClient
import coding.withze.chapterlimakmtiga.viewmodel.ViewModelCar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCarBinding
    private lateinit var carVM : ViewModelCar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carVM = ViewModelProvider(this).get(ViewModelCar::class.java)

        binding.btnAddCar.setOnClickListener {
            postCar()
        }
    }


    private fun postCar() {
        val name = binding.etCarname.text.toString()
        val category = binding.etCategory.text.toString()
        val price = binding.etPrice.text.toString().toInt()
        val status = binding.etStatus.text.toString().lowercase().toBoolean()
        val image = binding.etImageLink.text.toString()
        carVM.postApiCar(name, category, price,status, image)
        carVM.postLiveDataCar().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

}