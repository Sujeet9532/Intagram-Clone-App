package digi.coders.intagramcloneapp.Fregment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Adafter.AllPostAdafter
import digi.coders.intagramcloneapp.Adafter.SearchAdafter
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.Utils.USER_NODE
import digi.coders.intagramcloneapp.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var adapter: SearchAdafter
    var userList=ArrayList<UserModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(inflater, container, false)
        adapter= SearchAdafter(requireContext(),userList)
        binding.searchRecyclerview.layoutManager= LinearLayoutManager(requireContext())
        binding.searchRecyclerview.adapter=adapter
        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var tempList = arrayListOf<UserModel>()
            userList.clear()
            for (i in it.documents) {
                if (i.id.equals(Firebase.auth.currentUser!!.uid)){

                }else{
                    var user = i.toObject(UserModel::class.java)
                    tempList.add(user!!)

                }

            }
            userList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        binding.searchButton.setOnClickListener {
            var searchText=binding.searchView.text.toString()
            Firebase.firestore.collection(USER_NODE).whereEqualTo("username",searchText).get().addOnSuccessListener {
                var tempList = arrayListOf<UserModel>()
                userList.clear()
                if (it.isEmpty){

                }else{

                }
                for (i in it.documents) {
                    if (it.isEmpty){

                    }else{
                        if (i.id.equals(Firebase.auth.currentUser!!.uid)){

                        }else{
                            var user = i.toObject(UserModel::class.java)
                            tempList.add(user!!)

                    }


                    }

                }
                userList.addAll(tempList)
                adapter.notifyDataSetChanged()
            }

        }
        return binding.root
    }


}