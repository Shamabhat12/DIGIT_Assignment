package digit.academy.tutorial.services;

import digit.academy.tutorial.models.*;
import digit.academy.tutorial.repository.AdvocateRepository;
import digit.academy.tutorial.enrichment.AdvocateEnrichment;
import digit.academy.tutorial.validators.AdvocateValidator;
import digit.academy.tutorial.util.WorkflowUtil;
import digit.academy.tutorial.util.ResponseInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdvocateService {

        @Autowired
        private AdvocateValidator advocateValidator;

        @Autowired
        private AdvocateEnrichment advocateEnrichment;

        @Autowired
        private AdvocateRepository advocateRepository;

        @Autowired
        private WorkflowUtil workflowUtil;

        @Autowired
        private ResponseInfoFactory responseInfoFactory;

        public AdvocateResponse create(AdvocateRequest request) {
                // Step 1: Validate the request
                advocateValidator.validateCreate(request);

                // Step 2: Enrich the request
                advocateEnrichment.enrichCreate(request);

                // Step 3: Update workflow status
                // workflowUtil.updateWorkflowStatus(request);

                // Step 4: Save via Kafka to database
                advocateRepository.save(request);

                // Step 5: Return response
                return AdvocateResponse.builder()
                                .advocates(request.getAdvocates())
                                .responseInfo(responseInfoFactory
                                                .createResponseInfoFromRequestInfo(
                                                                request.getRequestInfo(), true))
                                .build();
        }

        public AdvocateResponse update(AdvocateRequest request) {
                // Step 1: Validate the request
                advocateValidator.validateUpdate(request);

                // Step 2: Enrich the request
                advocateEnrichment.enrichUpdate(request);

                // Step 3: Update workflow status
                // workflowUtil.updateWorkflowStatus(request);

                // Step 4: Update via Kafka to database
                advocateRepository.update(request);

                // Step 5: Return response
                return AdvocateResponse.builder()
                                .advocates(request.getAdvocates())
                                .responseInfo(responseInfoFactory
                                                .createResponseInfoFromRequestInfo(
                                                                request.getRequestInfo(), true))
                                .build();
        }

        public AdvocateResponse search(AdvocateSearchRequest request) {
                // Step 1: Search in database
                List<Advocate> advocates = advocateRepository
                                .search(request.getCriteria());

                // Step 2: Return response
                return AdvocateResponse.builder()
                                .advocates(advocates)
                                .responseInfo(responseInfoFactory
                                                .createResponseInfoFromRequestInfo(
                                                                request.getRequestInfo(), true))
                                .build();
        }
}