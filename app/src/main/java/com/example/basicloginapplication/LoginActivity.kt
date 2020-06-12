package com.example.basicloginapplication

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.login_button
import kotlinx.android.synthetic.main.activity_main.password_input_layout
import kotlinx.android.synthetic.main.activity_main.username_input_layout
import kotlinx.android.synthetic.main.activity_main.view.email
import kotlinx.android.synthetic.main.activity_main.view.password

class LoginActivity : AppCompatActivity() {

  private var areValidCredentials = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    handleTextFields()
  }

  private fun handleTextFields() {
    login_button.setOnClickListener {
      val email = username_input_layout.email.text.toString()
      val password = password_input_layout.password.text.toString()
      handleEmailInput(email)
      handlePasswordInput(password)
      handleLogin(email, password)
    }
  }

  private fun handleEmailInput(email: String) {
    if (!email.isValidEmail()) {
      username_input_layout.error = getString(R.string.email_name_error_message)
      areValidCredentials = false
    } else {
      username_input_layout.isErrorEnabled = false
    }
  }

  private fun handlePasswordInput(password: String) {
    if (!password.isValidPassword()) {
      password_input_layout.error = getString(R.string.password_error_message)
      areValidCredentials = false
    } else {
      password_input_layout.isErrorEnabled = false;
    }
  }

  private fun handleLogin(emailInput: String, passwordInput: String) {
    if (areValidCredentials) {
      if (emailInput == EMAIL && passwordInput == PASSWORD) {
        Toast.makeText(this, getString(R.string.login_successful_text), Toast.LENGTH_SHORT)
            .show()
      } else {
        Toast.makeText(this, getString(R.string.login_failed_text), Toast.LENGTH_SHORT)
            .show()
      }
    } else {
      areValidCredentials = true
    }
  }

  fun CharSequence.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this)
      .matches()

  fun CharSequence.isValidPassword() = !isNullOrEmpty() && this.length > 5

  companion object {
    private const val EMAIL = "admin@gmail.com"
    private const val PASSWORD = "password"
  }
}
