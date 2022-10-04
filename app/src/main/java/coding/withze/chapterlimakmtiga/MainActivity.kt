package coding.withze.chapterlimakmtiga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coding.withze.chapterlimakmtiga.adapter.CarAdapter
import coding.withze.chapterlimakmtiga.adapter.UserAdapter
import coding.withze.chapterlimakmtiga.databinding.ActivityMainBinding
import coding.withze.chapterlimakmtiga.viewmodel.ViewModelCar
import coding.withze.chapterlimakmtiga.viewmodel.ViewModelUser

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var carVM: ViewModelCar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        carVM = ViewModelProvider(this).get(ViewModelCar::class.java)
        showDataCar()

        binding.addButton.setOnClickListener {
            addCar()
        }
    }

    fun showDataCar(){
        isLoading(true)
        carVM.getCarApi()
        carVM.getLiveDataCar().observe(this) {
            if (it != null) {
                isLoading(false)
                val layoutManager = LinearLayoutManager(this)
                binding.rvCar.layoutManager = layoutManager
                val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
                binding.rvCar.addItemDecoration(itemDecoration)
                val adapter = CarAdapter(it)
                binding.rvCar.adapter = adapter
                adapter.onClick = {
                    updateCar(it.id)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun addCar(){
        startActivity(Intent(this, AddCarActivity::class.java))
    }

    private fun updateCar(id : Int){
        val intent = Intent(this, EditCarActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun isLoading(state : Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}