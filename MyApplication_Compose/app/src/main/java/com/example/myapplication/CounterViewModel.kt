import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = mutableStateOf(0)
    val count: State<Int> = _count

    // Function to increment the counter
    fun increment() {
        _count.value += 1
    }

    // Function to decrement the counter
    fun decrement() {
        _count.value -= 1
    }

    fun viewModel(){
    }
}
