package org.contribhub.infra.config

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.io.support.EncodedResource
import org.springframework.core.io.support.PropertySourceFactory

/**
 * todo yaml 파일이 load 되지 않아 추가된 클래스
 * @see <a href="https://terrys-tech-log.tistory.com/50"></a>
 */
class YamlPropertySourceFactory : PropertySourceFactory {
    override fun createPropertySource(
        name: String?,
        resource: EncodedResource,
    ): org.springframework.core.env.PropertySource<*> {
        val factory = YamlPropertiesFactoryBean()
        factory.setResources(resource.resource)
        val properties = factory.getObject()
        return PropertiesPropertySource(resource.resource.filename!!, properties!!)
    }
}
