package com.namrata.skosystems.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.namrata.skosystems.data.local.UserEntity
import com.namrata.skosystems.data.mappers.toUser
import com.namrata.skosystems.data.remote.UserDTO
import com.namrata.skosystems.data.remote.UserRepository
import com.namrata.skosystems.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    pager: Pager<Int, UserEntity>,
    repository: UserRepository
): ViewModel() {
//    val pager = pager
//    var userPagingFlow: Flow<PagingData<User>>? = null
    val userPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toUser() }
        }
        .cachedIn(viewModelScope)


//    val list = repository.getQuotes().cachedIn(viewModelScope)

//    fun setData(it: UserDTO?) {
//        println("Data received ${it.toString()}")
//        userPagingFlow = pager
//            .flow
//            .map { pagingData ->
//                pagingData.map { it.toUser() }
//            }
//            .cachedIn(viewModelScope)
//
//
//    }

}