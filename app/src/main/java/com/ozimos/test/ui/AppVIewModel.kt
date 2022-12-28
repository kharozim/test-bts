package com.ozimos.test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozimos.test.data.ApiService
import com.ozimos.test.data.request.LoginRequest
import com.ozimos.test.data.request.RegisterRequest
import com.ozimos.test.data.response.CheckListResponse
import com.ozimos.test.data.response.LoginResponse
import com.ozimos.test.util.StateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppVIewModel @Inject constructor(private val service: ApiService) : ViewModel() {

    private val _loginData = MutableLiveData<StateUtil<LoginResponse>>()
    val loginData: LiveData<StateUtil<LoginResponse>>
        get() = _loginData

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                _loginData.postValue(StateUtil.Loading)
                val request = LoginRequest(username = username, password = password)
                val response = service.login(request)

                if (response.isSuccessful) {
                    val data = response.body()?.data ?: LoginResponse("")
                    val message = response.body()?.message ?: ""
                    _loginData.postValue(StateUtil.Success(message = message, data = data))
                } else {
                    val errorMessage = response.body()?.errorMessage ?: "Error"
                    _loginData.postValue(StateUtil.Failed(message = errorMessage))
                }
            } catch (e: Exception) {
                _loginData.postValue(StateUtil.Failed(message = e.localizedMessage))

            }

        }
    }

    private val _registerData = MutableLiveData<StateUtil<Boolean>>()
    val registerData: LiveData<StateUtil<Boolean>>
        get() = _registerData

    fun register(email: String, username: String, password: String) {
        viewModelScope.launch {

            try {
                _registerData.postValue(StateUtil.Loading)
                val request =
                    RegisterRequest(email = email, username = username, password = password)
                val response = service.register(request)

                if (response.isSuccessful) {
                    val data = response.body()?.data ?: LoginResponse("")
                    val message = response.body()?.message ?: ""
                    _registerData.postValue(StateUtil.Success(message = message, data = true))
                } else {
                    val errorMessage = response.body()?.errorMessage ?: "Error"
                    _registerData.postValue(StateUtil.Failed(message = errorMessage))
                }
            } catch (e: Exception) {
                _registerData.postValue(StateUtil.Failed(message = e.localizedMessage))

            }

        }
    }

    private val _checkList = MutableLiveData<StateUtil<List<CheckListResponse>>>()
    val checkList: LiveData<StateUtil<List<CheckListResponse>>>
        get() = _checkList

    fun getChecklit() {
        viewModelScope.launch {
            try {
                _registerData.postValue(StateUtil.Loading)
                val response = service.getChcecklist()

                if (response.isSuccessful) {
                    val data = response.body()?.data ?: emptyList()
                    val message = response.body()?.message ?: ""
                    _checkList.postValue(StateUtil.Success(message = message, data = data))
                } else {
                    val errorMessage = response.body()?.errorMessage ?: "Error"
                    _checkList.postValue(StateUtil.Failed(message = errorMessage))
                }
            } catch (e: Exception) {
                _checkList.postValue(StateUtil.Failed(message = e.localizedMessage))

            }

        }
    }
}