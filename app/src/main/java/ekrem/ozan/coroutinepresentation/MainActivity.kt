package ekrem.ozan.coroutinepresentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val totalTime = measureTimeMillis {
                val firstPrice = getPriceOne()
                val secondPrice = getPriceTwo()

                //Switching to default context
                withContext(Dispatchers.Default) {
                    val totalPrice = calculatePrice(firstPrice, secondPrice)

                    //Switching to main context
                    withContext(Dispatchers.Main){
                        val title = findViewById<TextView>(R.id.tvTitle)
                        title.text = "Total  price is $totalPrice"
                    }
                }
            }
            Log.d(TAG, "Total  time is $totalTime")
        }
    }

    private suspend fun calculatePrice(priceOne: Int, priceTwo: Int): Int {
        delay(2000)
        return priceOne + priceTwo
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