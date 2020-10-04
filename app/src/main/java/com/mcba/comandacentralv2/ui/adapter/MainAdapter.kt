package com.mcba.comandacentralv2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mcba.comandacentralv2.R
import com.mcba.comandacentralv2.Utils.Utils
import com.mcba.comandacentralv2.Utils.setImageResourceFromId
import com.mcba.comandacentralv2.data.model.*
import com.mcba.comandacentralv2.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_lote.view.*
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_product.view.txt_pos_product
import kotlin.math.roundToInt


class MainAdapter(
    private val context: Context, private val itemList: MutableList<Any>,
    private val idProductType: Int?,
    private val itemClickLister: OnItemClickListener?,
    private val itemTypeClickLister: OnItemTypeClickListener?,
    private val itemLoteClickLister: OnItemLoteClickListener?
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        const val VIEW_TYPE_LIST = 1
        const val VIEW_TYPE_PRODUCT = 2
        const val VIEW_TYPE_PRODUCT_TYPE = 3
        const val VIEW_TYPE_LOTE = 4
    }

    interface OnItemClickListener {
        fun onItemSelected(product: Lote, position: Int)
        fun onItemProductSelected(drink: ProductData, position: Int)
    }
    interface OnItemTypeClickListener {
        fun onItemProductSelected(productType: ProductType, position: Int)
    }
    interface OnItemLoteClickListener {
        fun onItemProductSelected(lote: Lote, position: Int)
    }

    fun deleteItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        if (viewType == VIEW_TYPE_LIST) {
            return MainViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
            )
        }
        if (viewType == VIEW_TYPE_PRODUCT_TYPE) {
            return GridViewHolderType(
                LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
            )
        }
        if (viewType == VIEW_TYPE_LOTE) {
            return GridViewHolderLote(
                LayoutInflater.from(context).inflate(R.layout.item_lote, parent, false)
            )
        }
        else {
            return GridViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is Lote -> VIEW_TYPE_LOTE
            is ProductData -> VIEW_TYPE_PRODUCT
            is ProductType -> VIEW_TYPE_PRODUCT_TYPE
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(itemList[position] as Lote, position)
            is GridViewHolder -> holder.bind(itemList[position] as ProductData, position)
            is GridViewHolderType -> holder.bind(itemList[position] as ProductType, position)
            is GridViewHolderLote -> holder.bind(itemList[position] as Lote, position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Lote>(itemView) {
        override fun bind(item: Lote, position: Int) {
            // Glide.with(context).load(item.imagen).centerCrop().into(itemView.img_trago)
            itemView.setOnClickListener { itemClickLister?.onItemSelected(item, position) }
        }
    }

    inner class GridViewHolder(itemView: View) : BaseViewHolder<ProductData>(itemView) {
        override fun bind(item: ProductData, position: Int) {
            itemView?.txt_pos_product?.text = item?.descripcion
            itemView?.img_product?.setImageResourceFromId(item?.productId)
            itemView.setOnClickListener { itemClickLister?.onItemProductSelected(item, position) }
        }
    }

    inner class GridViewHolderType(itemView: View) : BaseViewHolder<ProductType>(itemView) {
        override fun bind(item: ProductType, position: Int) {
            itemView?.txt_pos_product?.text = item?.descripcion
            itemView?.img_product?.setImageResourceFromId(item?.idLProductType)
            itemView.setOnClickListener { itemTypeClickLister?.onItemProductSelected(item, position) }
        }
    }

    inner class GridViewHolderLote(itemView: View) : BaseViewHolder<Lote>(itemView) {
        override fun bind(item: Lote, position: Int) {
            var cantMax =350
            itemView?.txt_pos_product?.text = item?.descripcion
            itemView?.img_product_lote?.setImageResourceFromId(idProductType)
            itemView?.txt_cantidad.text = item?.cantidad.roundToInt().toString()
            Utils.setColor(itemView?.txt_cantidad, (item?.cantidad.roundToInt() *100/cantMax))

            itemView?.txt_lote_date.text = item?.fechaIngreso
            itemView.setOnClickListener { itemLoteClickLister?.onItemProductSelected(item, position) }
        }
    }
}