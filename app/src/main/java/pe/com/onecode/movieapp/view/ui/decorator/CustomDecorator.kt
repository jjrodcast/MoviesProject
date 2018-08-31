/*
 *
 *  * Created by Jorge Rodríguez on 30/08/18 08:03 PM
 *  * Copyright (c) 2018. All rights reserved.
 *  *
 *  * Last modified 30/08/18 02:26 AM
 *
 */

package pe.com.onecode.movieapp.view.ui.decorator

import android.content.Context
import android.graphics.Rect
import android.support.annotation.IntegerRes
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.View

class CustomDecorator(val context: Context, @IntegerRes val offset: Int) : RecyclerView.ItemDecoration() {

    private var mItemOffset: Int

    init {
        val itemOffsetDp = context.resources.getInteger(offset)
        mItemOffset = convertDpToPixel(itemOffsetDp, context.resources.displayMetrics)
    }

    private fun convertDpToPixel(offsetDpValue: Int, metrics: DisplayMetrics): Int {
        return offsetDpValue * (metrics.densityDpi / 160)
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect?.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }


}