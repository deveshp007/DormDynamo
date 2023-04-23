package dex.alpha.dormdynamo.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dex.alpha.dormdynamo.R

class StudentLogin : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var mEmailEditText: EditText
    private lateinit var mPasswordEditText: EditText
    private lateinit var mLoginButton: Button
    private lateinit var tVRegister: TextView
    lateinit var back_Btn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        mAuth = FirebaseAuth.getInstance()
        mEmailEditText = findViewById(R.id.email_edit_text)
        mPasswordEditText = findViewById(R.id.password_edit_text)
        mLoginButton = findViewById(R.id.login_button)
        mLoginButton.setOnClickListener(this)

        // Check if user is already logged in
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            // Redirect to main activity
            val intent = Intent(this, StudentDashboard::class.java)
            startActivity(intent)
            finish()
        }

        tVRegister = findViewById(R.id.textView_register)
        tVRegister.setOnClickListener(){
            val intent = Intent(this, StudentRegister::class.java)
            startActivity(intent)
            finish()
        }

        back_Btn = findViewById(R.id.btnBack)
        back_Btn.setOnClickListener(){
            onBackPressed()
            finish()
        }


    }
    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_button -> loginUser()
        }
    }

    private fun loginUser() {
        val email = mEmailEditText.text.toString().trim()
        val password = mPasswordEditText.text.toString().trim()

        if (email.isEmpty()) {
            mEmailEditText.error = "Email is required"
            mEmailEditText.requestFocus()
            return
        }

        if (password.isEmpty()) {
            mPasswordEditText.error = "Password is required"
            mPasswordEditText.requestFocus()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, StudentDashboard::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}