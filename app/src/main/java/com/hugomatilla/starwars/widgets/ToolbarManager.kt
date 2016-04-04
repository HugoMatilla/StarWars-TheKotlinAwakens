package com.hugomatilla.starwars.widgets

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.widget.Toolbar
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.app.App
import com.hugomatilla.starwars.base.ctx
import org.jetbrains.anko.toast

/**
 * Created by hugomatilla on 04/04/16.
 */

interface ToolbarManager {
    val toolbar: android.support.v7.widget.Toolbar
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }


    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> App.instance.toast("Settings")
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = with (DrawerArrowDrawable(toolbar.ctx)) {
        progress = 1f
        this
    }
}