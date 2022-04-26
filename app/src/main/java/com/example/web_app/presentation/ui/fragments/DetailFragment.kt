package com.example.web_app.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.web_app.R
import com.example.web_app.databinding.FragmentDetatlBinding
import com.example.web_app.domain.entity.Brawler
import com.example.web_app.presentation.presenter.DetailFragmentPresenter
import com.example.web_app.presentation.presenter.ListFragmentPresenter
import com.example.web_app.presentation.view.DetailFragmentView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import retrofit2.HttpException
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : MvpAppCompatFragment(R.layout.fragment_list), DetailFragmentView {
    var binding: FragmentDetatlBinding? = null
    val bundle = Bundle()

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailFragmentPresenter

    @ProvidePresenter
    fun providePresenter(): DetailFragmentPresenter = presenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetatlBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id")
        Log.e("zero",id.toString())
        id?.let { presenter.onLoadDetailInfo(it) }
    }

    override fun loadBrawlerInfo(brawler: Brawler) {
        binding?.ivBrawler?.setImageResource(brawler.imageId)
        binding?.tvBrawlerNameDetail?.text = brawler.name
        binding?.tvFirstGadget?.text = brawler.gadgets[0].name
        binding?.tvSecondGadget?.text = brawler.gadgets[1].name
        binding?.tvFirstPower?.text=brawler.starPowers[0].name
        binding?.tvSecondPower?.text=brawler.starPowers[1].name
    }

    override fun showLoading() {
        binding?.progressBarDetail?.isVisible = true
    }

    override fun showError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                view?.let {
                    Snackbar.make(
                        it,
                        "ERROR",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            is NullPointerException -> {
                view?.let {
                    Snackbar.make(
                        it,
                        "ERROR",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            else -> {
                view?.let {
                    Snackbar.make(
                        it,
                        "ERROR",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun hideLoading() {
        binding?.progressBarDetail?.isVisible = false
    }
}