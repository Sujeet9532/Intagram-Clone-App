package digi.coders.intagramcloneapp.Adafter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Model.Post
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.Utils.USER_NODE
import digi.coders.intagramcloneapp.databinding.PostBackgeoundHomeLayoutBinding

class AllPostAdafter(var context: Context, val postlist:ArrayList<Post>):RecyclerView.Adapter<AllPostAdafter.AllPostViewHolder>() {
   inner class AllPostViewHolder(var binding: PostBackgeoundHomeLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPostViewHolder {
    var binding=PostBackgeoundHomeLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return AllPostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postlist.size
    }

    override fun onBindViewHolder(holder: AllPostViewHolder, position: Int) {
        try {
            Firebase.firestore.collection(USER_NODE).document(postlist.get(position).uid).get().addOnSuccessListener {
                var userModel=it.toObject(UserModel::class.java)
                Glide.with(context).load(userModel?.image).placeholder(R.drawable.user).into(holder.binding.profileImage)
                holder.binding.userName.text=userModel?.username
            }

        }catch (ex:Exception){

        }

        Glide.with(context).load(postlist.get(position).postUrl).placeholder(R.drawable.loading).into(holder.binding.postImageView)
        try {
            val text: String = TimeAgo.using(postlist.get(position).time.toLong())
            holder.binding.userTime.text=text
        }catch (ex:Exception){

        }
        holder.binding.sentSms.setOnClickListener{
            var i=Intent(Intent.ACTION_SEND)
            i.putExtra(Intent.EXTRA_TEXT,postlist.get(position).postUrl)
            i.type="text/plain"
            context.startActivity(i)
        }
        holder.binding.caption.text=postlist.get(position).caption
        holder.binding.haedIcon.setOnClickListener{
            holder.binding.haedIcon.setImageResource(R.drawable.redhard)
        }

    }
}