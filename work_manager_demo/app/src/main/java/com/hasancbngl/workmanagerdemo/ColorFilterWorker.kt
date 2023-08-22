package com.hasancbngl.workmanagerdemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.LightingColorFilter
import android.graphics.Paint
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class ColorFilterWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val imageFile = workerParameters.inputData.getString(WorkerKeys.IMAGE_URI)
            ?.toUri()?.toFile()
        delay(5000)
        return imageFile?.let { file ->
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            val resultBitmap = bitmap.copy(bitmap.config, true)
            val paint = Paint()
            paint.colorFilter = LightingColorFilter(0x07F56D, 1)
            val canvas = Canvas(resultBitmap)
            canvas.drawBitmap(resultBitmap, 0f, 0f, paint)

            //save the file
            withContext(Dispatchers.IO) {
                val resultImageFile = File(context.cacheDir, "new-image.jpg")
                val outputStream = FileOutputStream(resultImageFile)
                val successful = resultBitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    90,
                    outputStream
                )
                if (successful) Result.success(
                    workDataOf(
                        WorkerKeys.FILTER_URI to resultImageFile.toUri().toString()
                    )
                )
                else Result.failure()
            }
        } ?: Result.failure()
    }
}