package digi.coders.intagramcloneapp.Adafter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.databinding.FollowBackBinding

class FollowBgAdafter(val context: Context, val followList: ArrayList<UserModel>) : RecyclerView.Adapter<FollowBgAdafter.ViewHolder>(){
    inner class ViewHolder(var binding: FollowBackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=FollowBackBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return followList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Glide.with(context).load(followList.get(position).image).placeholder(R.drawable.baseline_person_24).into(holder.binding.profileImage)
        holder.binding.textView4.text=followList.get(position).username
    }
}