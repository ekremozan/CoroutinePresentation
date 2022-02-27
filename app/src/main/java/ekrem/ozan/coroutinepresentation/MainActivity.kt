package ekrem.ozan.coroutinepresentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Before runBlocking")
        runBlocking {
            val totalTime = measureTimeMillis {
                launch(Dispatchers.IO) { getPriceOne() }
                launch(Dispatchers.IO) { getPriceTwo() }
                Log.d(TAG, "Start runBlocking")
                delay(3000L)
                Log.d(TAG, "End runBlocking")
            }
            Log.d(TAG, "Total  time is $totalTime")
        }
        Log.d(TAG, "After runBlocking")
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