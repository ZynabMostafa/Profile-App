package com.example.profileapp.screens.homeScreen.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel(){

    private var _show = mutableStateOf(false)
    var show = _show

    private var _name = mutableStateOf("")
    var name = _name

    private var _age = mutableStateOf(0)
    var age = _age

    private var _jobDescription = mutableStateOf("")
    var jobDescription = _jobDescription


}