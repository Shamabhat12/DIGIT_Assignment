package digit.academy.tutorial.repository;

import digit.academy.tutorial.models.Advocate;
import digit.academy.tutorial.models.AdvocateRequest;
import digit.academy.tutorial.models.AdvocateSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AdvocateRepository {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private AdvocateQueryBuilder queryBuilder;

    @Autowired
    private AdvocateRowMapper rowMapper;

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    @Value("${advocate.kafka.create.topic}")
    private String createTopic;

    @Value("${advocate.kafka.update.topic}")
    private String updateTopic;

    public void save(AdvocateRequest request) {
        kafkaTemplate.send(createTopic, request);
    }

    public void update(AdvocateRequest request) {
        kafkaTemplate.send(updateTopic, request);
    }

    public List<Advocate> search(
            List<AdvocateSearchCriteria> criteriaList) {
        String query = queryBuilder.getAdvocateSearchQuery(
                criteriaList);
        return jdbcTemplate.query(query, rowMapper);
    }
}