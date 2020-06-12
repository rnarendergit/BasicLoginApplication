package com.example.basicloginapplication

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.login_button
import kotlinx.android.synthetic.main.activity_main.password_input_layout
import kotlinx.android.synthetic.main.activity_main.username_input_layout
import kotlinx.android.synthetic.main.activity_main.view.password
import kotlinx.android.synthetic.main.activity_main.view.username

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setupTextFields()
  }

  private fun setupTextFields() {
    login_button.setOnClickListener {
      val userName = username_input_layout.username.text.toString()
      val password = password_input_layout.password.text.toString()
      if (!userName.isValidEmail()) {
        username_input_layout.error = getString(R.string.user_name_error_message)
      } else {
        username_input_layout.isErrorEnabled = false
      }
      if (!password.isValidPassword()) {
        password_input_layout.error = getString(R.string.password_error_message)
      } else {
        password_input_layout.isErrorEnabled = false;
      }
      handleLogin()
    }
  }

  private fun CharSequence.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this)
      .matches()

  private fun CharSequence.isValidPassword() = !isNullOrEmpty() && this.length > 5

  private fun handleLogin() {
    if (username_input_layout.username.text.toString() == USERNAME && password_input_layout.password.text.toString() == PASSWORD) {
      Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT)
          .show()
    } else {
      Toast.makeText(this, "Username/password combination is wrong", Toast.LENGTH_SHORT)
          .show()
    }
  }

  companion object {
    private const val USERNAME = "admin@gmail.com"
    private const val PASSWORD = "password"
  }
}
