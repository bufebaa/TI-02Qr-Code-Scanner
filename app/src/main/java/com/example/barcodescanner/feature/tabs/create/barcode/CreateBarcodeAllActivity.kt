package com.example.barcodescanner.feature.tabs.create.barcode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.barcodescanner.R
import com.example.barcodescanner.extension.applySystemWindowInsets
import com.example.barcodescanner.feature.BaseActivity
import com.example.barcodescanner.feature.tabs.create.CreateBarcodeActivity
import com.google.zxing.BarcodeFormat
import kotlinx.android.synthetic.main.activity_create_barcode_all.*

class CreateBarcodeAllActivity : BaseActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, CreateBarcodeAllActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_barcode_all)
        supportEdgeToEdge()
        handleToolbarBackClicked()
    }

    private fun supportEdgeToEdge() {
        root_view.applySystemWindowInsets(applyTop = true, applyBottom = true)
    }

    private fun handleToolbarBackClicked() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}