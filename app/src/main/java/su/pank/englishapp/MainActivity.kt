package su.pank.englishapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.ktor.client.engine.okhttp.*



import su.pank.englishapp.navs.GeneralNavigation
import su.pank.englishapp.ui.theme.EnglishAppTheme

val client = createSupabaseClient(
    supabaseUrl = "https://hatdnfxpjxvvndrelqrn.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhhdGRuZnhwanh2dm5kcmVscXJuIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTQyNDc3OTksImV4cCI6MjAwOTgyMzc5OX0.dTbRCTBeM53JSaJzSLaB0vEIJFOwTNKDaODW_J9YBzE"
) {
    install(Postgrest)
    install(GoTrue) {
        scheme = "public"
        host = "pank.su"
        autoSaveToStorage = true
        autoLoadFromStorage = true
    }
    httpEngine = OkHttpEngine(OkHttpConfig())
    install(ComposeAuth) {
        googleNativeLogin(serverClientId = "645301365787-k93pghhiurjn9onctao1402mv65l64n9.apps.googleusercontent.com")
    }
    install(Realtime){

    }
}


var user: UserInfo? = null


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            EnglishAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface
                ) {
                    GeneralNavigation()
                }
            }
        }
    }
}

