package app.compose_joke.domain.usecase

import app.compose_joke.data.model.Joke
import app.compose_joke.domain.repository.JokeRepository

class GetJokeUseCase(private val repository: JokeRepository) {
    suspend operator fun invoke(): Joke {
        return repository.getJoke()
    }
}