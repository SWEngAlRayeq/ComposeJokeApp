package app.compose_joke.di

import app.compose_joke.data.remote.JokeApi
import app.compose_joke.data.repository.JokeRepositoryImpl
import app.compose_joke.domain.repository.JokeRepository
import app.compose_joke.domain.usecase.GetJokeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://official-joke-api.appspot.com/"

    @Provides
    @Singleton
    fun provideJokeApi(client: OkHttpClient): JokeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeApi::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideJokeRepository(api: JokeApi): JokeRepository {
        return JokeRepositoryImpl(api)
    }

    @Provides
    fun provideGetJokeUseCase(repo: JokeRepository): GetJokeUseCase {
        return GetJokeUseCase(repo)
    }

}