package dex.alpha.dormdynamo.warden

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import dex.alpha.dormdynamo.R

class WardenLogin : AppCompatActivity() {

    lateinit var userTxt: EditText
    lateinit var passTxt: EditText
    lateinit var loginBtn: Button
    private lateinit var back_Btn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warden_login)

        userTxt = findViewById(R.id.user_edit_text_warden)
        passTxt = findViewById(R.id.password_edit_text_warden)
        loginBtn = findViewById(R.id.login_button_warden)


        // Get SharedPreferences instance
        val sharedPrefs = getSharedPreferences("warden_prefs", Context.MODE_PRIVATE)

        // Check if warden is already logged in
        if (sharedPrefs.getBoolean("is_logged_in", false)) {
            val intent = Intent(this, WardenDashboard::class.java)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener(){
            val user = userTxt.text.toString()
            val pass = passTxt.text.toString()
            if (user == "admin" && pass == "admin"){

                // Save the flag indicating that the warden is logged in
                val editor: SharedPreferences.Editor = sharedPrefs.edit()
                editor.putBoolean("is_logged_in", true)
                editor.apply()

                val intent = Intent(this, WardenDashboard::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Wrong Credentials Entered !!", Toast.LENGTH_SHORT).show()
            }
        }

        // back button
        back_Btn = findViewById(R.id.btnBack)
        back_Btn.setOnClickListener(){
            onBackPressed()
            finish()
        }

    }
}