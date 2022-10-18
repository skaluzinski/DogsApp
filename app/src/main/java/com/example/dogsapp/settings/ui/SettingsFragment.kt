package com.example.dogsapp.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dogsapp.databinding.SettingsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private val quoteViewModel: SettingsViewModel by viewModels()
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsFragment = SettingsFragmentBinding.inflate(inflater, container, false)
        _binding = settingsFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deleteDatabaseCard.setOnClickListener {
            deleteDatabaseDialog()
        }
        binding.changeAppThemeCard.setOnClickListener {
            showChangeThemeDialog()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showChangeThemeDialog() {
        val builder = AlertDialog.Builder(requireContext())
        var checkedItem = -1
        val themes = arrayOf("System Default", "Light", "Dark")
        builder.setTitle("Choose your app theme.")
        builder.setSingleChoiceItems(themes, checkedItem) { dialog, which ->
            checkedItem = which
            val newTheme = when (checkedItem) {
                0 -> AppThemes.SYSTEM
                1 -> AppThemes.DAY
                2 -> AppThemes.NIGHT
                else -> AppThemes.DAY
            }
            setAppTheme(newTheme)
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteDatabaseDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Do you really want to delete locally saved quotes?")
        builder.setPositiveButton(
            "Yes",
            { _, _ ->
                quoteViewModel.deleteAllQuotes(toastFunction = ::showToast)
            })
        builder.setNegativeButton("No", { _, _ -> })
        builder.create().show()
    }

    private fun setAppTheme(theme: AppThemes) {
        when (theme) {
            AppThemes.DAY -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppThemes.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            AppThemes.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        //val editor = SharedPreferences("Themes",Fragment)
    }
}