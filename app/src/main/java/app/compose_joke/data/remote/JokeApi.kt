package app.compose_joke.data.remote

import app.compose_joke.data.model.Joke
import retrofit2.http.GET

interface JokeApi {

    @GET("random_joke")
    suspend fun getRandomJoke(): Joke

}