package digit.academy.tutorial.util;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class IdgenUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${egov.idgen.host}")
    private String idgenHost;

    @Value("${egov.idgen.path}")
    private String idgenPath;

    public List<String> getIdList(RequestInfo requestInfo,
            String tenantId,
            String idName,
            String idFormat,
            Integer count) {
        // Build request body as a Map
        List<Map<String, String>> idRequests = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<String, String> idRequest = new HashMap<>();
            idRequest.put("idName", idName);
            idRequest.put("tenantId", tenantId);
            if (idFormat != null) {
                idRequest.put("format", idFormat);
            }
            idRequests.add(idRequest);
        }

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("RequestInfo", requestInfo);
        requestBody.put("idRequests", idRequests);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Call IDGen service
        try {
            Map response = restTemplate.postForObject(
                    idgenHost + idgenPath,
                    entity,
                    Map.class);

            if (response == null) {
                throw new CustomException("IDGEN_ERROR",
                        "No response from IDGen service");
            }

            // Extract IDs from response
            List<Map<String, String>> idResponses = (List<Map<String, String>>) response
                    .get("idResponses");

            if (idResponses == null || idResponses.isEmpty()) {
                throw new CustomException("IDGEN_ERROR",
                        "No IDs returned from IDGen service");
            }

            List<String> ids = new ArrayList<>();
            idResponses.forEach(id -> ids.add(id.get("id")));
            return ids;

        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error calling IDGen service", e);
            throw new CustomException("IDGEN_ERROR",
                    "Error calling IDGen service: " + e.getMessage());
        }
    }
}