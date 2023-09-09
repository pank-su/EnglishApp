package su.pank.englishapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import su.pank.englishapp.ui.theme.EnglishAppTheme

val client = createSupabaseClient(
    supabaseUrl = "https://hatdnfxpjxvvndrelqrn.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhhdGRuZnhwanh2dm5kcmVscXJuIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTQyNDc3OTksImV4cCI6MjAwOTgyMzc5OX0.dTbRCTBeM53JSaJzSLaB0vEIJFOwTNKDaODW_J9YBzE"
) {
    install(Postgrest)
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GeneralNavigation()
                }
            }
        }
    }
}

