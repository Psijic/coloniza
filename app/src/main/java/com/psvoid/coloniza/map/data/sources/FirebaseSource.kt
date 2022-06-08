package com.psvoid.coloniza.map.data.sources

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.psvoid.coloniza.map.domain.EventFilter
import com.psvoid.coloniza.map.domain.EventItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber

class FirebaseSource {
    private val firebaseDb: DatabaseReference = Firebase.database.reference

    init {
//        Firebase.database.setPersistenceEnabled(true)
    }

    @ExperimentalCoroutinesApi
    suspend fun fetchEvents(countryName: String, period: EventFilter.Period): List<EventItem>? =
        suspendCancellableCoroutine {
            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Timber.v("Fetch Firebase markers: onDataChange")
                    val data = dataSnapshot.getValue<List<EventItem>>()
                    it.resume(data) {}
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.w("Fetch Firebase markers: onCancelled ${databaseError.toException()}")
                    it.cancel()
                }
            }

            val db = firebaseDb.child("events").child(countryName)
            db.addListenerForSingleValueEvent(listener)
            it.invokeOnCancellation { db.removeEventListener(listener) }
        }

    @ExperimentalCoroutinesApi
    fun fetchEventsFlow(countryName: String, period: EventFilter.Period): Flow<List<EventItem>?> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Timber.v("Fetch Firebase markers: onDataChange")
                val data = dataSnapshot.getValue<List<EventItem>>()
                trySend(data)
                close()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Timber.w("Fetch Firebase markers: onCancelled ${databaseError.toException()}")
                cancel()
            }
        }
        val ref = firebaseDb.child("events").child(countryName)
        ref.addListenerForSingleValueEvent(listener)
        awaitClose { ref.removeEventListener(listener) }
    }
}
