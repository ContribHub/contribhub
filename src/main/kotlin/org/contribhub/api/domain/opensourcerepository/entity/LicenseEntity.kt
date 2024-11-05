package org.contribhub.api.domain.opensourcerepository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.contribhub.api.domain.opensourcerepository.entity.common.BaseTimeEntity

@Entity
@Table(name = "licenses")
class LicenseEntity(
    licenKey: String,
    licenName: String,
    licenUrl: String,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "licen_seq")
    val licenSeq: Long? = null

    @Column(name = "licen_key")
    var licenKey = licenKey
        protected set

    @Column(name = "licen_name")
    var licenName = licenName
        protected set

    @Column(name = "licen_url")
    var licenUrl = licenUrl
        protected set

    // 레포지토리의 연관관계는 필요할때 설정.
}
