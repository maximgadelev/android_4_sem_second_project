package com.example.web_app.presentation.view

import com.example.web_app.domain.entity.Brawler
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface ListFragmentView : MvpView {
    fun showList(brawlers:List<Brawler>)

    fun showLoading()

    @Skip
    fun showError(throwable: Throwable)

    fun hideLoading()
    @OneExecution

    fun openDetailsScreen(id:String)
}