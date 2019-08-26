package com.fakhar.internalstorageexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {

    companion object{
        var FILE_NAME = "example.txt"
    }

    lateinit var editText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = et

        var saveBtn : Button = btn_save
        var loadBtn : Button = btn_load

        saveBtn.setOnClickListener(View.OnClickListener {

            var txt : String = editText.text.toString()

            var fos : FileOutputStream? = openFileOutput(FILE_NAME , Context.MODE_PRIVATE)

            fos?.write(txt.toByteArray())
            editText.text.clear()
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
            fos?.close()
        })

        loadBtn.setOnClickListener(View.OnClickListener {

            var fis : FileInputStream = openFileInput(FILE_NAME)
            var isr : InputStreamReader = InputStreamReader(fis)

            var br : BufferedReader = BufferedReader(isr)
            var sb : StringBuilder = StringBuilder()
            var txt : String = br.readLine()

            while (txt  != null)
            {
                sb.append(txt).append("\n")

                if (br.readLine() != null)
                    txt = br.readLine()
                else
                    break
            }

            editText.setText(sb.toString())
            fis.close()

        })
    }
}


