package com.example.barcodescanner.extension

import com.example.barcodescanner.R
import com.google.zxing.BarcodeFormat

fun BarcodeFormat.toImageId(): Int {
    return when (this) {
        BarcodeFormat.QR_CODE -> R.drawable.ic_qr_code
        BarcodeFormat.DATA_MATRIX -> R.drawable.ic_data_matrix
        BarcodeFormat.AZTEC -> R.drawable.ic_aztec
        BarcodeFormat.PDF_417 -> R.drawable.ic_pdf417
        else -> R.drawable.ic_barcode
    }
}

fun BarcodeFormat.toColorId(): Int {
    return when (this) {
        BarcodeFormat.QR_CODE -> R.color.orange1
        BarcodeFormat.DATA_MATRIX, BarcodeFormat.AZTEC, BarcodeFormat.PDF_417, BarcodeFormat.MAXICODE -> R.color.orange
        else -> R.color.purple
    }
}