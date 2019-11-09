package com.onuryurtturk.reposita.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.onuryurtturk.reposita.R
import com.onuryurtturk.reposita.adapter.RepoAdapter
import com.onuryurtturk.reposita.databinding.ActivityRepositoriesBinding
import com.onuryurtturk.reposita.di.InjectionUtils
import com.onuryurtturk.reposita.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_repositories.*

const val REQUEST_CODE = 999

class ReposActivity : AppCompatActivity() {


    private lateinit var dataBinding: ActivityRepositoriesBinding
    private lateinit var recyclerAdapter: RepoAdapter


    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            dataBinding.viewModel!!.checkForStar()
            recyclerAdapter.notifyDataSetChanged()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    private fun initBinding(){
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_repositories)
        dataBinding.lifecycleOwner = this
        val factory = InjectionUtils.provideRepoViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(RepoViewModel::class.java)
        dataBinding.viewModel = viewModel
        initAdapter()
    }

    private fun initAdapter() {
        val viewModel = dataBinding.viewModel
        if (viewModel != null) {
            recyclerAdapter = RepoAdapter(dataBinding.viewModel!!)
            val layoutManager = LinearLayoutManager(this@ReposActivity)
            recycler_repository.layoutManager = layoutManager
            recycler_repository.addItemDecoration(DividerItemDecoration(this@ReposActivity, layoutManager.orientation))
            recycler_repository.adapter = recyclerAdapter
        }
        setupObservers()
    }

    private fun setupObservers() {
        dataBinding.viewModel?.reposLive?.observe(this, Observer {
            recyclerAdapter.updateRepoItems(it)
        })

        dataBinding.viewModel?.mSelectedPosition?.observe(this, Observer{
            startActivityForResult(RepoDetailActivity.getStartIntent(this, it.toString()), REQUEST_CODE)
        })

        dataBinding.viewModel?.keyboardClose?.observe(this, Observer {
            if(it){
                val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
            }

        })


    }



}
