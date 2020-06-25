package `in`.surelocal.mynewsapp

import `in`.surelocal.mynewsapp.ui.gallery.GalleryFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newtitle=intent.getStringExtra("title")
        val newdescription=intent.getStringExtra("description")
        setContentView(R.layout.activity_gallery)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GalleryFragment(newtitle,newdescription))
                .commitNow()
        }
    }
}