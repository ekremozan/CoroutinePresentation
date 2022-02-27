package ekrem.ozan.coroutinepresentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val firstResponse = async { networkCallOne() }
                val secondResponse = async { networkCallTwo() }

                Log.d(TAG, "AnswerOne is: ${firstResponse.await()}")
                Log.d(TAG, "AnswerTwo is: ${secondResponse.await()}")

            }
            Log.d(TAG, "Requests took $time ms.")
        }
    }

    private suspend fun networkCallOne(): String {
        delay(2000L)
        return "Answer 1"
    }

    private suspend fun networkCallTwo(): String {
        delay(3000L)
        return "Answer 2"
    }
}