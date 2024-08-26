package com.wangila.sugarcanedeliveryapplication



import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SugarcaneViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SugarcaneRepository
    val pendingDeliveries: LiveData<List<Sugarcane>>
    val deliveredHistory: LiveData<List<Sugarcane>>

    init {
        val sugarcaneDao = AppDatabase.getDatabase(application).sugarcaneDao()
        repository = SugarcaneRepository(sugarcaneDao)
        pendingDeliveries = repository.pendingDeliveries.asLiveData()
        deliveredHistory = repository.deliveredHistory.asLiveData()
    }

    fun insert(sugarcane: Sugarcane) = viewModelScope.launch {
        repository.insert(sugarcane)
    }

    fun delete(sugarcane: Sugarcane) = viewModelScope.launch {
        repository.delete(sugarcane)
    }

    fun update(sugarcane: Sugarcane) = viewModelScope.launch {
        repository.update(sugarcane)
    }
}

class SugarcaneViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SugarcaneViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SugarcaneViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
