package com.putatu.repository.search;

import com.putatu.domain.Position;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Position entity.
 */
public interface PositionSearchRepository extends ElasticsearchRepository<Position, Long> {
}
