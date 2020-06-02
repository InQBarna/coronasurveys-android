package org.coronasurveys.mobile.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Generic margin item decoration, just pass your desired margin dp and it will be applied to all sides
 */
class MarginItemDecoration(
    private val spaceHeight: Int,
    private val paddingTop: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State) {
        val spaceInDp = spaceHeight.toPx(view.context)
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0 && paddingTop) {
                top = spaceInDp
            }
            left =  spaceInDp
            right = spaceInDp
            bottom = spaceInDp
        }
    }
}