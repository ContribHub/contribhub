package org.contribhub.api.domain.opensourcerepository.repository.mapping

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor

class CustomTopicRepositoryEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomTopicRepositoryEntityRepository
