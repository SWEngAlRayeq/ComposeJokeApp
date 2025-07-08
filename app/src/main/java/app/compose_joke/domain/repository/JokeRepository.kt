package app.compose_joke.domain.repository

import app.compose_joke.data.model.Joke

interface JokeRepository {
    suspend fun getJoke(): Joke
}