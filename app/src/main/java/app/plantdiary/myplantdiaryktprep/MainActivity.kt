package app.plantdiary.myplantdiaryktprep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import app.plantdiary.myplantdiaryktprep.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragment : MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        fragment = MainFragment.newInstance();
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }


    }

    public fun addName(v: View?) {
        fragment.addName(v);
    }

}
