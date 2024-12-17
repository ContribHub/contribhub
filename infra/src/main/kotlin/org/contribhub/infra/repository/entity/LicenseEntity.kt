package org.contribhub.infra.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.contribhub.core.entity.License
import org.contribhub.infra.repository.entity.common.BaseTimeEntity

@Entity
@Table(
    name = "licenses",
    uniqueConstraints = [
        UniqueConstraint(
            name = "licen_key_unique",
            columnNames = ["licen_key"],
        ),
    ],
)
class LicenseEntity(
    licenKey: String,
    licenName: String,
    licenUrl: String,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "licen_seq")
    val licenSeq: Long? = null

    @Column(name = "licen_key", columnDefinition = "VARCHAR(50)")
    var licenKey = licenKey
        protected set

    @Column(name = "licen_name", columnDefinition = "VARCHAR(200)")
    var licenName = licenName
        protected set

    @Column(name = "licen_url", columnDefinition = "VARCHAR(2083)")
    var licenUrl = licenUrl
        protected set

    // 레포지토리의 연관관계는 필요할때 설정.

    fun toDomain(): License =
        License(name = licenName)
}
