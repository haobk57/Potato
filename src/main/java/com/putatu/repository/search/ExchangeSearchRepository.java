package com.putatu.repository.search;

import com.putatu.domain.Exchange;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Exchange entity.
 */
public interface ExchangeSearchRepository extends ElasticsearchRepository<Exchange, Long> {
}
