package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contact.data.UserDatabase
import com.example.contact.model.User
import com.example.contact.reposority.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }
}