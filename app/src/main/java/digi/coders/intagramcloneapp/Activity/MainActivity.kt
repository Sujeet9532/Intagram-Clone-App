package digi.coders.intagramcloneapp.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import digi.coders.intagramcloneapp.R
import digi.coders.intagramcloneapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingg : BottomSheetDialog
    private val binding by lazy {
       ActivityMainBinding.inflate(layoutInflater)
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        var NavController=findNavController(R.id.fragmentContainerView)
        var bottomnav=findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomnav.setupWithNavController(NavController)




//        refleceFregment(HomeFragment())















    }
    fun refleceFregment(Fregment:Fragment){
        if (Fregment!=null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView,Fregment)
            transaction.commit()
        }
    }
//    fun alartdilogbox(){
//
//
//
//
//    }
}