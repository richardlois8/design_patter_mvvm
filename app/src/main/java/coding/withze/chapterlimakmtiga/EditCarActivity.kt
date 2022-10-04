package coding.withze.chapterlimakmtiga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coding.withze.chapterlimakmtiga.databinding.ActivityEditCarBinding
import coding.withze.chapterlimakmtiga.viewmodel.ViewModelCar

class EditCarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditCarBinding
    private lateinit var carVM : ViewModelCar
    private var editedId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        carVM = ViewModelProvider(this).get(ViewModelCar::class.java)
        editedId = intent.getIntExtra("id", 0)

        getDataCar()

        binding.btnUpdateCar.setOnClickListener {
            updateCar()
        }
    }

    fun getDataCar(){
        carVM.getCarById(editedId)
        carVM.getLiveDataGetCarById().observe(this) {
            if (it != null) {
                binding.etCarname.setText(it.name)
                binding.etCategory.setText(it.category)
                binding.etPrice.setText(it.price.toString())
                binding.etStatus.setText(it.status.toString())
                binding.etImageLink.setText(it.image.toString())
            }
        }
    }

    fun updateCar(){
        val name = binding.etCarname.text.toString()
        val category = binding.etCategory.text.toString()
        val price = binding.etPrice.text.toString().toInt()
        val status = binding.etStatus.text.toString().lowercase().toBoolean()
        val image = binding.etImageLink.text.toString()
        carVM.updateCarApi(editedId,name, category, price,status, image)
        carVM.updateLiveDataCar().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java).putExtra("edited",true))
                finish()
            }
        }
    }
}