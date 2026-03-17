package digit.academy.tutorial.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import digit.academy.tutorial.models.Advocate;
import digit.academy.tutorial.models.AdvocateRequest;
import lombok.extern.slf4j.Slf4j;
import org.egov.common.contract.request.RequestInfo;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.*;

@Component
@Slf4j
public class WorkflowUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${egov.workflow.host}")
    private String workflowHost;

    @Value("${egov.workflow.transition.path}")
    private String workflowTransitionPath;

    public void updateWorkflowStatus(AdvocateRequest request) {
        List<Advocate> advocates = request.getAdvocates();
        RequestInfo requestInfo = request.getRequestInfo();

        advocates.forEach(advocate -> {
            if (advocate.getWorkflow() != null) {
                callWorkflowTransition(
                        requestInfo,
                        advocate.getTenantId(),
                        advocate.getId(),
                        advocate.getApplicationNumber(),
                        advocate.getWorkflow());
            }
        });
    }

    private void callWorkflowTransition(
            RequestInfo requestInfo,
            String tenantId,
            String businessId,
            String applicationNumber,
            digit.academy.tutorial.models.Workflow workflow) {

        // Build process instance as Map
        Map<String, Object> processInstance = new HashMap<>();
        processInstance.put("businessService", "ADVOCATE");
        processInstance.put("businessId", applicationNumber);
        processInstance.put("action", workflow.getAction());
        processInstance.put("tenantId", tenantId);
        processInstance.put("moduleName", "advocate-service");

        if (workflow.getComments() != null) {
            processInstance.put("comment", workflow.getComments());
        }

        if (workflow.getAssignees() != null) {
            List<Map<String, String>> assignees = new ArrayList<>();
            workflow.getAssignees().forEach(assignee -> {
                Map<String, String> assigneeMap = new HashMap<>();
                assigneeMap.put("uuid", assignee);
                assignees.add(assigneeMap);
            });
            processInstance.put("assignes", assignees);
        }

        // Build request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("RequestInfo", requestInfo);
        requestBody.put("ProcessInstances",
                Collections.singletonList(processInstance));

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Call workflow service
        try {
            Map response = restTemplate.postForObject(
                    workflowHost + workflowTransitionPath,
                    entity,
                    Map.class);

            log.info("Workflow transition response: {}", response);

        } catch (Exception e) {
            log.error("Error calling workflow service", e);
            throw new CustomException("WORKFLOW_ERROR",
                    "Error updating workflow status: "
                            + e.getMessage());
        }
    }
}