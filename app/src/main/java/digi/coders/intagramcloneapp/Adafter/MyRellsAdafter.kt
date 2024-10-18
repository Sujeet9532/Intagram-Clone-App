package digi.coders.intagramcloneapp.Adafter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import digi.coders.intagramcloneapp.Model.Rells
import digi.coders.intagramcloneapp.databinding.MyPostLayoutBinding

class MyRellsAdafter(var context: Context,var rellsList: ArrayList<Rells>) :RecyclerView.Adapter<MyRellsAdafter.ViewHolder>(){
    inner class ViewHolder(var binding: MyPostLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=MyPostLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rellsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(rellsList.get(position).rellurl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.myPostImageview);
    }
}