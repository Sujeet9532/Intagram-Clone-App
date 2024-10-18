package digi.coders.intagramcloneapp.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Model.Rells
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.Utils.RELLS
import digi.coders.intagramcloneapp.Utils.RELLS_PROFILE
import digi.coders.intagramcloneapp.Utils.USER_NODE
import digi.coders.intagramcloneapp.Utils.UplodeVideo
import digi.coders.intagramcloneapp.databinding.ActivityReellsBinding


class ReelsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityReellsBinding.inflate(layoutInflater)
    }
    private lateinit var videoUrl: String
    private val launcher: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()) {
            uri->
        uri?.let {
            UplodeVideo(uri, RELLS_PROFILE,ProgressDialog(this)){
                    url->
                if (url!=null){
                    binding.dataRell.setVideoURI(uri)
                    videoUrl=url
                }
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rellsVideo.setOnClickListener {
            launcher.launch("video/*")

        }
        binding.cancelRellBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.rellPost.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var name: UserModel =it.toObject(UserModel::class.java)!!
                var user: UserModel =it.toObject(UserModel::class.java)!!
                val rells: Rells = Rells(videoUrl!!,binding.captioon.editText?.text.toString(),user.image!!,user.username!!)
                Firebase.firestore.collection(RELLS).document().set(rells)
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ RELLS).document().set(rells).addOnSuccessListener {
                    Toast.makeText(this, "Post Uploaded", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }



        }

    }

}