package digi.coders.intagramcloneapp.Fregment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import digi.coders.intagramcloneapp.Activity.PostActivity
import digi.coders.intagramcloneapp.Activity.ReelsActivity
import digi.coders.intagramcloneapp.databinding.FragmentUploadBinding


class UploadFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentUploadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUploadBinding.inflate(inflater, container, false)
        binding.bottomSeetAddPost.setOnClickListener{
            startActivity(Intent(requireContext(), PostActivity::class.java))

            dismiss()

        }

        binding.addPostUpload.setOnClickListener{
            startActivity(Intent(requireContext(), ReelsActivity::class.java))
            dismiss()

        }




        return binding.root
    }


    companion object {

    }
    private fun alartdailog(){
        val builder = AlertDialog.Builder(requireContext())

    }
}