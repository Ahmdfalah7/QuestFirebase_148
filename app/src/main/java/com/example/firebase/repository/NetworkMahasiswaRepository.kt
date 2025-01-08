package com.example.firebase.repository

import com.example.firebase.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkMahasiswaRepository (
    private val firestore : FirebaseFirestore
) : MahasiswaRepository{
    override suspend fun getMahasiswa(): Flow<List<Mahasiswa>> = callbackFlow {
        val mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("nim", Query.Direction.ASCENDING)
            .addSnapshotListener{ value, error ->
                if (value != null){
                    val mhsList = value.documents.mapNotNull {
                        it.toObject(Mahasiswa::class.java)!!
                    }
                }
                awaitClose{
                    mhsCollection.remove()
                }
            }
    }

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMahasiswa(nim: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getMahasiswabyNim(nim: String): Mahasiswa {
        TODO("Not yet implemented")
    }
}