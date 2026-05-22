package mx.com.sheff.cardcollectiontracker.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import mx.com.sheff.cardcollectiontracker.core.network.lorcast.api.LorcastApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

/**
 * Hilt module that provides networking dependencies for the Lorcast API.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val LORCAST_BASE_URL = "https://api.lorcast.com/v0/"

    /**
     * Configures kotlinx-serialization to be lenient with unknown fields,
     * so future API additions don't break the app.
     */
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    /**
     * Configures HTTP logging. In release builds we lower the level to
     * avoid leaking response bodies to logs.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            // TODO: switch to NONE for release builds when add BuildConfig flags
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(LORCAST_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideLorcastApiService(retrofit: Retrofit): LorcastApiService =
        retrofit.create(LorcastApiService::class.java)
}
