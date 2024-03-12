package com.namrata.skosystems

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.namrata.skosystems.data.remote.DataPoller
import com.namrata.skosystems.data.remote.UserApi
import com.namrata.skosystems.data.remote.UserRepository
import com.namrata.skosystems.presentation.UserViewModel
import com.namrata.skosystems.ui.theme.ComposePaging3CachingTheme
import dagger.hilt.android.AndroidEntryPoint
import  com.namrata.skosystems.presentation.UserScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userApi: UserApi
//    lateinit var repository: UserRepository
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePaging3CachingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = hiltViewModel<UserViewModel>()
                    val users = viewModel.userPagingFlow.collectAsLazyPagingItems()
                    UserScreen(users = users)

//                    repository = UserRepository(userApi = userApi)
//                    var dataPoller =  DataPoller(repository, Dispatchers.IO)
//                    val data1 =  dataPoller.poll(10000)
//
//                    CoroutineScope(Dispatchers.IO).launch {
//                        data1.collect{
//                            viewModel.setData(it)
//                        }
//                    }
                }
            }
        }
    }
}