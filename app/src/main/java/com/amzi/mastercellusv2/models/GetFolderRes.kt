package com.amzi.mastercellusv2.models

data class GetFolderRes(
    val files: List<File>,
    val folders: List<Folder>
)