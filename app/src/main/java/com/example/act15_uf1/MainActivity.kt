package com.example.act15_uf1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentText = ""
    private var lastResult = 0.0
    private var operation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                currentText += (it as Button).text
                textViewResult.text = currentText
            }
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            currentText = ""
            lastResult = 0.0
            operation = null
            textViewResult.text = "0"
        }

        findViewById<Button>(R.id.buttonPlus).setOnClickListener { setOperation("+", textViewResult) }
        findViewById<Button>(R.id.buttonMinus).setOnClickListener { setOperation("-", textViewResult) }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { setOperation("*", textViewResult) }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { setOperation("/", textViewResult) }

        findViewById<Button>(R.id.buttonEquals).setOnClickListener {
            if (operation != null && currentText.isNotEmpty()) {
                val secondOperand = currentText.toDouble()
                lastResult = when (operation) {
                    "+" -> lastResult + secondOperand
                    "-" -> lastResult - secondOperand
                    "*" -> lastResult * secondOperand
                    "/" -> lastResult / secondOperand
                    else -> lastResult
                }
                textViewResult.text = lastResult.toString()
                currentText = ""
                operation = null
            }
        }
    }

    private fun setOperation(op: String, textView: TextView) {
        if (currentText.isNotEmpty()) {
            lastResult = currentText.toDouble()
            currentText = ""
            operation = op
            textView.text = op
        }
    }
}
