package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.PostsItemBinding

class PostsAdapter(
   val postList : ArrayList<Posts>
): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

   override fun onCreateViewHolder(
   parent: ViewGroup,
   viewType: Int,
 ): PostsViewHolder {
   val binding = PostsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
  return PostsViewHolder(binding)
 }

 override fun onBindViewHolder(
  holder: PostsViewHolder,
  position: Int,
 ){
  val posts = postList[position]
  holder.adapterBinding.tvPostsId.text = posts.id.toString()
  holder.adapterBinding.tvUserId.text = posts.userId.toString()
  holder.adapterBinding.tvTitle.text = posts.title.toString()
  holder.adapterBinding.tvSubtitle.text = posts.subtitle.toString()
 }

 override fun getItemCount() = postList.size

 inner class PostsViewHolder(val adapterBinding: PostsItemBinding) :
       RecyclerView.ViewHolder(adapterBinding.root)
 }
