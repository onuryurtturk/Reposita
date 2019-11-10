package com.onuryurtturk.reposita.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.onuryurtturk.reposita.BR
import com.onuryurtturk.reposita.R
import com.onuryurtturk.reposita.persistence.FavEntity
import com.onuryurtturk.reposita.databinding.ActivityRepositoryDetailBinding
import com.onuryurtturk.reposita.di.InjectionUtils
import com.onuryurtturk.reposita.model.Repo
import com.onuryurtturk.reposita.viewmodel.RepoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repository_detail.*


const val EXTRA_SELECTED_POSITION = "EXTRA_SELECTED_POSITION"

class RepoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepositoryDetailBinding
    private lateinit var viewModel: RepoViewModel
    private lateinit var repoItem: Repo

    /**
     * Create Detail Activity Intent with selected position parameter
     */
    companion object {
        fun getStartIntent(context: Context, position: String): Intent {
            return Intent(context, RepoDetailActivity::class.java).putExtra(EXTRA_SELECTED_POSITION, position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        initBinding()
    }


    /**
     * Binding and actionbar init
     */
    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository_detail)
        binding.lifecycleOwner = this
        val factory = InjectionUtils.provideRepoViewModelFactory()
        viewModel  = ViewModelProviders.of(this, factory).get(RepoViewModel::class.java)
        repoItem = viewModel.getRepoByIndex(Integer.valueOf(intent.getStringExtra(EXTRA_SELECTED_POSITION)))!!
        binding.setVariable(BR.RepoDetailItem,repoItem)
        binding.executePendingBindings()
        Picasso.get().load(repoItem.owner.avatar_url).into(img_user)
        supportActionBar!!.title = repoItem.name
    }

    /**
     * enable back button functionality
     */
    private fun setupUI(){
        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.back)
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Create fav menu
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail_menu, menu)
        if(repoItem.faved){
            menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_faved)
        }else{
            menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_unfaved)
        }
        return true
    }

    /**
     * Handle fav menu click
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_fav -> {
            doFavOp(item)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    /**
     * if repo not added to favlist set icon to "ic_unfaved"
     * else set to "ic_faved"
     */
    fun doFavOp(menuItem: MenuItem){
        if(!repoItem.faved){
            repoItem.faved = true
            viewModel.favRepo(FavEntity(null,repoItem.owner.id,repoItem.id))
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_faved)

        }else{
            repoItem.faved = false
            viewModel.unFavRepo(repoItem.owner.id,repoItem.id)
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_unfaved)
        }

    }
}
