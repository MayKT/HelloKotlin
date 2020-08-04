package com.mkttestprojects.hellokotlin.ui.main

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.mkttestprojects.hellokotlin.R
import com.mkttestprojects.hellokotlin.common.BaseActivity
import com.mkttestprojects.hellokotlin.ui.main.home.HomeFragment
import com.mkttestprojects.hellokotlin.ui.main.profile.ProfileFragment
import com.mkttestprojects.hellokotlin.util.SharePreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val HOME_FRAGMENT = "home"

    private val USERS_FRAGMENT = "users"

    private val POSTS_FRAGMENT = "posts"

    private val PROFILE_FRAGMENT = "profile"

    private lateinit var fragmentToShow : Fragment

    @Inject
    lateinit var sharePreferenceHelper: SharePreferenceHelper

    lateinit var fragment : Fragment

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun setUpContents(savedInstanceState: Bundle?) {
        setupToolbar(false)
        checkTheme()
        setupBottomNav()
    }


    private fun setupBottomNav() {

        val homeItem = AHBottomNavigationItem("Home",R.drawable.ic_home_black_24dp)
        val item2 = AHBottomNavigationItem("News",R.drawable.ic_news_24)
        val item3 = AHBottomNavigationItem("Tab3",R.drawable.ic_home_black_24dp)
        val profileItem = AHBottomNavigationItem("Profile",R.drawable.ic_account_circle_24)

        bottom_navigation.addItem(homeItem)
        bottom_navigation.addItem(item2)
        bottom_navigation.addItem(item3)
        bottom_navigation.addItem(profileItem)

        bottom_navigation.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))

        bottom_navigation.setOnTabSelectedListener(AHBottomNavigation.OnTabSelectedListener { position, wasSelected ->
            when(position){
                0 -> {
                    displayView(HOME_FRAGMENT)
                    return@OnTabSelectedListener true
                }
                1 -> {
                    displayView(USERS_FRAGMENT)
                    return@OnTabSelectedListener true
                }
                2 -> {
                    displayView(POSTS_FRAGMENT)
                    return@OnTabSelectedListener true
                }
                3 -> {
                    displayView(PROFILE_FRAGMENT)
                    return@OnTabSelectedListener true
                }
                else -> return@OnTabSelectedListener true
            }
        })
        bottom_navigation.currentItem = 0
    }

    fun displayView(frag : String) {

        when(frag) {
            HOME_FRAGMENT -> {
                fragmentToShow = HomeFragment()
            }

            USERS_FRAGMENT -> {
            }

            POSTS_FRAGMENT -> {
            }

            PROFILE_FRAGMENT -> {
                fragmentToShow = ProfileFragment()
            }

        }

        if (this::fragmentToShow.isInitialized) {

            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container,fragmentToShow).commit()

        }

    }

    private fun checkTheme() {
        when(sharePreferenceHelper.darkStatus){
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            1 ->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            2 ->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

}