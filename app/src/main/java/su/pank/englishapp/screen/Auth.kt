package su.pank.englishapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.composable.rememberLoginWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.ui.AuthForm
import io.github.jan.supabase.compose.auth.ui.LocalAuthState
import io.github.jan.supabase.compose.auth.ui.ProviderButtonContent
import io.github.jan.supabase.compose.auth.ui.email.OutlinedEmailField
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.Github
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.OAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import su.pank.englishapp.client

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun Auth() {
    LaunchedEffect(null){
        client.gotrue.sessionStatus.collect {
            when(it) {
                is SessionStatus.Authenticated -> println(it.session.user)
                SessionStatus.LoadingFromStorage -> println("Loading from storage")
                SessionStatus.NetworkError -> println("Network error")
                SessionStatus.NotAuthenticated -> println("Not authenticated")
            }
        }
    }

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
                onClick = { }, //Login with email and password,
                enabled = state.validForm,
            ) {
                Text("Login")
            }
            Button(onClick = {
                CoroutineScope(Dispatchers.Main).launch{
                    client.gotrue.loginWith(Github)
                }

            }) {
                ProviderButtonContent(provider = Github)
            }
            Button(onClick = {
                CoroutineScope(Dispatchers.Main).launch{
                    client.gotrue.loginWith(Github)
                }

            }) {
                ProviderButtonContent(provider = Google)
            }
        }
    }
}