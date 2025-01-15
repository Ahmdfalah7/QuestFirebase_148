package com.example.firebase.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.model.Mahasiswa
import com.example.firebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val mahasiswaRepository: MahasiswaRepository
) : ViewModel() {

    private val nim: String = checkNotNull(savedStateHandle["nim"]) // Ambil nim dari savedState

    // Menggunakan StateFlow untuk detailMhsUiState
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    init {
        getMhsbyNim()
    }

    fun getMhsbyNim() {
        viewModelScope.launch {
            mahasiswaRepository.getMahasiswabyNim(nim)
                .onStart {
                   _detailUiState.value = DetailUiState.Loading
                }
                .catch {
                    // Tangani kesalahan
                    _detailUiState.value = DetailUiState.Error
                }
                .collect { mahasiswa ->
                    _detailUiState.value = DetailUiState.Success(mahasiswa) // Jika berhasil, update UI
                }
        }
    }
}