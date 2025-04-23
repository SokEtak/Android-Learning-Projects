package com.example.todo_app

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {
    var FILENAME  = "listinfo.dat"

    fun writeDate(item:ArrayList<String>,context: Context){
         var fos : FileOutputStream = context.openFileOutput(FILENAME,Context.MODE_PRIVATE)
         var oas = ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }
    fun readData(context: Context):ArrayList<String>{
        var itemList  = ArrayList<String>()

        try {
            var fis : FileInputStream = context.openFileInput(FILENAME)
            var ois = ObjectInputStream(fis)

            itemList = ois.readObject() as ArrayList<String>

        }catch (ex:FileNotFoundException){
            itemList = java.util.ArrayList<String>()
        }

        return itemList
    }
}