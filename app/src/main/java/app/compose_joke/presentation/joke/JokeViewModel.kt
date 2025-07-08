package app.compose_joke.presentation.joke

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.compose_joke.data.model.Joke
import app.compose_joke.domain.usecase.GetJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getJokeUseCase: GetJokeUseCase
): ViewModel() {

    var joke by mutableStateOf<Joke?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    init {
        getNewJoke()
    }

    fun getNewJoke() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                joke = getJokeUseCase()
            } catch (e: Exception) {
                error = e.message
            }
            finally {
                isLoading = false
            }
        }
    }

}