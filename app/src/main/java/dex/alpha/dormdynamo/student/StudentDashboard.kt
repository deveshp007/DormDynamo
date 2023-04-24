package dex.alpha.dormdynamo.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import dex.alpha.dormdynamo.R
import dex.alpha.dormdynamo.student.fragments.RoomType

class StudentDashboard : AppCompatActivity() {

    companion object{
        lateinit var roomType : String
        lateinit var seater: String
        lateinit var gender: String
        var nameOfStudent: String? = ""
        var emailOfStudent: String? = ""
        var registrationNumber: String? = ""
        lateinit var hostel: String
        lateinit var room: String
        lateinit var bed: String
    }

    private lateinit var logout_Btn : ImageView
    private lateinit var back_Btn : ImageView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        mAuth = FirebaseAuth.getInstance()

        logout_Btn = findViewById(R.id.logout_button)
        logout_Btn.setOnClickListener(){
            
            mAuth.signOut()
            startActivity(Intent(this, StudentLogin::class.java))
            finish()
        }
        back_Btn = findViewById(R.id.btnBack)
        back_Btn.setOnClickListener(){
            onBackPressed()
            finish()
        }



        // fragment ---------
        val roomTypeFragment = RoomType()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, roomTypeFragment)
            .commit()

        //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, RoomTypeFragment().apply { arguments = Bundle().also { it.putString() } })
    }

}