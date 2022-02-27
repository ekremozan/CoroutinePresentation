package ekrem.ozan.coroutinepresentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            timer().join()
            Log.d(TAG, "Main thread is continuing....")
        }
    }

    private fun timer() = GlobalScope.launch(Dispatchers.Default) {
        repeat(5) {
            Log.d(TAG, "Coroutine is still working....${it}")
            delay(1000L)
        }
    }

    private suspend fun getPriceOne(): Int {
        delay(2500)
        Log.d(TAG, "Calculated price two")
        return 5
    }

    private suspend fun getPriceTwo(): Int {
        delay(1500)
        Log.d(TAG, "Calculated price one")
        return 7
    }
}