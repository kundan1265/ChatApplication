package com.example.chatapplication



data class User(val name: String? = null, val email: String? = null , val uid: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}