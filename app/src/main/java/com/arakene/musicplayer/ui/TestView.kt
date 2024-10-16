package com.arakene.musicplayer.ui

import android.Manifest
import android.accounts.AccountManager
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.arakene.musicplayer.BuildConfig
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TestView(
    viewModel: TestViewModel = TestViewModel()
) {
    val context = LocalContext.current

    val util = remember {
        Util()
    }

    val scope = rememberCoroutineScope()

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
    }

    val testLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        Log.d(">>>>", "뭐오는거니? $it")

    }

    val chooseAccountLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        Log.d(">>>>", "TEST $it")
        if (it.resultCode == Activity.RESULT_OK) {

            val accountName =
                it.data?.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)

            util.setName(accountName ?: "")

            util.setService(context)
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.GET_ACCOUNTS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            launcherMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.INTERNET
                )
            )
        }

        util.setCredential(context)
    }


    Column {

        Button(onClick = {
            util.getAccountIntent()?.let {
                chooseAccountLauncher.launch(it)
            }
        }) {
            Text(text = "계정 선택")
        }

        Button(
            onClick = {

                val ceh = CoroutineExceptionHandler { coroutineContext, throwable ->
                    if (throwable is UserRecoverableAuthIOException) {
                        testLauncher.launch(throwable.intent)
                    }

                    throwable.printStackTrace()
                }

                scope.launch(Dispatchers.IO + ceh) {
                    val request = util.youtube.search()
                        .list(listOf("snippet"))
                    val response = request
//                        .setForMine(true)
                        .setKey(BuildConfig.youtube_key)
                        .setQ("노돌리")
                        .setType(listOf("video"))
                        .execute()

                    Log.d(">>>>", "RESPONSE")

                    val youtubeURL = "https://www.youtube.com/watch?v=${response.items.first().id}"

                    Log.d(">>>>", "url $youtubeURL")
                }
            }
        ) {
            Text("예제 테스트")

        }

    }

}
