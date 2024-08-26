package com.wangila.sugarcanedeliveryapplication



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.lazy.items



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SugarcaneApp(viewModel: SugarcaneViewModel) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sugarcane Delivery App") })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    label = { Text("Entry") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Send, contentDescription = null) },
                    label = { Text("Deliver") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                    label = { Text("History") }
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> EntryScreen(viewModel, Modifier.padding(innerPadding))
            1 -> DeliveryScreen(viewModel, Modifier.padding(innerPadding))
            2 -> HistoryScreen(viewModel, Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryScreen(viewModel: SugarcaneViewModel, modifier: Modifier = Modifier) {
    var type by remember { mutableStateOf("") }
    var farmer by remember { mutableStateOf("") }
    var driver by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Type") }
        )
        OutlinedTextField(
            value = farmer,
            onValueChange = { farmer = it },
            label = { Text("Farmer") }
        )
        OutlinedTextField(
            value = driver,
            onValueChange = { driver = it },
            label = { Text("Driver") }
        )
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight") }
        )
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") }
        )
        Button(
            onClick = {
                val sugarcane = Sugarcane(
                    type = type,
                    farmer = farmer,
                    driver = driver,
                    weight = weight.toDoubleOrNull() ?: 0.0,
                    location = location
                )
                viewModel.insert(sugarcane)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Save")
        }
    }
}

@Composable
fun DeliveryScreen(viewModel: SugarcaneViewModel, modifier: Modifier = Modifier) {
    val pendingDeliveries by viewModel.pendingDeliveries.observeAsState(emptyList())

    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(pendingDeliveries) { delivery ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation =CardDefaults.elevatedCardElevation()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Type: ${delivery.type}")
                    Text("Farmer: ${delivery.farmer}")
                    Text("Driver: ${delivery.driver}")
                    Text("Weight: ${delivery.weight}")
                    Text("Location: ${delivery.location}")
                    Button(
                        onClick = {
                            val updatedDelivery = delivery.copy(delivered = true)
                            viewModel.update(updatedDelivery)
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Mark as Delivered")
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryScreen(viewModel: SugarcaneViewModel, modifier: Modifier = Modifier) {
    val deliveredHistory by viewModel.deliveredHistory.observeAsState(emptyList())

    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(deliveredHistory) { delivery ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation =CardDefaults.elevatedCardElevation()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Type: ${delivery.type}")
                    Text("Farmer: ${delivery.farmer}")
                    Text("Driver: ${delivery.driver}")
                    Text("Weight: ${delivery.weight}")
                    Text("Location: ${delivery.location}")
                    Button(
                        onClick = {
                            viewModel.delete(delivery)
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
