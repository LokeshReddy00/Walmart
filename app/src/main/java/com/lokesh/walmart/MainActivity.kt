package com.lokesh.walmart

import CapitalRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lokesh.walmart.Models.Capital
import SearchBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 16.dp)
            ) {
                CapitalScreen()
            }
        }
    }
}

@Composable
fun CapitalScreen() {
    val capitalRepository = remember { CapitalRepository() }
    var capitals by remember { mutableStateOf<List<Capital>>(emptyList()) }
    var filteredCapitals by remember { mutableStateOf<List<Capital>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        capitalRepository.fetchCountries { fetchedCountries ->
            capitals = fetchedCountries ?: emptyList()
            filteredCapitals = capitals
            isLoading = false
        }
    }

    if (isLoading) {
        Text(
            text = "Loading...",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    } else {
        Column {
            SearchBar(searchQuery) { query ->
                searchQuery = query
                filteredCapitals = capitals.filter { it.name.contains(query, ignoreCase = true) }
            }

            CapitalList(filteredCapitals)
        }
    }
}

@Composable
fun CapitalList(capitals: List<Capital>) {
    LazyColumn {
        items(capitals.size) { index ->
            val capital = capitals[index]
            CapitalItem(capital)

            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun CapitalItem(capital: Capital) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${capital.name}, ${capital.region}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = capital.code,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.End
            )
        }

        Text(
            text = capital.capital,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
    }
}
