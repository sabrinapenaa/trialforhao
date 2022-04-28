package com.example.nak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class RestaurantLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_login)
        val login = findViewById<Button>(R.id.login_button)
        val signup = findViewById<Button>(R.id.signupBtn)
//        login.setBackgroundColor();
//        signup.setBackgroundColor(Color.WHITE);
//        signup.setTextColor(Color.BLACK);
//        login.setTextColor(Color.BLACK);
        //Check if user is logged in
        //If they are, take them to mainActivity

        if(ParseUser.getCurrentUser() != null){
            goToMainActivity()
            //goToTrialActivity()
        }
        //else they must login


        findViewById<Button>(R.id.login_button).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username,password)
        }

        findViewById<Button>(R.id.signupBtn).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username,password)
        }
//        val firstObject = ParseObject("FirstClass")
//        firstObject.put("message","Hey ! First message from android. Parse is now connected")
//        firstObject.saveInBackground {
//            if (it != null){
//                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
//            }else{
//                Log.d("MainActivity","Object saved.")
//            }
//        }
    }
    private fun signUpUser(username: String, password: String){
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
                //User has susscessfully signed in
                //TODO nagivte the user to MainActivitysabr
                loginUser(username,password)
                //TODO: Show a toast to indicate user sucessfully signed up for an account
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                //TODO show a toast to tell user sign up was not successuful
                e.printStackTrace()
            }

        }
    }
    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                // Hooray!  The user is logged in.
                Log.i(TAG, "Successfully logged in user")
                goToMainActivity()
                //goToTrialActivity()
            } else {
                // Signup failed.  Look at the ParseException to see what happened.
                e.printStackTrace()
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }
    private fun goToMainActivity(){
        val intent = Intent(this@RestaurantLoginActivity, RestaurantActivity::class.java)
        startActivity(intent)
        finish() //close out login activity
    }


    companion object{
        const val TAG = "RestaurantLoginActivity"
    }

}