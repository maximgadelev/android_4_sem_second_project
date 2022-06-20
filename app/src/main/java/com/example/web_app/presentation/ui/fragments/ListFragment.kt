package com.example.web_app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.web_app.R
import com.example.web_app.databinding.FragmentListBinding
import com.example.web_app.domain.entity.Brawler
import com.example.web_app.presentation.presenter.ListFragmentPresenter
import com.example.web_app.presentation.ui.BrawlerAdapter
import com.example.web_app.presentation.view.ListFragmentView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import retrofit2.HttpException
import java.lang.NumberFormatException
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : ListFragmentView, MvpAppCompatFragment(R.layout.fragment_list) {
    @Inject
    @InjectPresenter
    lateinit var presenter: ListFragmentPresenter

    @ProvidePresenter
    fun providePresenter(): ListFragmentPresenter = presenter
    var binding: FragmentListBinding? = null
    val bundle = Bundle()
    private var mRecyclerView: RecyclerView? = null
    private var weatherAdapter: BrawlerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.rv_weather_list)
        mRecyclerView?.layoutManager = GridLayoutManager(context, 1)
        presenter.onLoadList()
        initSearch()
    }

    private fun initSearch() {
        binding?.searchView?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                openDetailsScreen(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
    }

    override fun showList(brawlers: List<Brawler>) {
        weatherAdapter = BrawlerAdapter(
            brawlers,
        ) {
            bundle.putInt("id", it)
            findNavController().navigate(
                R.id.action_listFragment_to_detailFragment,
                bundle
            )
        }
        mRecyclerView?.adapter = weatherAdapter
    }

    override fun showLoading() {
        binding?.progressBarList?.isVisible = true
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
        binding?.progressBarList?.isVisible = false
    }

    override fun openDetailsScreen(id:String) {
        try {
            bundle.putInt("id", Integer.parseInt(id))
            findNavController().navigate(
                R.id.action_listFragment_to_detailFragment,
                bundle
            )
        }catch (ex:NumberFormatException){
            view?.let {
                Snackbar.make(
                    it,
                    "Бравлер не найден или вы ввели некорректный айди",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}