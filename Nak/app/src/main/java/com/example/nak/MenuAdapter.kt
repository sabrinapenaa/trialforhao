package com.example.nak

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter( val context: Context, val posts: MutableList<MenuItems>)
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername : TextView
        val tvMenuDescription : TextView

        init {
            tvUsername = itemView.findViewById(R.id.tvitemName)
            tvMenuDescription = itemView.findViewById(R.id.tvitemDescription)

        }

        fun bind(post : MenuItems){
            tvMenuDescription.text = post.getDescription()
            tvUsername.text = post.getMenuItem()

            //Populate image w Glide


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        //Which layout file to use for this item
        val view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
       Log.i(TAG, "POsts size " + posts.size )
        return posts.size
    }
    // Clean all elements of the recycler
    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(postList: List<MenuItems>) {
        posts.addAll(postList)
        notifyDataSetChanged()
    }
    companion object{
        const val TAG = "RestaurantActivity"
        val REQUEST_CODE = 10

    }
}