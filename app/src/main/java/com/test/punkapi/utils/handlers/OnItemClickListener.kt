package com.test.punkapi.utils.handlers

interface OnItemClickListener<T> {

    fun onItemClick(position: Int, item: T)

}