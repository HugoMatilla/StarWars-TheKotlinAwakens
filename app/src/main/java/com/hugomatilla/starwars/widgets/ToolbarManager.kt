package com.hugomatilla.starwars.widgets

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.app.App
import com.hugomatilla.starwars.articles.list.ArticlesListPresenter
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


    fun initToolbarSettings(presenter: ArticlesListPresenter) {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_delete_database -> deleteDatabase(presenter)
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    private fun deleteDatabase(presenter: ArticlesListPresenter) {
        App.instance.toast("Deleting Database")
        presenter.deleteDatabase()

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