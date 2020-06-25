package `in`.surelocal.mynewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.surelocal.mynewsapp.login.LogInFragment

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, LogInFragment.newInstance())
//                .commitNow()
//        }
        }

}