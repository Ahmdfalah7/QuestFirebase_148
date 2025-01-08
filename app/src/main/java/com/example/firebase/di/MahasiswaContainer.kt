package com.example.firebase.di

import com.example.firebase.repository.MahasiswaRepository
import com.example.firebase.repository.NetworkMahasiswaRepository
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val mahasiswaRepository: MahasiswaRepository
}


class MahasiswaContainer : AppContainer {
    private val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    override val mahasiswaRepository : MahasiswaRepository by lazy {
        NetworkMahasiswaRepository(firestore)
    }
}
