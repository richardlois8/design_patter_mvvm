package coding.withze.chapterlimakmtiga.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coding.withze.chapterlimakmtiga.databinding.ItemCarBinding
import coding.withze.chapterlimakmtiga.databinding.ItemUserBinding
import coding.withze.chapterlimakmtiga.model.CarResponseItem
import coding.withze.chapterlimakmtiga.model.UserResponseItem
import com.bumptech.glide.Glide

class UserAdapter(private var carList: List<UserResponseItem>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemCarBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(response : UserResponseItem) {
            binding.nameCar.text = response.name
            binding.categoryCar.text = "Umur : ${response.age}"
            binding.priceCar.text = "Alamat : ${response.address}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        var view = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bind(carList[position])
    }

    override fun getItemCount(): Int = carList.size
}