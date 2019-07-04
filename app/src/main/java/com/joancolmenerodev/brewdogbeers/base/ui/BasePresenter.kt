package com.joancolmenerodev.brewdogbeers.base.ui

interface BasePresenter<in T : BaseView> {
    fun attachView(view: T)
    fun detachView()
}