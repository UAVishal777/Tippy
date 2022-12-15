package com.example.tippy

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val  TAG="MainActivity"
private const val INITIAL_TIP_PERCENT=15

class MainActivity : AppCompatActivity() {

    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip:SeekBar
    private lateinit var tiptvPercentage:TextView
    private lateinit var tiptvamt:TextView
    private lateinit var tvTotalAmount:TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBaseAmount=findViewById((R.id.tiptvEdit))
        seekBarTip=findViewById(R.id.tiptvseek)
        tiptvPercentage=findViewById(R.id.tiptvPercentage)
        tiptvamt=findViewById(R.id.tiptvamt)
        tvTotalAmount=findViewById(R.id.tiptvtotalamt)

        seekBarTip.progress= INITIAL_TIP_PERCENT
        tiptvPercentage.text="$INITIAL_TIP_PERCENT%"

        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "OnProgressChanged $p1")
                tiptvPercentage.text = "$p1%"
                computeTipAndTotal()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        etBaseAmount.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG,"afterTextChanged $p0")
                computeTipAndTotal()
            }

        })
    }
    @SuppressLint("SetTextI18n")
    private fun computeTipAndTotal(){
        if(etBaseAmount.text.isEmpty()){
            tiptvamt.text=""
            tvTotalAmount.text=""
            return
        }
        val baseAmount =etBaseAmount.text.toString().toDouble()
        val tipPercent=seekBarTip.progress

        val tipAmount=baseAmount*tipPercent/100
        val totalAmount=baseAmount + tipAmount

        tiptvamt.text="%.2f".format(tipAmount)
        tvTotalAmount.text="%.2f".format(totalAmount)


    }
}