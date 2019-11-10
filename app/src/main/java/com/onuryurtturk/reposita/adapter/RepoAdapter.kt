package com.onuryurtturk.reposita.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onuryurtturk.reposita.databinding.ItemRepoBinding
import com.onuryurtturk.reposita.model.Repo
import com.onuryurtturk.reposita.viewmodel.RepoViewModel

class RepoAdapter(private val repoListViewModel: RepoViewModel) : RecyclerView.Adapter<RepoViewHolder>() {

    /**
     * Repo Recyclerview adapter
     */
    var repoList: List<Repo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(inflater,parent,false)
        val holder = RepoViewHolder(binding,repoListViewModel)
        holder.itemView.setOnClickListener {
            if(holder.adapterPosition!= RecyclerView.NO_POSITION){
                repoListViewModel.onRepoItemClick(holder.adapterPosition)
            }
        }
        return holder
    }
    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.init(repoList[position])
    }

    /**
     * When observer catch changes on items, this function calls for updating recyclerview
     */
    fun updateRepoItems(repoList: List<Repo>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}