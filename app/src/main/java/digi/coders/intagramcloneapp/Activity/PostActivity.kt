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
import digi.coders.intagramcloneapp.Model.Post
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.Utils.POST
import digi.coders.intagramcloneapp.Utils.POST_PROFILE
import digi.coders.intagramcloneapp.Utils.USER_NODE
import digi.coders.intagramcloneapp.Utils.UplodeImage
import digi.coders.intagramcloneapp.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {
    val binging by lazy {
        ActivityPostBinding.inflate(layoutInflater)



    }
    var profileImage: String? = null
    private val launcher: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()) {
            uri->
        uri?.let {
            UplodeImage(uri, POST_PROFILE){
                url->
                if (url!=null){
                    binging.postImage.setImageURI(uri)
                    profileImage=url
                }
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binging.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       var progressDialog=ProgressDialog.show(this,"Uploading","Please Wait")
        progressDialog.dismiss()
        setSupportActionBar(binging.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binging.materialToolbar.setNavigationOnClickListener {
            finish()
        }
        binging.postImage.setOnClickListener {
            launcher.launch("image/*")


        }
        binging.cancelBtnPost.setOnClickListener {
            finish()
        }

        binging.postBtn.setOnClickListener {
            val caption=binging.captioon.editText?.text.toString()
            if (profileImage!=null){

                uploadPost(profileImage!!,caption)
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().get().addOnSuccessListener {
                    val user=it.toObject(Post::class.java)
                    val post=Post(profileImage!!,caption,
                       uid = Firebase.auth.currentUser!!.uid,
                        time = System.currentTimeMillis().toString())
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {
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

    private fun uploadPost(profileImage: String, caption: String) {
        val post=Post(profileImage,caption)
        Firebase.firestore.collection(POST).document().set(post)


    }


}