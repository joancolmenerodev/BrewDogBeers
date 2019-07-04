package com.joancolmenerodev.brewdogbeers.base.ui

import io.reactivex.disposables.CompositeDisposable

abstract class AbstractPresenter<T : BaseView> : BasePresenter<T> {
    var view: T? = null
    lateinit var compositeDisposable : CompositeDisposable

    protected open fun onViewAttached(){}
    protected open fun onViewDetached(){}
    override fun attachView(view: T) {
        compositeDisposable = CompositeDisposable()
        this.view = view
        onViewAttached()
    }

    override fun detachView() {
        this.view = null
        compositeDisposable.clear()
        onViewDetached()
    }
}