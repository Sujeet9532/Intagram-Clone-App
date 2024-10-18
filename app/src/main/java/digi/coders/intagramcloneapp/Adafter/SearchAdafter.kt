package digi.coders.intagramcloneapp.Adafter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.Utils.FOLLOW
import digi.coders.intagramcloneapp.databinding.SearchBackgroundBinding

class SearchAdafter(val context: Context, val userList: ArrayList<UserModel>) :RecyclerView.Adapter<SearchAdafter.ViewHolder>(){
    inner class  ViewHolder(var binding: SearchBackgroundBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var binding=SearchBackgroundBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isfollow=false
        Glide.with(context).load(userList.get(position).image+ FOLLOW).placeholder(R.drawable.baseline_person_24).into(holder.binding.profileImage)
        holder.binding.name.text=userList.get(position).username
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+FOLLOW)
            .whereEqualTo("email",userList.get(position).email).get().addOnSuccessListener {
                if (it.documents.size==0 || it.isEmpty){
                    isfollow=false
                }else{
                    holder.binding.profileSearch.text="Unfollow"
                    isfollow=true

                }

        }

        holder.binding.profileSearch.setOnClickListener {
            if (isfollow){
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+FOLLOW)
                    .whereEqualTo("email",userList.get(position).email).get().addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+FOLLOW).document(it.documents.get(0).id).delete()
                        holder.binding.profileSearch.text="Follow"
                        isfollow=false

                    }

            }else{
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+FOLLOW).document().set(userList.get(position))
                holder.binding.profileSearch.text="Unfollow"

            }







        }

    }
}