package com.example.nak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.parse.ParseFile
import com.parse.ParseUser
import java.io.File

class RestaurantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        //this is where the restaurant will add the items
        findViewById<Button>(R.id.submitbtn).setOnClickListener {
            //send post to server without an image
            //Get the description that they have inputted
            val description = findViewById<EditText>(R.id.etMenuDesc).text.toString()
            val menuItem  = findViewById<EditText>(R.id.etItemName).text.toString()
            val user = ParseUser.getCurrentUser()

            submitPost(description,menuItem,user)
            findViewById<EditText>(R.id.etMenuDesc).text.clear()
            findViewById<EditText>(R.id.etItemName).text.clear()

            }
        }
    private fun submitPost(description: String, itemName : String, user: ParseUser) {
        //Createhe post object
        val post = MenuItems()
        post.setDescription(description)
        post.setMenuItem(itemName)
        post.setUser(user)
        post.saveInBackground { exception ->
            if(exception != null){
                Log.e(MainActivity.TAG, "Error while saving post")
                exception.printStackTrace()

            }else{
                Log.i(MainActivity.TAG, "Successfully saved post")


            }
        }
    }

}

