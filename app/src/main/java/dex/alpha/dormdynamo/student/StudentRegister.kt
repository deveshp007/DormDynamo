package dex.alpha.dormdynamo.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import dex.alpha.dormdynamo.R

class StudentRegister : AppCompatActivity() , View.OnClickListener{

    private lateinit var mAuth: FirebaseAuth

    private lateinit var mEmailEditText: EditText
    private lateinit var mPasswordEditText: EditText
    private lateinit var mNameEditText: EditText
    private lateinit var mRegistrationEditText: EditText
    private lateinit var mRegisterButton: Button
    private lateinit var tVLogin: TextView
    private lateinit var back_Btn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_register)

        mAuth = FirebaseAuth.getInstance()
        mEmailEditText = findViewById(R.id.email_edit_text)
        mPasswordEditText = findViewById(R.id.password_edit_text)
        mNameEditText = findViewById(R.id.name_edit_text)
        mRegistrationEditText = findViewById(R.id.registration_edit_text)
        mRegisterButton = findViewById(R.id.register_button)
        mRegisterButton.setOnClickListener(this)

        tVLogin = findViewById(R.id.textView_login)
        tVLogin.setOnClickListener(){
            val intent = Intent(this, StudentLogin::class.java)
            startActivity(intent)
            finish()
        }

        // back Button
        back_Btn = findViewById(R.id.btnBack)
        back_Btn.setOnClickListener(){
            onBackPressed()
            finish()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.register_button -> registerUser()
        }
    }

    private fun registerUser() {
        val email = mEmailEditText.text.toString().trim()
        val password = mPasswordEditText.text.toString().trim()
        val name = mNameEditText.text.toString().trim()
        val registrationNumber = mRegistrationEditText.text.toString().trim()

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

        if (name.isEmpty()) {
            mNameEditText.error = "Name is required"
            mNameEditText.requestFocus()
            return
        }

        if (registrationNumber.isEmpty()) {
            mRegistrationEditText.error = "Registration number is required"
            mRegistrationEditText.requestFocus()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val currentUser = mAuth.currentUser
                    currentUser?.let { user ->
                        val userProfileChangeRequest = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                        user.updateProfile(userProfileChangeRequest)
                            .addOnCompleteListener { updateTask ->
                                if (updateTask.isSuccessful) {
                                    Toast.makeText(applicationContext, "Registration successful!", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this, StudentLogin::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Log.e("RegisterActivity", "Error: ${updateTask.exception?.message}")
                                    Toast.makeText(applicationContext, "Registration failed: ${updateTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } ?: run {
                        Log.e("RegisterActivity", "Error: Current user is null.")
                        Toast.makeText(applicationContext, "Registration failed: Current user is null.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val exception = task.exception as FirebaseAuthException?
                    Log.e("RegisterActivity", "Error: ${exception?.errorCode} ${exception?.message}")
                    Toast.makeText(applicationContext, "Registration failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }
}
