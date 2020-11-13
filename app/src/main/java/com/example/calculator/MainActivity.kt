package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var lastResult: TextView
    private var operand : Double = 0.0
    private var operation : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        lastResult = findViewById(R.id.lastResultTextView)
    }

    fun numberClick(view: View){
        if (view is TextView){
            var number: String = view.text.toString()
            var result: String = resultTextView.text.toString()

            if (result == "0" && number != "."){
                result = ""
            }
            if(number == "." && result.contains(".")){
                number = ""
            }
            resultTextView.text = result + number
        }
    }

    fun operationClick(view: View){
        if(view is TextView){
            if(!TextUtils.isEmpty(resultTextView.text)){
                operand = resultTextView.text.toString().toDouble()
            }

            operation = view.text.toString()

            lastResult.text = resultTextView.text
            resultTextView.text = ""
        }
    }

    fun equalsClick(view: View){
        val secOperandText : String = resultTextView.text.toString()
        var secOperand : Double = 0.0
        if(!TextUtils.isEmpty(secOperandText)){
            secOperand = secOperandText.toDouble()
        }

        when(operation){
            "+" -> resultTextView.text = (operand + secOperand).toString()
            "-" -> resultTextView.text = (operand - secOperand).toString()
            "*" -> resultTextView.text = (operand * secOperand).toString()
            "/" -> resultTextView.text = (operand / secOperand).toString()
            "%" -> resultTextView.text = (operand / 100 * secOperand).toString()
            "^" -> resultTextView.text = (operand.pow(secOperand)).toString()
        }
        lastResult.text = ""
        if(resultTextView.text.takeLast(2) == ".0"){
            resultTextView.text = resultTextView.text.substring(0, resultTextView.text.length - 2)
        }
    }

    fun clear(view: View){
        if(view is TextView){
            operand = 0.0
            lastResult.text = ""
            resultTextView.text = ""
        }
    }

    fun del(view: View){
        if(view is TextView) {
            if (!TextUtils.isEmpty(resultTextView.text)) {
                resultTextView.text = resultTextView.text.substring(0, resultTextView.text.length - 1)
            }
        }
    }
}