package com.putatu.repository.search;

import com.putatu.domain.PaymentCoin;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PaymentCoin entity.
 */
public interface PaymentCoinSearchRepository extends ElasticsearchRepository<PaymentCoin, Long> {
}
