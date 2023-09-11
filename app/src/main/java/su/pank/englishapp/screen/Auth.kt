package su.pank.englishapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberLoginWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.ui.AuthForm
import io.github.jan.supabase.compose.auth.ui.LocalAuthState
import io.github.jan.supabase.compose.auth.ui.ProviderButtonContent
import io.github.jan.supabase.compose.auth.ui.ProviderIcon
import io.github.jan.supabase.compose.auth.ui.email.OutlinedEmailField
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
import io.github.jan.supabase.compose.auth.ui.providerPainter
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.Google

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import su.pank.englishapp.R
import su.pank.englishapp.client
import su.pank.englishapp.user


@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun Auth(navController: NavController) {
    val state by client.gotrue.sessionStatus.collectAsState()
    LaunchedEffect(state){
        when (state){
            is SessionStatus.Authenticated -> {
                user = client.gotrue.currentUserOrNull()
                if (user != null){
                    println(user)
                    navController.navigate("helloScreen")
                }
            }

            else -> {}
        }
    }
    AuthForm {
        val action = client.composeAuth.rememberLoginWithGoogle(
            onResult = { result -> //optional error handling
                when (result) {
                    is NativeSignInResult.Success -> {
                        println(result)
                        CoroutineScope(Dispatchers.Main).launch{
                            user = client.gotrue.currentUserOrNull()

                        }
                    }
                    is NativeSignInResult.ClosedByUser -> {}
                    is NativeSignInResult.Error -> {
                        println(result.message)
                    }
                    is NativeSignInResult.NetworkError -> {}
                }
            },
            fallback = { // optional: add custom error handling, not required by default

            }
        )
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Hello to use this app you need auth by google")
            FilledIconButton(onClick = { action.startFlow() }, modifier = Modifier.size(100.dp)) {
                Icon(painter = painterResource(id = R.drawable.google), contentDescription = null)
            }
        }

    }
}