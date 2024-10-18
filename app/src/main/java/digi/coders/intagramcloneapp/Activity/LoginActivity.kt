package digi.coders.intagramcloneapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import digi.coders.intagramcloneapp.Model.UserModel
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
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
        binding.loginUpBtn.setOnClickListener {
           if (binding.loginemailField.editText?.text.toString().isNotEmpty() &&
               binding.loginpasswordField.editText?.text.toString().isNotEmpty()){
               var userModel=UserModel(binding.loginemailField.editText?.text.toString(),
                   binding.loginpasswordField.editText?.text.toString())
               Firebase.auth.signInWithEmailAndPassword(userModel.email!!,userModel.psssword!!)
                   .addOnCompleteListener {
                       if (it.isSuccessful){
                           startActivity(Intent(this, MainActivity::class.java))
                           finish()

                       }else{
                           Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                       }
                   }
           }else{
               Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()



               
           }

        }
    }
}