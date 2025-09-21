package org.bike4city.theurbebike.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bike4city.theurbebike.R
import org.json.JSONArray

data class License(val name: String, val license: String, val url: String)

class LicensesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Legge il file licenses.json da res/raw
        val raw = resources.openRawResource(R.raw.licenses).readBytes().decodeToString()
        val list = mutableListOf<License>()
        JSONArray(raw).let { arr ->
            for (i in 0 until arr.length()) {
                val o = arr.getJSONObject(i)
                list += License(
                    o.getString("name"),
                    o.getString("license"),
                    o.getString("url")
                )
            }
        }

        setContent { LicensesScreen(list) }
    }
}

@Composable
private fun LicensesScreen(itemsList: List<License>) {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(itemsList) { item ->
                    ElevatedCard {
                        Column(Modifier.padding(16.dp)) {
                            Text(item.name, style = MaterialTheme.typography.titleMedium)
                            Text(item.license, style = MaterialTheme.typography.bodySmall)
                            Text(item.url, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
