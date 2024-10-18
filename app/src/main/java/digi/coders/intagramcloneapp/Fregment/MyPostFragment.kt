package digi.coders.intagramcloneapp.Fregment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Adafter.MyPostAdafter
import digi.coders.intagramcloneapp.Adafter.MyRellsAdafter
import digi.coders.intagramcloneapp.Model.Post
import digi.coders.intagramcloneapp.databinding.FragmentMyPostBinding


class MyPostFragment : Fragment() {
    private lateinit var binding:FragmentMyPostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMyPostBinding.inflate(layoutInflater)
        var postList=ArrayList<Post>()
        var adafter=MyPostAdafter(requireContext(),postList)
        binding.postRecyclerview.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.postRecyclerview.adapter=adafter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            var tempList=ArrayList<Post>()
            for (i in it.documents){
                var post:Post=i.toObject(Post::class.java)!!
                tempList.add(post)
            }
            postList.addAll(tempList)
            adafter.notifyDataSetChanged()
        }

        return binding.root

    }

    companion object {

    }
}