package com.coronasurveys.mobile.presentation.adapters

import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

/**
 * Created by Marc Portabella Navarro on 26/07/2019
 * This class just adds logging in onCreateViewHolder and OnBindViewHolder times,
 * and methods to get all items or groups of the adapter (as an immutable list).
 */
open class BasicAdapter: GroupAdapter<GroupieViewHolder>()  {

    init {
        this.setHasStableIds(true)
    }


    fun getGroups(): List<Group> {
        return (0 until groupCount).map {
            getGroupAtAdapterPosition(it)
        }
    }

    fun getItems(): List<Item<*>> {
        return (0 until itemCount).map {
            getItem(it)
        }
    }

    inline fun <reified T: Group> getGroupsByType(): List<T>  {
        return (0 until groupCount).mapNotNull {
            getGroupAtAdapterPosition(it) as? T
        }
    }

    inline fun <reified T: Item<*>> getItemsByType(): List<T>  {
        return (0 until itemCount).mapNotNull {
            getItem(it) as? T
        }
    }
}