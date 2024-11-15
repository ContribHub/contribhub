package org.contribhub.api.domain.opensourcerepository.repository.issues

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor

class CustomIssueEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomIssueEntityRepository
