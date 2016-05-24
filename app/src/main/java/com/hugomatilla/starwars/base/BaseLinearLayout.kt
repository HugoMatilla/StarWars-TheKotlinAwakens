package com.hugomatilla.starwars.base

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

/**
 * Created by hugomatilla on 23/05/16.
 */

abstract class BaseLinearLayout : LinearLayout {

    protected constructor(context: Context) : super(context) {
        setup(context)
    }

    protected fun setup(context: Context) {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(getLayoutResource(), this, true)

    }

    protected abstract fun getLayoutResource(): Int

    protected constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup(context)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(context)
    }

}