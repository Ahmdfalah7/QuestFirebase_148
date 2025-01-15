package com.example.firebase.model

data class Mahasiswa (
    val nim: String,
    val nama: String,
    val jenis_kelamin: String,
    val kelas: String,
    val alamat: String,
    val angkatan: String,
    val judul_skripsi: String,
    val dosen_pembimbing: String
){
    constructor(): this("","","","","","","","")
}