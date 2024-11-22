package com.amzi.mastercellusv2.models

data class GetFolderResV2(
    val files: List<File>,
    val folders: List<Folder>
)