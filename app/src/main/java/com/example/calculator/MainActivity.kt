package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var buttonList: List<Button>
    lateinit var display: TextView

    private var num1 = ""
    private var num2 = ""
    private var op = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.tvDisplay)

        buttonList = listOf(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9),
            findViewById(R.id.btnPls),
            findViewById(R.id.btnMin),
            findViewById(R.id.btnMul),
            findViewById(R.id.btnDiv),
            findViewById(R.id.btnEql),
            findViewById(R.id.btnDec),
            findViewById(R.id.btnNeg),
            findViewById(R.id.btnClr),
            findViewById(R.id.btnDel),
        )

        for (button in buttonList)
            button.setOnClickListener(this)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("num1", num1)
        outState.putString("num2", num2)
        outState.putString("op", op)
        outState.putString("display",display.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        num1 = savedInstanceState.getString("num1","")
        num2 = savedInstanceState.getString("num2","")
        op = savedInstanceState.getString("op","")
        display.text = savedInstanceState.getString("display","")
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn0 -> setValues("0")
            R.id.btn1 -> setValues("1")
            R.id.btn2 -> setValues("2")
            R.id.btn3 -> setValues("3")
            R.id.btn4 -> setValues("4")
            R.id.btn5 -> setValues("5")
            R.id.btn6 -> setValues("6")
            R.id.btn7 -> setValues("7")
            R.id.btn8 -> setValues("8")
            R.id.btn9 -> setValues("9")
            R.id.btnClr -> clearValues()
            R.id.btnEql -> result()
            R.id.btnDec -> setDecimal()
            R.id.btnNeg -> setNegative()
            R.id.btnDel -> delete()
            R.id.btnPls -> {
                op = "+"
                display.text = "$num1 +"
            }
            R.id.btnMin -> {
                op = "-"
                display.text = "$num1 -"
            }
            R.id.btnMul -> {
                op = "*"
                display.text = "$num1 *"
            }
            R.id.btnDiv -> {
                op = "/"
                display.text = "$num1 /"
            }



        }
    }

    private fun setValues(s: String) {
        if (op.isEmpty()) {
            num1 += s
            display.text = num1
        } else {
            num2 += s
            display.text = num2
        }
    }
    private fun result() {
        val a = num1.toFloat()
        val b = num2.toFloat()

        when (op) {
            "+" -> display.text = "${a + b}"
            "-" -> display.text = "${a - b}"
            "*" -> display.text = "${a * b}"
            "/" -> {
                if(b == 0F)
                {
                    Toast.makeText(this,"Can't Divide by Zero", Toast.LENGTH_LONG).show()
                    clearValues()
                }
                display.text = "${a / b}"
            }

        }
    }

    private fun clearValues() {
        display.text = ""
        num1 = ""
        num2 = ""
        op = ""
    }

    private fun setDecimal() {
        if (op.isEmpty()) {
            if (num1.contains("."))
                return
            num1 += "."
            display.text = num1
        } else {
            if (num2.contains("."))
                return
            num2 += "."
            display.text = num2
        }
    }

    private fun setNegative() {
        if (op.isEmpty()) {
            num1 = if (num1.startsWith("-"))
                num1.substring(1)
            else
                "-$num1"
            display.text = num1
        } else {
            num2 = if (num2.startsWith("-"))
                num2.substring(1)
            else
                "-$num2"
            display.text = num2
        }
    }

    private fun delete() {
        if (op.isEmpty()) {
            num1 = num1.substring(0, num1.length - 1)
            display.text = num1
        } else {
            num2 = num2.substring(0, num2.length - 1)
            display.text = num2
        }
    }

}
