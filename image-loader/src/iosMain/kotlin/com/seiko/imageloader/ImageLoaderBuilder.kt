package com.seiko.imageloader

import com.seiko.imageloader.component.decoder.SkiaImageDecoder
import com.seiko.imageloader.component.fetcher.KtorUrlFetcher
import com.seiko.imageloader.component.keyer.KtorUlKeyer
import com.seiko.imageloader.component.mapper.KtorUrlMapper
import com.seiko.imageloader.request.Options
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class ImageLoaderBuilder : CommonImageLoaderBuilder<ImageLoaderBuilder>() {

    override var httpClient: Lazy<HttpClient> = lazy { HttpClient(Darwin) }
    override var requestDispatcher: CoroutineDispatcher = Dispatchers.Default

    actual fun build(): ImageLoader {
        val components = componentBuilder
            // Mappers
            .add(KtorUrlMapper())
            // Keyers
            .add(KtorUlKeyer())
            // Fetchers
            .add(KtorUrlFetcher.Factory(httpClient))
            // .add(FileFetcher.Factory())
            // Decoders
            .add(SkiaImageDecoder.Factory())
            .build()

        return RealImageLoader(
            components = components,
            options = options ?: Options(),
            interceptors = interceptors,
            requestDispatcher = requestDispatcher,
            memoryCache = memoryCache,
            diskCache = diskCache,
        )
    }
}
