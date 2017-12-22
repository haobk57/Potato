package com.putatu.repository.search;

import com.putatu.domain.CustomerType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CustomerType entity.
 */
public interface CustomerTypeSearchRepository extends ElasticsearchRepository<CustomerType, Long> {
}
