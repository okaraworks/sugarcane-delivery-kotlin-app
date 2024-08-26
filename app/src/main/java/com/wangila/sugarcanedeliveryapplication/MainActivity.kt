package com.wangila.sugarcanedeliveryapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.lifecycle.viewmodel.compose.viewModel
import com.wangila.sugarcanedeliveryapplication.ui.theme.SugarcaneDeliveryApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SugarcaneDeliveryApplicationTheme {
                val viewModel: SugarcaneViewModel = viewModel(factory = SugarcaneViewModelFactory(application))
                SugarcaneApp(viewModel)
            }
        }
    }
}
