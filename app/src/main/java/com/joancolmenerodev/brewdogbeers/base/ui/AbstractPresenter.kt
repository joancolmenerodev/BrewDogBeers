package com.joancolmenerodev.brewdogbeers.base.ui

abstract class AbstractPresenter<T : BaseView> : BasePresenter<T> {
    var view: T? = null

    protected open fun onViewAttached(){}
    protected open fun onViewDetached(){}
    override fun attachView(view: T) {
        this.view = view
        onViewAttached()
    }

    override fun detachView() {
        this.view = null
        onViewDetached()
    }
}