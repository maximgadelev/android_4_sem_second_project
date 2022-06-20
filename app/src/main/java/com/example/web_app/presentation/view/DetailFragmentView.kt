package com.example.web_app.presentation.view

import com.example.web_app.domain.entity.Brawler
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface DetailFragmentView : MvpView {
    fun loadBrawlerInfo(brawler: Brawler)
    fun showLoading()

    @Skip
    fun showError(throwable: Throwable)

    fun hideLoading()
}