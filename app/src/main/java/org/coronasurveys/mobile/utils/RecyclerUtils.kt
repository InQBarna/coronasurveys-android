package org.coronasurveys.mobile.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter

object RecyclerUtils {

    fun setAdapter(recyclerView: RecyclerView, adapter: GroupAdapter<*>,
                   horizontal: Boolean = false,
                   itemMargin: Int = 0, columns: Int = 1,
                   fixedSize: Boolean = true
    ) {
        val layoutManager =
            if (columns == 1)
                LinearLayoutManager(
                    recyclerView.context,
                    if (horizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL,
                    false
                )
            else
                GridLayoutManager(recyclerView.context, columns)

        recyclerView.apply {
            setHasFixedSize(fixedSize)
            if (itemDecorationCount == 0) {
                val dividerItemDecoration =
                    MarginItemDecoration(
                        itemMargin
                    )
                this.addItemDecoration(dividerItemDecoration)
            }
            this.layoutManager = layoutManager
            this.adapter = adapter
        }

    }


}