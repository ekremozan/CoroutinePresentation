package ekrem.ozan.coroutinepresentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timer = GlobalScope.launch(Dispatchers.Default) {
            for (i in 35..45) {
                if (isActive)
                    Log.d(TAG, "Result for $i: ${fib(i)}")
            }
            Log.d(TAG, "Ending long running process....")
        }

        GlobalScope.launch(Dispatchers.Main) {
            delay(300L)
            timer.cancel()
            Log.d(TAG, "Main thread is continuing....")
        }
    }

    private fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }

}