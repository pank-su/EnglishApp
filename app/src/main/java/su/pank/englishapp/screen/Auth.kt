package su.pank.englishapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ui.AuthForm
import io.github.jan.supabase.compose.auth.ui.LocalAuthState
import io.github.jan.supabase.compose.auth.ui.ProviderButtonContent
import io.github.jan.supabase.compose.auth.ui.email.OutlinedEmailField
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
import io.github.jan.supabase.gotrue.providers.Github
import io.github.jan.supabase.gotrue.providers.OAuthProvider
import su.pank.englishapp.client

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun Auth() {
    AuthForm {
        var password by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }

        val state = LocalAuthState.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedEmailField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") })
            OutlinedPasswordField(value = password, onValueChange = { password = it }, label = {
                Text(
                    text = "Password"
                )
            })
            Button(
                onClick = { client.}, //Login with email and password,
                enabled = state.validForm,
            ) {
                Text("Login")
            }
            Button(onClick = { /*TODO*/ }) {
                ProviderButtonContent(provider = Github)
            }
        }
    }
}