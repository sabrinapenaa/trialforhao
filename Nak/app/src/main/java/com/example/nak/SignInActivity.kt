package com.example.nak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        findViewById<Button>(R.id.btnCustomer).setOnClickListener {
            goToCustomerPage()
        }
        findViewById<Button>(R.id.btnRestaurant).setOnClickListener {
            goToRestaurantPage()
            Log.i(TAG, "Got to restarutnt page")
        }
    }

    private fun goToRestaurantPage() {
        val intent = Intent(this@SignInActivity, RestaurantActivity::class.java)
        startActivity(intent)
        finish() //close out login activity
    }

    private fun goToCustomerPage() {
        val intent = Intent(this@SignInActivity, MenuListActivity::class.java)
        startActivity(intent)
        finish() //close out login activity
    }
    companion object{
        const val TAG = "RestaurantActivity"
        val REQUEST_CODE = 10

    }
}