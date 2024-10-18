package digi.coders.intagramcloneapp.Fregment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.integrity.internal.i
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Adafter.AllPostAdafter
import digi.coders.intagramcloneapp.Adafter.FollowBgAdafter
import digi.coders.intagramcloneapp.Model.Post
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.Utils.FOLLOW
import digi.coders.intagramcloneapp.Utils.POST
import digi.coders.intagramcloneapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val postList=ArrayList<Post>()
    private lateinit var allPostAdafter: AllPostAdafter
    private val followList=ArrayList<UserModel>()
    private lateinit var followBgAdafter: FollowBgAdafter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        allPostAdafter= AllPostAdafter(requireContext(),postList)
        binding.recyclerviewPost.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerviewPost.adapter=allPostAdafter
        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            var tempList= arrayListOf<Post>()
            postList.clear()
            for (i in it.documents){
                var post=i.toObject(Post::class.java)
                tempList.add(post!!)
            }
            postList.addAll(tempList)
            allPostAdafter.notifyDataSetChanged()
        }
        followBgAdafter= FollowBgAdafter(requireContext(),followList)
        binding.profileListRecyclerview.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.profileListRecyclerview.adapter=followBgAdafter
       Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).get().addOnSuccessListener {
           var tempList= arrayListOf<UserModel>()
           followList.clear()
           for (i in it.documents){
               var user=i.toObject(UserModel::class.java)
               tempList.add(user!!)
           }
           followList.addAll(tempList)
           followBgAdafter.notifyDataSetChanged()
       }


        return binding.root
    }


}