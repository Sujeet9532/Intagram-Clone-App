package digi.coders.intagramcloneapp.Adafter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import digi.coders.intagramcloneapp.Fregment.MyPostFragment
import digi.coders.intagramcloneapp.Model.Post
import digi.coders.intagramcloneapp.databinding.MyPostLayoutBinding

class MyPostAdafter(var context: Context,var postList: ArrayList<Post>)
    :RecyclerView.Adapter<MyPostAdafter.ViewHolder>(){
    inner class ViewHolder(var binding:MyPostLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=MyPostLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
      return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(postList[position].postUrl).into(holder.binding.myPostImageview)

    }


}