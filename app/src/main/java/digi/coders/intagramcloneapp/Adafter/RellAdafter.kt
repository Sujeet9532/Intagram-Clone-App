package digi.coders.intagramcloneapp.Adafter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import digi.coders.intagramcloneapp.Model.Rells
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.databinding.ReelsBackgroundBinding


class RellAdafter(var context: Context, var rellList: ArrayList<Rells>):RecyclerView.Adapter<RellAdafter.ViewHOlder>() {
     inner class ViewHOlder(var binding: ReelsBackgroundBinding): RecyclerView.ViewHolder(binding.root
     )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHOlder {
        var binding= ReelsBackgroundBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHOlder(binding)
    }

    override fun getItemCount(): Int {
       return rellList.size
    }

    override fun onBindViewHolder(holder: ViewHOlder, position: Int) {
        Picasso.get().load(rellList.get(position).profileLink).placeholder(R.drawable.baseline_person_24).into(holder.binding.profileImageRell)
        holder.binding.rellTitle.text=rellList.get(position).caption
        holder.binding.userTitle.text=rellList.get(position).rellname
        holder.binding.videoViewRell.setVideoPath(rellList.get(position).rellurl)
        holder.binding.videoViewRell.setOnPreparedListener {
            holder.binding.progressBar.visibility= View.GONE
           holder.binding.videoViewRell.start()
        }



    }
}