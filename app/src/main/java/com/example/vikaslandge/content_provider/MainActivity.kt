package com.example.vikaslandge.content_provider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var resolver = contentResolver
        var cursor = resolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        var adapter = SimpleCursorAdapter(
                this,
                R.layout.indiview,
                cursor,
                arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER),
                intArrayOf(R.id.name,R.id.number),
                0
        )

        lview.adapter = adapter

        var list = mutableListOf<String>()
        while (cursor.moveToNext()){
            var index = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            list.add(cursor.getString(index))
        }

        var array_adapter = ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,list)
        et1.setAdapter(array_adapter)
        et1.threshold = 1

        search.setOnClickListener{

            var resolver = contentResolver
            var cursor  =     resolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"=?",
                    arrayOf(et1.text.toString()),
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            var adapter = SimpleCursorAdapter(this,
                    R.layout.indiview,cursor,
                    arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER),
                    intArrayOf(R.id.name,R.id.number),0)
            lview.adapter = adapter


        }


    }
}
