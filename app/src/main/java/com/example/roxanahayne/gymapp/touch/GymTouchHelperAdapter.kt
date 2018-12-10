package com.example.roxanahayne.gymapp.touch


interface GymTouchHelperAdapter {
    fun onDismissed(position: Int)
    fun onItemMoved(fromPosition: Int, toPosition: Int)
}