package coding.withze.chapterlimakmtiga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coding.withze.chapterlimakmtiga.databinding.ItemCarBinding
import coding.withze.chapterlimakmtiga.model.CarResponseItem
import coding.withze.chapterlimakmtiga.model.PostCarResponse
import com.bumptech.glide.Glide

class CarAdapter(private var carList: List<CarResponseItem>):RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    var onClick : ((CarResponseItem) -> Unit)? = null
    class ViewHolder(var binding : ItemCarBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(carResponseItem: CarResponseItem, onClick : ((CarResponseItem) -> Unit)?){
            var name : Any
            if(carResponseItem.name == null){
                name = "No Name"
            }else{
                name = carResponseItem.name
            }
            binding.nameCar.text = name
            var category : Any
            if(carResponseItem.category == null){
                category = "No Category"
            }else{
                category = carResponseItem.category.toString()
            }
            binding.categoryCar.text = category
            binding.priceCar.text = carResponseItem.price.toString()
            Glide.with(binding.root)
                .load(carResponseItem.image)
                .into(binding.imgCar)
            binding.cardView.setOnClickListener {
                onClick?.invoke(carResponseItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.ViewHolder {
        var view = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarAdapter.ViewHolder, position: Int) {
        holder.bind(carList[position],onClick)
    }

    override fun getItemCount(): Int = carList.size

    fun updateItems(newCarList: List<CarResponseItem>) {
        val diffResult = DiffUtil.calculateDiff(CarDiffCallback(carList, newCarList))
        carList = newCarList
        diffResult.dispatchUpdatesTo(this)
    }
}

class CarDiffCallback(private var oldList : List<CarResponseItem>,private var newList: List<CarResponseItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
    }

}