package com.onuryurtturk.reposita.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.onuryurtturk.reposita.BR
import com.onuryurtturk.reposita.model.Repo
import com.onuryurtturk.reposita.viewmodel.RepoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoViewHolder constructor(private val dataBinding: ViewDataBinding, private val repoViewModel: RepoViewModel) : RecyclerView.ViewHolder(dataBinding.root) {

    fun init(itemData: Repo) {
        dataBinding.setVariable(BR.RepoItem, itemData)
        dataBinding.executePendingBindings()
        Picasso.get().load(itemData.owner.avatar_url).into(itemView.img_user)
    }
}