package org.contribhub.api.domain.opensourcerepository.repository.repositories

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.api.domain.opensourcerepository.dto.response.RepositoryListResponse
import org.contribhub.api.domain.opensourcerepository.entity.LanguageEntity
import org.contribhub.api.domain.opensourcerepository.entity.LicenseEntity
import org.contribhub.api.domain.opensourcerepository.entity.RepositoryEntity
import org.springframework.data.domain.Pageable

class CustomRepositoryEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomRepositoryEntityRepository {
    override fun findRepositoryListPage(
        lastId: Long,
        pageable: Pageable,
    ): List<RepositoryListResponse> {
        /**
         * TODO : 일반join을 사용하면 조인이 중복되어 두번 발생하는 문제가 발생,
         *        fetch join을 쓰는게 맞지만, 현재의 엔티티 구조에서,Missing constructor for type 이 발생,
         *        우선 일반 join으로 결과 반환이 가능하도록 하고, 추후 확인해서 fetch join을 사용하도록 변경 예정.
         */
        return jpqlExecutor.findPage(pageable) {
            selectNew<RepositoryListResponse>(
                path(RepositoryEntity::repoSeq).`as`(expression(Long::class, "repoId")),
                path(RepositoryEntity::repoName),
                path(RepositoryEntity::ownerId).`as`(expression(String::class, "repoOwnerId")),
                path(RepositoryEntity::ownerName).`as`(expression(String::class, "repoOwnerName")),
                path(RepositoryEntity::openIssueCount),
                path(RepositoryEntity::mainUrl).`as`(expression(String::class, "repoUrl")),
                path(RepositoryEntity::viewCount),
                path(RepositoryEntity::starCount),
                path(RepositoryEntity::licenseEntity).path(LicenseEntity::licenName).`as`(expression(String::class, "licenseName")),
                path(RepositoryEntity::languageEntity).path(LanguageEntity::language).`as`(expression(String::class, "mainLanguage")),
                path(RepositoryEntity::forkCount),
            ).from(
                entity(RepositoryEntity::class),
//                leftFetchJoin(RepositoryEntity::languageEntity),
//                leftFetchJoin(RepositoryEntity::licenseEntity)
                leftJoin(
                    LanguageEntity::class,
                ).on(path(LanguageEntity::languageSeq).eq(path(RepositoryEntity::languageEntity).path(LanguageEntity::languageSeq))),
                leftJoin(
                    LicenseEntity::class,
                ).on(path(LicenseEntity::licenSeq).eq(path(RepositoryEntity::licenseEntity).path(LicenseEntity::licenSeq))),
            ).where(
                path(RepositoryEntity::repoSeq).gt(lastId),
            ).orderBy(
                path(RepositoryEntity::repoSeq).asc(),
            )
        }.filterNotNull()
    }
}
