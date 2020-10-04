package com.mcba.comandacentralv2.Utils

import android.widget.ImageView
import com.mcba.comandacentralv2.R

fun ImageView.setImageResourceFromId(imageId: Int?) {

    when (imageId) {
        1 -> {
            this.setImageResource(R.drawable.ic_bananas)
        }
        1001 -> {
            this.setImageResource(R.drawable.ic_bananas)
        }
        1002 -> {
            this.setImageResource(R.drawable.ic_bananas)
        }
        2 -> {
            this.setImageResource(R.drawable.ic_manzana)
        }
        3 -> {
            this.setImageResource(R.drawable.ic_pera)
        }
        2001 -> {
            this.setImageResource(R.drawable.ic_manzana)
        }
        2002 -> {
            this.setImageResource(R.drawable.ic_manzana_granny)
        }
        3001 -> {
            this.setImageResource(R.drawable.ic_pera)
        }
        else -> {
        }
    }
}

