package com.example.smarthome.utils

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreReference {
    private var firestore: CollectionReference? = null

    fun getInstance(context: Context): CollectionReference {
        if (firestore == null) {
            firestore = FirebaseFirestore.getInstance(FirebaseApp.initializeApp(context)!!)
                .collection("devices")
        }
        return firestore as CollectionReference
    }

}