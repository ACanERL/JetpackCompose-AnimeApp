package com.example.anime_app_jetpackcompose.models.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.anime_app_jetpackcompose.models.api.ApiService
import com.example.anime_app_jetpackcompose.models.data.character.CharacterData

class CharacterPagingSource(private val apiService: ApiService): PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getTopCharacters(page = page)

            if(response.data != null) {
                LoadResult.Page(
                    data = response.data,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (response.data.isEmpty()) null else page.plus(1),
                )
            } else {
                LoadResult.Page(
                    data = response.data ?: emptyList(),
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (response.pagination?.has_next_page == null) null else page + 1
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}