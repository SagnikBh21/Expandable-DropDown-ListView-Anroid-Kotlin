package com.orbitalsonic.expandabledropdownlist

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListViewAdapter: ExpandableListViewAdapter
    private lateinit var listDataGroup: MutableList<String>
    private lateinit var listDataChild: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // initializing the views
        initViews()
        // initializing the listeners
        initListeners()
        // initializing the objects
        initObjects()
        // preparing list data
        initListData()
    }

    /**
     * method to initialize the views
     */
    private fun initViews() {
        expandableListView = findViewById(R.id.expandableListView)
    }

    /**
     * method to initialize the listeners
     */
    private fun initListeners() {
        // ExpandableListView on child click listener
        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            showMessage(listDataGroup[groupPosition] + " : " + listDataChild[listDataGroup[groupPosition]]!![childPosition])
            false
        }
        // ExpandableListView Group expanded listener
        expandableListView.setOnGroupExpandListener { groupPosition ->
            showMessage(listDataGroup[groupPosition] + " " + getString(R.string.text_expanded))
        }
        // ExpandableListView Group collapsed listener
        expandableListView.setOnGroupCollapseListener { groupPosition ->
            showMessage(listDataGroup[groupPosition] + " " + getString(R.string.text_collapsed))
        }
    }

    /**
     * method to initialize the objects
     */
    private fun initObjects() {
        // initializing the list of groups
        listDataGroup = ArrayList()
        // initializing the list of child
        listDataChild = HashMap()
        // initializing the adapter object
        expandableListViewAdapter = ExpandableListViewAdapter(this, listDataGroup, listDataChild)
        // setting list adapter
        expandableListView.setAdapter(expandableListViewAdapter)
    }

    /**
     * Preparing the list data
     */
    private fun initListData() {
        // Adding group data
        listDataGroup.add(getString(R.string.cricket))
        listDataGroup.add(getString(R.string.football))
        listDataGroup.add(getString(R.string.basketball))

        // list of Cricket Team
        val cricketList: MutableList<String> = ArrayList()
        var mArray: Array<String> = resources.getStringArray(R.array.string_array_cricket)
        for (item in mArray) {
            cricketList.add(item)
        }
        // list of Football Team
        val footballList: MutableList<String> = ArrayList()
        mArray = resources.getStringArray(R.array.string_array_football)
        for (item in mArray) {
            footballList.add(item)
        }
        // list of BasketBall
        val basketballList: MutableList<String> = ArrayList()
        mArray = resources.getStringArray(R.array.string_array_basketball)
        for (item in mArray) {
            basketballList.add(item)
        }

        // Adding child data
        listDataChild[listDataGroup[0]] = cricketList
        listDataChild[listDataGroup[1]] = footballList
        listDataChild[listDataGroup[2]] = basketballList
        // notify the adapter
        expandableListViewAdapter.notifyDataSetChanged()
    }

    private fun showMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}