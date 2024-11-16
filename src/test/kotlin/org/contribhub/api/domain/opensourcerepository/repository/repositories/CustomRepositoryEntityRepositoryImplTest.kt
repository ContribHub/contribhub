package org.contribhub.api.domain.opensourcerepository.repository.repositories

// import org.contribhub.api.infra.repository.entity.RepositoryEntity
// import org.junit.jupiter.api.Assertions.assertEquals
// import org.springframework.beans.factory.annotation.Autowired
// import java.time.Instant

// TODO : 메모리 디비 설정을 안해서 테스트 시, 디비가 필요한 테스트 불가. 디비접근 설정 공유를 위해 테스트 주석처리
// @SpringBootTest
// @ActiveProfiles("test")
class CustomRepositoryEntityRepositoryImplTest(
//    @Autowired private val repositoryEntityRepository: RepositoryEntityRepository,
) {
    /*@Test
    @DisplayName("레포지토리 upsert 기본 기능 테스트 - insert")
    @Rollback(true)
    fun basicUpsertTest() {
        // given
        var repoList: MutableList<RepositoryEntity> = mutableListOf()

        for (i: Int in 1..3) {
            repoList.add(
                RepositoryEntity(
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
    }*/
}
