package dex.alpha.dormdynamo.warden

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import dex.alpha.dormdynamo.R

class WardenDashboard : AppCompatActivity() {

    lateinit var logoutBtn: ImageView
    private lateinit var back_Btn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warden_dashboard)

        logoutBtn = findViewById(R.id.logout_button_warden)
        logoutBtn.setOnClickListener(){
            // Clear the "is_logged_in" flag in SharedPreferences
            val sharedPrefs = getSharedPreferences("warden_prefs", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.putBoolean("is_logged_in", false)
            editor.apply()

            // Launch the WardenLogin activity and finish the current activity
            val intent = Intent(this, WardenLogin::class.java)
            startActivity(intent)
            finish()
        }

        // back button
        back_Btn = findViewById(R.id.btnBack)
        back_Btn.setOnClickListener(){
            onBackPressed()
            finish()
        }
    }
}