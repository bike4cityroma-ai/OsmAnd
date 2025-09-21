package org.bike4city.theurbebike.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.bike4city.theurbebike.R

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AboutScreen(
                onOpenSource = {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.tub_github_url)))
                    )
                },
                onLicenses = {
                    startActivity(Intent(this, LicensesActivity::class.java))
                }
            )
        }
    }
}

@Composable
private fun AboutScreen(onOpenSource: () -> Unit, onLicenses: () -> Unit) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.mipmap.ic_launcher_round),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            "TheUrbeBike",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            getString(R.string.tub_tagline),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
                Text(
                    getString(R.string.tub_about_text),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = onOpenSource) {
                        Text(getString(R.string.tub_open_source))
                    }
                    OutlinedButton(onClick = onLicenses) {
                        Text(getString(R.string.tub_licenses))
                    }
                }
            }
        }
    }
}
