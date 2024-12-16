package org.contribhub.infra.repositories

import org.contribhub.infra.config.InfraConfig
import org.contribhub.infra.config.TestConfig
import org.contribhub.infra.repository.entity.GithubRepositoryEntity
import org.contribhub.infra.repository.repositories.RepositoryEntityRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import java.time.Instant

// TODO : 메모리 디비 설정을 안해서 테스트 시, 디비가 필요한 테스트 불가. 디비접근 설정 공유를 위해 테스트 주석처리
@SpringBootTest(classes = [TestConfig::class, InfraConfig::class])
@ActiveProfiles("test")
class CustomRepositoryEntityGithubRepositoryImpleTest(
    @Autowired private val repositoryEntityRepository: RepositoryEntityRepository,
) {
    @Test
    @DisplayName("레포지토리 upsert 기본 기능 테스트 - insert")
    @Rollback(true)
    fun basicUpsertTest() {
        // given
        var repoList: MutableList<GithubRepositoryEntity> = mutableListOf()

        for (i: Int in 1..3) {
            repoList.add(
                GithubRepositoryEntity(
                    repoId = i.toLong(),
                    repoName = "repo$i",
                    repoFullName = "repo$i - test",
                    repoDescription = "repo$i - description",
                    ownerId = (i + 5).toLong(),
                    ownerName = "testOwner$i",
                    forkCount = i + 20,
                    openIssueCount = i + 21,
                    mainUrl = "mainUrl$i",
                    starCount = i + 100,
                    createdAt = Instant.now(),
                    updatedAt = Instant.now(),
                    licenSeq = null,
                    languageSeq = null,
                ),
            )
        }

        // when
        repositoryEntityRepository.upsertRepositories(repoList)

        // then
        assert(repositoryEntityRepository.findAll().isNotEmpty())
        assertEquals(repositoryEntityRepository.findAll().size, 3)

        println(repositoryEntityRepository.findAll())
    }
}
