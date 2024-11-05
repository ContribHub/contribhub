package org.contribhub.api.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class GithubCronService(
    private val githubService: GithubService,
) {
    // 새벽 1시에 수행하는 Cron Job
    // TODO Replica 추가 시 쿠버네티스 CronJob으로 변경 가능
    @Scheduled(cron = "0 */1 * * * ?")
    suspend fun fetchAndUpsertRepositoriesByTopics() {
        val topics = githubService.getTopics()
        val repositories = githubService.fetchLatestRepositoriesByTopics(topics)
        githubService.upsertRepositories(repositories)
    }
}
