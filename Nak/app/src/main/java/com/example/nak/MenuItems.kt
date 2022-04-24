package com.example.nak

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseFile
import com.parse.ParseUser
@ParseClassName("MenuItems")

//Desciption
//Image
//User :user
   
    class MenuItems : ParseObject() {

        fun getDescription() : String?{
            return getString(KEY_DESCRIPTION)

        }
        fun setDescription(description: String) {
            put(KEY_DESCRIPTION,description)

        }
        fun getMenuItem(): String?{
            return getString(KEY_MENUITEM)
        }
        fun setMenuItem(Itemname: String){
            put(KEY_MENUITEM, Itemname)
        }
        fun getUser():ParseUser?{
            return getParseUser(KEY_USER)
        }
        fun setUser(parseUser: ParseUser){
            put(KEY_USER, parseUser)
        }

        companion object{
            const val KEY_DESCRIPTION = "ItemDescription"
            const val KEY_MENUITEM = "Itemname"
            const val KEY_USER = "user"

        }

    }

