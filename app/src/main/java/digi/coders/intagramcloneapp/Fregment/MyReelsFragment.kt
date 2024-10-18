package digi.coders.intagramcloneapp.Fregment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Adafter.MyRellsAdafter
import digi.coders.intagramcloneapp.Model.Rells
import digi.coders.intagramcloneapp.Utils.RELLS
import digi.coders.intagramcloneapp.databinding.FragmentMyRellsBinding


class MyReelsFragment : Fragment() {
    private lateinit var binging:FragmentMyRellsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binging=FragmentMyRellsBinding.inflate(inflater, container, false)
        var rellList=ArrayList<Rells>()
        var adafter= MyRellsAdafter(requireContext(),rellList)
        binging.myrellRecyclerview.layoutManager=
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binging.myrellRecyclerview.adapter=adafter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ RELLS).get().addOnSuccessListener {
            var tempList=ArrayList<Rells>()
            for (i in it.documents){
                var rell: Rells =i.toObject(Rells::class.java)!!
                tempList.add(rell)
            }
            rellList.addAll(tempList)
            adafter.notifyDataSetChanged()
        }
        return binging.root
    }
}