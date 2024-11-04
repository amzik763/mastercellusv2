package com.amzi.mastercellusv2.models

data class GetFolderRes(
    val files: List<Any>,
    val folders: List<Folder>
)