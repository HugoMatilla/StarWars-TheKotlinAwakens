package com.hugomatilla.starwars.base

import com.hugomatilla.starwars.widgets.IErrorDialog
import com.hugomatilla.starwars.widgets.ILoadingDialog

/**
 * Created by hugomatilla on 04/04/16.
 */

abstract class Presenter<T : Presenter.View>(val view: T) {

    interface View : IErrorDialog, ILoadingDialog {
        fun showEmptyCase()
    }
}