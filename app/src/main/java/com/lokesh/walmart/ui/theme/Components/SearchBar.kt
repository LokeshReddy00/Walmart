
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.lokesh.walmart.R

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    val textState = remember { mutableStateOf(TextFieldValue(searchQuery)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            modifier = Modifier.padding(end = 8.dp)
        )

        val textFieldColors = TextFieldDefaults.colors(

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent

        )

        TextField(
            value = textState.value,
            onValueChange = { newText ->
                textState.value = newText
                onSearchQueryChanged(newText.text)
            },
            placeholder = { Text(text = "Search...") },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors
        )
    }
}

