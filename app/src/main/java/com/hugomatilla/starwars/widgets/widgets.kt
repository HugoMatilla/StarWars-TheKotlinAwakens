package com.hugomatilla.starwars.widgets

/**
 * Created by hugomatilla on 04/04/16.
 */

interface IErrorDialog {
    fun showError(message: String)
}

interface ILoadingDialog {
    fun showLoading()
    fun hideLoading()
}
