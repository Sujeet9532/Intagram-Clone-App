package digi.coders.intagramcloneapp.Fregment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import digi.coders.intagramcloneapp.Activity.UpdateActivityActivity
import digi.coders.intagramcloneapp.Adafter.ViewPagerAdafter
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.Utils.USER_NODE
import digi.coders.intagramcloneapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
   private lateinit var binding: FragmentProfileBinding
   private lateinit var viewpageradafter: ViewPagerAdafter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(inflater,container,false)

        binding.profileEdit.setOnClickListener {
            var intent=Intent(activity, UpdateActivityActivity::class.java)
            intent.putExtra("MODE",1)
            activity?.startActivity(intent)
        }
        viewpageradafter=ViewPagerAdafter(requireActivity().supportFragmentManager)
        viewpageradafter.addfregment(MyPostFragment(),"My Post")
        viewpageradafter.addfregment(MyReelsFragment(),"My Rells")
        binding.viewPager.adapter=viewpageradafter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        return binding.root
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val user=it.toObject(UserModel::class.java)
               binding.name.text=user!!.username
                binding.bio.text=user!!.bio
                if (!user!!.image.isNullOrEmpty()){
                    Picasso.get().load(user!!.image).into(binding.profileImage)

                }

            }
    }
}