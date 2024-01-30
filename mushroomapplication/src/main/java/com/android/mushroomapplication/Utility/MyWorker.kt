import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Define the work you want to do here.
        myFunction()
        return Result.success()
    }
}

fun myFunction() {
    // Define the logic of your function here
    println("Function called at ${System.currentTimeMillis()}")
}