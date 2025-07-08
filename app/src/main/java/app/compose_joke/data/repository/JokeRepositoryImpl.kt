package app.compose_joke.data.repository

import app.compose_joke.data.model.Joke
import app.compose_joke.data.remote.JokeApi
import app.compose_joke.domain.repository.JokeRepository

class JokeRepositoryImpl(
    private val api: JokeApi
) : JokeRepository{
    override suspend fun getJoke(): Joke {
        return api.getRandomJoke()
    }
}