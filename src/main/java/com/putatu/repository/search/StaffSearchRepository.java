package com.putatu.repository.search;

import com.putatu.domain.Staff;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Staff entity.
 */
public interface StaffSearchRepository extends ElasticsearchRepository<Staff, Long> {
}
