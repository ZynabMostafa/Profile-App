package com.example.profileapp.screens.profileScreen.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private var _name = mutableStateOf("")
    var name = _name

    private var _age = mutableStateOf(0)
    var age = _age

    private var _jobDescription = mutableStateOf("")
    var jobDescription = _jobDescription

}