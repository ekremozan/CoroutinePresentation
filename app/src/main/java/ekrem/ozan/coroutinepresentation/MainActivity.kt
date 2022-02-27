package ekrem.ozan.coroutinepresentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Default) {
            val totalTime = measureTimeMillis {
                val firstPrice = getPriceOne()
                val secondPrice = getPriceTwo()

                val totalPrice = firstPrice + secondPrice

                Log.d(TAG, "Total  price is $totalPrice")
            }
            Log.d(TAG, "Total  time is $totalTime")
        }
    }

    private suspend fun getPriceOne(): Int {
        delay(2500)
        return 5
    }

    private suspend fun getPriceTwo(): Int {
        delay(500)
        return 7
    }
}