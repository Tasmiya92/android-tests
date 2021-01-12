package com.tasmiya.peopleinteractivedemoapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.tasmiya.peopleinteractivedemoapp.databinding.UserRowItemBinding
import com.tasmiya.peopleinteractivedemoapp.models.UserModel
import com.tasmiya.peopleinteractivedemoapp.models.Result

class RecyclerViewAdapter(private val clickListener: AdapterView.OnItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private var users = emptyList<Result>()

    class MyViewHolder(private val binding: UserRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: Result, clickListener: AdapterView.OnItemClickListener){
            binding.userResult = userModel
            var acceptOrRecject : String = ""
            binding.imgAccept.setOnClickListener{
                 acceptOrRecject = "You Accepted"
                clickListener("You Accepted")
                binding.tvMsg.text = acceptOrRecject
                binding.imgAccept.visibility = View.INVISIBLE
                binding.imgDecline.visibility = View.INVISIBLE
                Log.d("UserAcceoted","UserAccepted")
            }
            binding.imgDecline.setOnClickListener{
                acceptOrRecject = "You Declined"
                clickListener("You Declined")
                binding.tvMsg.text = acceptOrRecject
                binding.imgAccept.visibility = View.INVISIBLE
                binding.imgDecline.visibility = View.INVISIBLE
                Log.d("UserDeclined","UserDeclined")
                clickListener("You Declined")

            }

            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) : MyViewHolder{
               val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserRowItemBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)

    }

    override fun getItemCount(): Int {
         return users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = users[position]
        holder.bind(currentUser,clickListener)
    }

    fun setData(newData: UserModel){
        users = newData.results
    }
}

private operator fun AdapterView.OnItemClickListener.invoke(s: String) {


}
