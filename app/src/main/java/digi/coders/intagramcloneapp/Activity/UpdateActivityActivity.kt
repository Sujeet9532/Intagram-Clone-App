package digi.coders.intagramcloneapp.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.Utils.USER_NODE
import digi.coders.intagramcloneapp.Utils.USER_PROFILE_FORD
import digi.coders.intagramcloneapp.Utils.UplodeImage
import digi.coders.intagramcloneapp.databinding.ActivitySignupBinding
import digi.coders.intagramcloneapp.databinding.ActivityUpdateActivityBinding

class UpdateActivityActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityUpdateActivityBinding.inflate(layoutInflater)

    }
    private var selectedImageUri: Uri? = null

    lateinit var userModel: UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        userModel = UserModel()
        if (intent.hasExtra("MODE")) {
            if (intent.getIntExtra("MODE", -1) == 1) {
                Firebase.firestore.collection(USER_NODE)
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .get().addOnSuccessListener {
                        userModel = it.toObject(UserModel::class.java)!!
                        if (!userModel.image.isNullOrEmpty()) {
                            Picasso.get().load(userModel.image).into(binding.updateProfileImage)

                        }
                        binding.updateNameField.editText?.setText(userModel.username)
                        binding.updateBioField.editText?.setText(userModel.bio)

                    }


            }
        }
        binding.updateBioBtn.setOnClickListener {

            if (intent.hasExtra("MODE")) {
                if (intent.getIntExtra("MODE", -1) == 1) {
                    userModel.username = binding.updateNameField.editText?.text.toString()
                    userModel.bio = binding.updateBioField.editText?.text.toString()
                    if (selectedImageUri != null) {
                        val storageRef = Firebase.storage.reference
                        val imageRef = storageRef.child("images/${FirebaseAuth.getInstance().currentUser!!.uid}.jpg")
                        val uploadTask = imageRef.putFile(selectedImageUri!!)
                        uploadTask.addOnSuccessListener {
                            imageRef.downloadUrl.addOnSuccessListener { uri ->
                                userModel.image = uri.toString()

                                updateFirestoreWithImage(userModel)
                            }
                        }.addOnFailureListener {
                            Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        updateFirestoreWithImage(userModel)
                    }
                    Firebase.firestore.collection(USER_NODE)
                        .document(FirebaseAuth.getInstance().currentUser!!.uid)
                        .set(userModel).addOnSuccessListener {
                            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                }
            }
        }
        binding.updateAddImageImage.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

    }




    // Register for activity result to pick visual media
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {

            selectedImageUri = uri
            binding.updateProfileImage.setImageURI(uri) // Set image in ImageView
        } else {

        }
    }
    private fun updateFirestoreWithImage(userModel: UserModel) {
        Firebase.firestore.collection(USER_NODE)
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .set(userModel).addOnSuccessListener {


            }
    }

}