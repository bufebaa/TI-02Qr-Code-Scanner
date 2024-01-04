package com.example.barcodescanner.feature.tabs.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.barcodescanner.BuildConfig
import com.example.barcodescanner.R
import com.example.barcodescanner.di.barcodeDatabase
import com.example.barcodescanner.extension.applySystemWindowInsets
import com.example.barcodescanner.extension.packageManager
import com.example.barcodescanner.extension.showError
import com.example.barcodescanner.feature.common.dialog.DeleteConfirmationDialogFragment
import com.example.barcodescanner.feature.tabs.settings.camera.ChooseCameraActivity
import com.example.barcodescanner.feature.tabs.settings.theme.ChooseThemeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment(), DeleteConfirmationDialogFragment.Listener {
    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supportEdgeToEdge()
    }

    override fun onResume() {
        super.onResume()
        handleButtonClicks()
        showAppVersion()
    }

    override fun onDeleteConfirmed() {
        clearHistory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }

    fun supportEdgeToEdge() {
        app_bar_layout.applySystemWindowInsets(applyTop = true)
    }


    private fun handleButtonClicks() {
        button_choose_theme.setOnClickListener { ChooseThemeActivity.start(requireActivity()) }
        button_choose_camera.setOnClickListener { ChooseCameraActivity.start(requireActivity()) }
        button_clear_history.setOnClickListener { showDeleteHistoryConfirmationDialog() }
    }

    private fun clearHistory() {
        button_clear_history.isEnabled = false

        barcodeDatabase.deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    button_clear_history.isEnabled = true
                },
                { error ->
                    button_clear_history.isEnabled = true
                    showError(error)
                }
            )
            .addTo(disposable)
    }

    private fun showDeleteHistoryConfirmationDialog() {
        val dialog = DeleteConfirmationDialogFragment.newInstance(R.string.dialog_delete_clear_history_message)
        dialog.show(childFragmentManager, "")
    }

    private fun showAppInMarket() {
        val uri = Uri.parse("market://details?id=" + requireContext().packageName)
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun showSourceCode() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/wewewe718/QrAndBarcodeScanner"))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun showAppVersion() {
        button_app_version.hint = BuildConfig.VERSION_NAME
    }
}