package com.example.drevodavec

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val treeNames = listOf(
        "dub",
        "buk",
        "lip",
        "jav",
        "jas",
        "jed",
        "smr",
        "bor"
    )

    private val sizeNumbers = generateSequence(10) { it + 4 } // `it` is the previous element
    private val treeSizes = sizeNumbers.take(12).toList().map { it.toString() }.toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tableLayoutTree = findViewById<TableLayout>(R.id.TableLayoutTree)
        var treeBtnCnt = 0
        for (i in 0..tableLayoutTree.childCount) {
            val child = tableLayoutTree.getChildAt(i)
            if (child is TableRow) {
                treeBtnCnt += child.childCount
            }
        }

        val buttons: Array<Button?> = arrayOfNulls<Button>(treeBtnCnt)
        buttons[0] = findViewById(R.id.button)
        buttons[1] = findViewById(R.id.button2)
        buttons[2] = findViewById(R.id.button3)
        buttons[3] = findViewById(R.id.button4)
        buttons[4] = findViewById(R.id.button5)
        buttons[5] = findViewById(R.id.button6)
        buttons[6] = findViewById(R.id.button7)
        buttons[7] = findViewById(R.id.button8)

        treeNames.mapIndexed { index, s -> buttons[index]?.text = s}
        for(button in buttons) {
            button?.setOnClickListener {
                if (it is Button) {
                    val textView = findViewById<TextView>(R.id.TextViewTreeSelection)
                    textView.text = it.text
                }
            }
        }
        val textView = findViewById<TextView>(R.id.TextViewTreeSelection)
        textView.text = buttons[0]?.text ?: treeNames[0]


        val tableLayoutTreeSizes = findViewById<TableLayout>(R.id.TableLayoutTreeSizes)
        var treeSizesBtnCnt = 0
        for (i in 0..tableLayoutTreeSizes.childCount) {
            val child = tableLayoutTreeSizes.getChildAt(i)
            if (child is TableRow) {
                treeSizesBtnCnt+= child.childCount
            }
        }
        val buttonsSizes: Array<Button?> = arrayOfNulls<Button>(treeSizesBtnCnt)
        buttonsSizes[0] = findViewById(R.id.button_size_00)
        buttonsSizes[1] = findViewById(R.id.button_size_02)
        buttonsSizes[2] = findViewById(R.id.button_size_03)
        buttonsSizes[3] = findViewById(R.id.button_size_04)
        buttonsSizes[4] = findViewById(R.id.button_size_05)
        buttonsSizes[5] = findViewById(R.id.button_size_06)
        buttonsSizes[6] = findViewById(R.id.button_size_07)
        buttonsSizes[7] = findViewById(R.id.button_size_08)
        buttonsSizes[8] = findViewById(R.id.button_size_09)
        buttonsSizes[9] = findViewById(R.id.button_size_10)
        buttonsSizes[10] = findViewById(R.id.button_size_11)
        buttonsSizes[11] = findViewById(R.id.button_size_12)

        treeSizes.mapIndexed { index, s -> buttonsSizes[index]?.text = s}
        for(button in buttonsSizes) {
            button?.setOnClickListener {
                if (it is Button) {
                    val editTextTreeSize = findViewById<TextView>(R.id.EditTextTreeSize)
                    editTextTreeSize.text = it.text
                }
            }
        }

        val buttonSync = findViewById<Button>(R.id.button_save)
        buttonSync.setOnClickListener {
            val dir = getExternalFilesDir(this)
            val file = File(dir.toString(), "example.txt")
           try {
                val treeName = findViewById<TextView>(R.id.TextViewTreeSelection)
                val treeSize = findViewById<EditText>(R.id.EditTextTreeSize)
                val textOut = "${treeName.text};${treeSize.text}\n"
                file.appendText(textOut)
                Toast.makeText(applicationContext,"${textOut.trim()} pridane",Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                println("Text write Exception $e")
                val alertDialog = AlertDialog.Builder(this@MainActivity).create()
                alertDialog.setTitle("Chyba pri zapise")
                alertDialog.setMessage(e.message)
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK"
                ) { dialog, _ -> dialog.dismiss() }
                alertDialog.show()
            }
        }

    }

    private fun getExternalFilesDir(context: Context): File? {
        val dir1 = context.getExternalFilesDir(null as String?)
        println("FML: ${dir1?.absolutePath}")
        dir1 ?: context.filesDir
        return dir1
    }

}