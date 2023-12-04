package com.example.foodfit.utils

sealed class UIEvents{
    data class ShowSnackBar(val msg:String): UIEvents()
}
