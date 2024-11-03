package org.contribhub.api.service

class GithubCronService(
    private val githubService: GithubService,
) {
    // TODO 하루에 한번 수행하는 Cron 등록
    suspend fun fetchAndUpsertRepositoriesByTopics() {
        val topics = githubService.getTopics()
        val repositories = githubService.fetchLatestRepositoriesByTopics(topics)
        githubService.upsertRepositories(repositories)
    }
}
