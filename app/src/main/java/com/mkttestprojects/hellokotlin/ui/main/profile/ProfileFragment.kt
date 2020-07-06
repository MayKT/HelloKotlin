package com.mkttestprojects.hellokotlin.ui.main.profile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.mkttestprojects.hellokotlin.R
import com.mkttestprojects.hellokotlin.common.BaseFragment
import com.mkttestprojects.hellokotlin.util.SharePreferenceHelper
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var sharePreferenceHelper: SharePreferenceHelper

    override fun getLayoutResource(): Int {
        return R.layout.fragment_profile
    }
    override fun setUpContents(savedInstanceState: Bundle?) {
        cv_change_theme.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.cv_change_theme -> {
                chooseThemeDialog()
            }
        }
    }

    private fun chooseThemeDialog() {
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle(getString(R.string.choose_theme_text))
        val styles = arrayOf("Light","Dark","System default")
        val checkedItem = sharePreferenceHelper.darkStatus

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    sharePreferenceHelper.darkStatus = 0
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    sharePreferenceHelper.darkStatus = 1
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    sharePreferenceHelper.darkStatus = 2
                    dialog.dismiss()
                }

            }
        }
        val dialog = builder.create()
        dialog.show()
    }

}