package com.example.coronavirustracker.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.coronavirustracker.R
import com.example.coronavirustracker.data.model.ViewPager2Fragments
import com.example.coronavirustracker.view.adapter.ViewPager2Adapter
import com.facebook.stetho.Stetho
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    val retrofitHelper by lazy { RetrofitHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Stetho for debugging
        Stetho.initializeWithDefaults(this)

        setContentView(R.layout.activity_main)

        viewPager.adapter = ViewPager2Adapter(this)

        // TabLayoutMediator needed to use ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(ViewPager2Fragments.values()[position].titleResId)
        }.attach()



        // TODO: How do you revert migrations?
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // NOTE: cannot add multiple columns in a single statement in SQLite, untlike SQL
                database.execSQL("ALTER TABLE JHUCountyResponse ADD confirmed INTEGER")
                database.execSQL("ALTER TABLE JHUCountyResponse ADD deaths INTEGER")
                database.execSQL("ALTER TABLE JHUCountyResponse ADD recovered INTEGER")
            }
        }

//        val db = AppDatabase.getDatabase(application)
//        fun getDbItems() : String {
//            db.countyDao().insert(JHUCountyResponse(1, Coordinates("1", "2"), "a1", "b2", "c3", Stats(1, 2, 3), "d4"))
//
//
//            val data: List<JHUCountyResponse> = db.countyDao().getAll()
//            var str = ""
//            data.forEach {
//                str += ""+ it +"\n"
//                Log.d("TAG-NAME", ""+it.javaClass.name)
//                Log.d("TAG-NAME", ""+it.javaClass.kotlin)
//                Log.d("TAG", ""+it)
//                Log.d("TAG-OBJ", ""+it.coordinates.latitude)
//            }
//
//            return str
//        }
//
//        Log.d("TAG", "Begin Coroutine")
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d("TAG", "Start DBItems")
//            val str = getDbItems()
//            Log.d("TAG", "Finish DBItems")
//            withContext(Dispatchers.Main) {
//                Log.d("TAG", "Begin Main Dispatcher")
//                tvTv.text = str
//            }
//            Log.d("TAG", "Finish CoroutineScope Block")
//        }
//        Log.d("TAG", "Outside CoroutineScope Block")
    }
}
