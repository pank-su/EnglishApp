package su.pank.englishapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue

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
    LaunchedEffect(state) {
        when (state) {
            is SessionStatus.Authenticated -> {
                user = client.gotrue.currentUserOrNull()
                if (user != null) {
                    println(user)
                    navController.navigate("helloScreen"){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                }
            }

            else -> {}
        }
    }
    if (state !is SessionStatus.NotAuthenticated) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else
        AuthForm {
            val action = client.composeAuth.rememberLoginWithGoogle(
                onResult = { result -> //optional error handling
                    when (result) {
                        is NativeSignInResult.Success -> {
                            println(result)
                            CoroutineScope(Dispatchers.Main).launch {
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FilledIconButton(
                    onClick = { action.startFlow() },
                    modifier = Modifier.size(100.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null
                    )
                }
            }

        }
}