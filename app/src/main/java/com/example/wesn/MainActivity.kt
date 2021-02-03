package com.example.wesn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*
import kotlin.collections.ArrayList


private val RANDOM: Random = Random()
private var series: LineGraphSeries<DataPoint>? = null
private var lastX = 0.0
var timestamp = ArrayList<Int>()
var MAC = ArrayList<String>()
var map: MutableMap<String, ArrayList<Int>>? = null
private lateinit var database: DatabaseReference
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val arrayAdapter: ArrayAdapter<Int> = ArrayAdapter(this, R.layout.layout1,R.id.textView,
//            timestamp
//
//        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = FirebaseDatabase.getInstance().getReference()
//        val listView: ListView = findViewById(R.id.list)
//        listView.adapter = arrayAdapter

        val childEventListenerMAC = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.getValue(String::class.java)


                if (data != null) {
                    MAC.add(data)
                }
//                arrayAdapter.notifyDataSetChanged()




            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        }
        val childEventListenerTimeStamp = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.getValue(Int::class.java)

                if (data != null) {
                    timestamp.add(data)
//                    arrayAdapter.notifyDataSetChanged()
                }



            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        }
//        MAC.clear()
        database.child("UUID").addChildEventListener(childEventListenerMAC)
//        timestamp.clear()
        database.child("Timestamp").addChildEventListener(childEventListenerTimeStamp)

        val graphView: GraphView = findViewById(R.id.graph)
        var data: Array<DataPoint> = Array<DataPoint>(MAC.size,init = {DataPoint(1.0,1.0)} )
        var i = 1
        while(i<=5)
        {
            val x: DataPoint = DataPoint(i*1.0, i-1*1.0)
            data?.set(i-1,x)
            i+=1
        }
        series = LineGraphSeries(data)
        graphView.addSeries(series)
        val viewport:Viewport  = graphView.getViewport()
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0.0);
        viewport.setMaxY(10.0);
        viewport.setScrollable(true);

    }
//    override fun onResume() {
//        super.onResume()
//        // we're going to simulate real time with thread that append data to the graph
//        Thread {
//            // we add 100 new entries
//            for (i in 0..99) {
//                runOnUiThread { addEntry() }
//
//                // sleep to slow down the add of entries
//                try {
//                    Thread.sleep(600)
//                } catch (e: InterruptedException) {
//                    // manage error ...
//                }
//            }
//        }.start()
//    }
//    private fun addEntry() {
//        // here, we choose to display max 10 points on the viewport and we scroll to end
//
//    }

}


