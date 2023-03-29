package com.nistruct.paging3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.nistruct.paging3.ui.theme.Paging3Theme
import com.nistruct.paging3.ui.theme.view.BeerScreen
import com.nistruct.paging3.ui.theme.viewmodel.BeerScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Paging3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var viewModel: BeerScreenViewModel = hiltViewModel()
                    val beers = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                  BeerScreen(beers)
                   // TestScreen()
                }
            }
        }
    }
}

@Composable
fun TestScreen() {
    Text(text = "test")
}


