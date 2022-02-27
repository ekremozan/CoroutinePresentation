package ekrem.ozan.coroutinepresentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            delay(2000L)
            Log.d(TAG, "Coroutine... ${Thread.currentThread().name}")
        }
        Log.d(TAG, "Main... ${Thread.currentThread().name}")
    }
}