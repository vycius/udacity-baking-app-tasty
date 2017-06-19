package com.vycius.tasty.image

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.media.MediaMetadataRetriever
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.vycius.tasty.image.model.VideoThumbnailUrl
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream


class VideoThumbnailFetcher(private val videoThumbnailUrl: VideoThumbnailUrl) : DataFetcher<InputStream> {

    override fun getDataClass(): Class<InputStream> =
            InputStream::class.java

    override fun cleanup() {
    }

    override fun getDataSource(): DataSource = DataSource.REMOTE

    override fun cancel() {
    }

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        val bitmap: Bitmap?
        var mediaMetadataRetriever: MediaMetadataRetriever? = null
        try {
            mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(videoThumbnailUrl.url, hashMapOf())

            bitmap = mediaMetadataRetriever.frameAtTime

        } finally {
            mediaMetadataRetriever?.release()
        }

        if (bitmap == null) {
            callback.onLoadFailed(Exception("Bitmap is null"))
        } else {
            val bos = ByteArrayOutputStream()
            bitmap.compress(CompressFormat.PNG, 100, bos)
            val bs = ByteArrayInputStream(bos.toByteArray())

            callback.onDataReady(bs)
        }
    }

}