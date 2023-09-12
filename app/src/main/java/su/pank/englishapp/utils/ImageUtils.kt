package su.pank.englishapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

suspend fun getBitmapFromUri(url: URL): Bitmap {
    return BitmapFactory.decodeStream(withContext(Dispatchers.IO) {
        withContext(Dispatchers.IO) {
            url.openConnection()
        }.getInputStream()
    })
}