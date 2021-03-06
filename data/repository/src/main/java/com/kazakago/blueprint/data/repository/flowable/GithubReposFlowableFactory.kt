package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubService
import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.cache.hierarchy.GithubReposStateManager
import com.kazakago.blueprint.data.mapper.response.github.GithubRepoResponseMapper
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.storeflowable.FetchingResult
import com.kazakago.storeflowable.pagination.PaginatingStoreFlowableFactory
import java.time.Duration
import java.time.LocalDateTime

internal class GithubReposFlowableFactory(
    private val githubService: GithubService,
    private val githubCache: GithubCache,
    private val githubRepoResponseMapper: GithubRepoResponseMapper,
    githubOrgName: GithubOrgName,
) : PaginatingStoreFlowableFactory<String, List<GithubRepoEntity>> {

    companion object {
        private val EXPIRED_DURATION = Duration.ofMinutes(30)
        private const val PER_PAGE = 20
    }

    override val flowableDataStateManager = GithubReposStateManager

    override val key = githubOrgName.value

    override suspend fun loadDataFromCache(): List<GithubRepoEntity>? {
        return githubCache.reposCache[key]
    }

    override suspend fun saveDataToCache(newData: List<GithubRepoEntity>?) {
        githubCache.reposCache[key] = newData
        githubCache.reposCacheCreatedAt[key] = LocalDateTime.now()
    }

    override suspend fun saveAdditionalDataToCache(cachedData: List<GithubRepoEntity>?, newData: List<GithubRepoEntity>) {
        githubCache.reposCache[key] = (cachedData ?: emptyList()) + newData
    }

    override suspend fun fetchDataFromOrigin(): FetchingResult<List<GithubRepoEntity>> {
        val response = githubService.getRepos(key, 1, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return FetchingResult(data = data, noMoreAdditionalData = data.isEmpty())
    }

    override suspend fun fetchAdditionalDataFromOrigin(cachedData: List<GithubRepoEntity>?): FetchingResult<List<GithubRepoEntity>> {
        val page = ((cachedData?.size ?: 0) / PER_PAGE + 1)
        val response = githubService.getRepos(key, page, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return FetchingResult(data = data, noMoreAdditionalData = data.isEmpty())
    }

    override suspend fun needRefresh(cachedData: List<GithubRepoEntity>): Boolean {
        val createdAt = githubCache.reposCacheCreatedAt[key]
        return if (createdAt != null) {
            val expiredAt = createdAt + EXPIRED_DURATION
            expiredAt < LocalDateTime.now()
        } else {
            true
        }
    }
}
