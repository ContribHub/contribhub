package org.contribhub.api.domain.opensourcerepository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.contribhub.api.domain.opensourcerepository.entity.common.BaseTimeEntity

@Entity
@Table(
    name = "languages",
    uniqueConstraints = [
        UniqueConstraint(
            name = "language_unique",
            columnNames = ["language"],
        ),
    ],
)
class LanguageEntity(
    language: String,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_seq")
    val languageSeq: Long? = null

    @Column(name = "language", columnDefinition = "VARCHAR(20)")
    var language = language
        protected set

    // 레포지토리의 연관관계는 필요할때 설정.
}
