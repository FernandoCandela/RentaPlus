package pe.edu.ulima.pm.renta

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.renta.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.signInAppCompatButton.setOnClickListener {
            val mEmail = binding.emailEditText.text.toString()
            val mPassword = binding.passwordEditText.text.toString()
            when {
                mEmail.isEmpty() || mPassword.isEmpty() -> {
                    Toast.makeText(
                        baseContext, "Correo o Contraseña incorrectos.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    SingIn(mEmail, mPassword)
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }

    private fun SingIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    reload()
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun reload() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}