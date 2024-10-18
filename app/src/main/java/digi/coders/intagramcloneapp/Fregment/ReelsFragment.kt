package digi.coders.intagramcloneapp.Fregment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Adafter.RellAdafter
import digi.coders.intagramcloneapp.Model.Rells
import digi.coders.intagramcloneapp.Utils.RELLS
import digi.coders.intagramcloneapp.databinding.FragmentReelsBinding


class ReelsFragment : Fragment() {
    private lateinit var binding: FragmentReelsBinding
   lateinit var adafter:RellAdafter
    var rellsList=ArrayList<Rells>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentReelsBinding.inflate(inflater, container, false)
        adafter=RellAdafter(requireContext(),rellsList)
        binding.viewPagerRells.adapter=adafter
        Firebase.firestore.collection(RELLS).get().addOnSuccessListener {
            var tempList=ArrayList<Rells>()
            rellsList.clear()
            for (i in it.documents){
                var rell:Rells=i.toObject(Rells::class.java)!!
                tempList.add(rell)
            }
            rellsList.addAll(tempList)
            rellsList.reverse()
            adafter.notifyDataSetChanged()

        }
        return binding.root
    }


}