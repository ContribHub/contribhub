package org.contribhub.infra.repository.repositories

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.core.entity.RepositorySearchKey
import org.contribhub.infra.repository.entity.GithubRepositoryEntity
import org.contribhub.infra.repository.entity.LanguageEntity
import org.contribhub.infra.repository.entity.LicenseEntity
import org.contribhub.infra.repository.entity.RepositoryEntity
import org.springframework.data.domain.Pageable

class CustomRepositoryEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomRepositoryEntityRepository {
    override fun upsertRepositories(repositories: List<GithubRepositoryEntity>) {
        TODO("Not yet implemented")
    }

    override fun findRepositoryListPage(
        lastId: Long,
        pageable: Pageable,
        searchKey: RepositorySearchKey,
        repoInTopicList: List<Long>,
    ): List<RepositoryEntity> {
        /**
         * TODO : 일반join을 사용하면 조인이 중복되어 두번 발생하는 문제가 발생,
         *        fetch join을 쓰는게 맞지만, 현재의 엔티티 구조에서,Missing constructor for type 이 발생,
         *        우선 일반 join으로 결과 반환이 가능하도록 하고, 추후 확인해서 fetch join을 사용하도록 변경 예정.
         */
        return jpqlExecutor
            .findPage(pageable) {
                select(entity(RepositoryEntity::class))
                    .from(
                        entity(RepositoryEntity::class),
//                leftFetchJoin(RepositoryEntity::languageEntity),
//                leftFetchJoin(RepositoryEntity::licenseEntity)
//                leftJoin(
//                    LanguageEntity::class,
//                ).on(path(LanguageEntity::languageSeq).eq(path(RepositoryEntity::languageEntity).path(LanguageEntity::languageSeq))),
//                leftJoin(
//                    LicenseEntity::class,
//                ).on(path(LicenseEntity::licenSeq).eq(path(RepositoryEntity::licenseEntity).path(LicenseEntity::licenSeq))),
                    ).whereAnd(
                        // TODO 동적쿼리에 사용된 표현식은 별도로 클래스로 뺴면 좋을듯 - 키워드가 더 늘어날수도 있기 때문에.
                        path(RepositoryEntity::repoSeq).gt(lastId),
                        repoInTopicList.isNotEmpty().let {
                            if (it) path(RepositoryEntity::repoSeq).`in`(repoInTopicList) else null
                        },
                        searchKey.licenId?.let {
                            path(RepositoryEntity::licenseEntity).path(LicenseEntity::licenSeq).eq(searchKey.licenId)
                        },
                        searchKey.languageId?.let {
                            path(RepositoryEntity::languageEntity).path(LanguageEntity::languageSeq).eq(searchKey.languageId)
                        },
                        searchKey.repoName?.let {
                            path(RepositoryEntity::repoFullName).like("%$it%")
                        },
                    ).orderBy(
                        path(RepositoryEntity::repoSeq).asc(),
                    )
            }.filterNotNull()
    }

    override fun findRepositoryDetail(repoId: Long): RepositoryEntity? {
        /**
         * TODO : 단건조회시 사용하려면 renderContext, entityManager를 주입받아서 사용해야 하고, 로직이 복잡해짐
         *        우선 findAll로 조회하고, 그중 첫번째 값을 뽑는 방식으로 처리하고 추후에 공식문서에서 제공하는 방식으로 변경.
         */
        return jpqlExecutor
            .findAll {
                select(entity(RepositoryEntity::class))
                    .from(
                        entity(RepositoryEntity::class),
//                leftFetchJoin(RepositoryEntity::languageEntity),
//                leftFetchJoin(RepositoryEntity::licenseEntity)
                        leftJoin(
                            LanguageEntity::class,
                        ).on(
                            path(LanguageEntity::languageSeq).eq(
                                path(RepositoryEntity::languageEntity).path(
                                    LanguageEntity::languageSeq,
                                ),
                            ),
                        ),
                        leftJoin(
                            LicenseEntity::class,
                        ).on(
                            path(LicenseEntity::licenSeq).eq(
                                path(RepositoryEntity::licenseEntity).path(
                                    LicenseEntity::licenSeq,
                                ),
                            ),
                        ),
                    ).where(
                        path(RepositoryEntity::repoSeq).eq(repoId),
                    )
            }.firstOrNull()
    }
}
