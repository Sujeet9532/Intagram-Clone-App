package digi.coders.intagramcloneapp.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.Utils.USER_NODE
import digi.coders.intagramcloneapp.Utils.USER_PROFILE_FORD
import digi.coders.intagramcloneapp.Utils.UplodeImage
import digi.coders.intagramcloneapp.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    lateinit var userModel:UserModel
private val launcher: ActivityResultLauncher<String> = registerForActivityResult(
    ActivityResultContracts.GetContent()) {
    uri->
    uri?.let {
        UplodeImage(uri,USER_PROFILE_FORD){
            if (it==null){
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }else{
                userModel.image=it
                binding.profileImage.setImageURI(uri)
            }
        }

    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val text = "<font color=#FFFFFFFF>Already Have Account</font> <font color=#1E88E5>Login ?</font>"
        binding.loginbtn.setText(Html.fromHtml(text))
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        userModel=UserModel()
        binding.signUpBtn.setOnClickListener {


                if (binding.textField.editText?.text.toString().equals("") &&
                    binding.emailField.editText?.text.toString().equals("") &&
                    binding.passwordField.editText?.text.toString().equals("")
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Please Fill All Information",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.emailField.editText?.text.toString(),
                        binding.passwordField.editText?.text.toString()

                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            userModel.username = binding.textField.editText?.text.toString()
                            userModel.email = binding.emailField.editText?.text.toString()
                            userModel.psssword = binding.passwordField.editText?.text.toString()
                            userModel.bio = binding.biotextField.editText?.text.toString()
                            Firebase.firestore.collection(USER_NODE)
                                .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
                                .set(userModel)
                                .addOnSuccessListener {
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                        } else {
                            Toast.makeText(this, result.exception.toString(), Toast.LENGTH_SHORT)
                                .show()

                    }

                }
            }


        }
        binding.pluseImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.loginbtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }
    }
}