package com.example.dogsapp.dogs.ui

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.dogsapp.R
import com.example.dogsapp.databinding.BreedsListItemBinding


class BreedsItemDecoration(
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val padding = 16
        val position = parent.getChildAdapterPosition(view)
        outRect.apply {
            if (position == 0){
                outRect.top = padding
            }
            outRect.bottom = padding
        }
    }
}