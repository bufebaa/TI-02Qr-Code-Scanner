package com.example.barcodescanner.feature.common.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.barcodescanner.R
import com.example.barcodescanner.model.SearchEngine

class ChooseSearchEngineDialogFragment : DialogFragment() {

    companion object {
        private val ITEMS = arrayOf(
            SearchEngine.BING,
            SearchEngine.DUCK_DUCK_GO,
            SearchEngine.GOOGLE,
            SearchEngine.QWANT,
            SearchEngine.STARTPAGE,
            SearchEngine.YAHOO,
            SearchEngine.YANDEX
        )
    }

    interface Listener {
        fun onSearchEngineSelected(searchEngine: SearchEngine)
    }

}