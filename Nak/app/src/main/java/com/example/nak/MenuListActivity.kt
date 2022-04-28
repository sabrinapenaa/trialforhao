package com.example.nak
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.nak.MenuAdapter

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class MenuListActivity : AppCompatActivity() {
    //lateinit var swipeContainer : SwipeRefreshLayout
    lateinit var postRecyclerView: RecyclerView
    lateinit var adapter : MenuAdapter
    var allPosts : MutableList<MenuItems> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_list)
       //super.onViewCreated(view, savedInstanceState)

        //This is where we set up our view

        postRecyclerView = findViewById(R.id.postRecyclerView)

        //Steps to populate RecyclerView
        //1. Crete layout for each row in list
        //2. Create data source for each row (This is the Post class)
        //3. Create adapter that will bridge data and row layout (PostAdapter class)
        //4. Set adapter on RecyclerView


        adapter = MenuAdapter(this, allPosts)
        postRecyclerView.adapter = adapter
        //5. Set layout manager on RecyclerView
        postRecyclerView.layoutManager = LinearLayoutManager(this)

        // Setup refresh listener which triggers new data loading



        queryPosts()

    }


    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<MenuItems> = ParseQuery.getQuery(MenuItems::class.java)
        // Specify the object id
        query.include(MenuItems.KEY_USER)
        query.addDescendingOrder("createdAt")
       query.whereEqualTo(MenuItems.KEY_USER, ParseUser.getCurrentUser())
        //Only return the current most 20 posts

        query.findInBackground( object: FindCallback<MenuItems> {
            //Find all Post objectrs
            override fun done(posts: MutableList<MenuItems>?, e: ParseException?) {
                if( e!= null){
                    Log.e(TAG, "Error fetching posts")
                }else{
                    if(posts != null){
                        for(post in posts){
                            Log.i(TAG, "Post: " + post.getDescription() + " , menu item: " + post.getMenuItem())

                        }
                       // adapter.clear()
                        allPosts.addAll(posts)
                        Log.i(TAG, "Added all")
                        adapter.notifyDataSetChanged()
                    }

                }
            }

        })
    }

    companion object{
        const val TAG = "MenuListActitiy"
    }
}