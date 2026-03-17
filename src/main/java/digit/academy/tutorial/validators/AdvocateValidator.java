package digit.academy.tutorial.validators;

import digit.academy.tutorial.models.Advocate;
import digit.academy.tutorial.models.AdvocateRequest;
import org.egov.tracer.model.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdvocateValidator {

    public void validateCreate(AdvocateRequest request) {
        Map<String, String> errorMap = new HashMap<>();

        List<Advocate> advocates = request.getAdvocates();

        if (advocates == null || advocates.isEmpty()) {
            throw new CustomException("ADVOCATE_CREATE_ERROR",
                    "Advocates list cannot be empty");
        }

        for (Advocate advocate : advocates) {
            // Validate tenantId
            if (!StringUtils.hasText(advocate.getTenantId())) {
                errorMap.put("INVALID_TENANT_ID",
                        "TenantId is mandatory");
            }

            // Validate individualId
            if (!StringUtils.hasText(advocate.getIndividualId())) {
                errorMap.put("INVALID_INDIVIDUAL_ID",
                        "IndividualId is mandatory");
            }

            // Validate advocateType
            if (!StringUtils.hasText(advocate.getAdvocateType())) {
                errorMap.put("INVALID_ADVOCATE_TYPE",
                        "AdvocateType is mandatory");
            }

            // Validate barRegistrationNumber
            if (!StringUtils.hasText(advocate.getBarRegistrationNumber())) {
                errorMap.put("INVALID_BAR_REG_NUMBER",
                        "Bar Registration Number is mandatory");
            }
        }

        if (!errorMap.isEmpty()) {
            throw new CustomException(errorMap);
        }
    }

    public void validateUpdate(AdvocateRequest request) {
        Map<String, String> errorMap = new HashMap<>();

        List<Advocate> advocates = request.getAdvocates();

        if (advocates == null || advocates.isEmpty()) {
            throw new CustomException("ADVOCATE_UPDATE_ERROR",
                    "Advocates list cannot be empty");
        }

        for (Advocate advocate : advocates) {
            // ID is mandatory for update
            if (!StringUtils.hasText(advocate.getId())) {
                errorMap.put("INVALID_ID",
                        "Id is mandatory for update");
            }

            // Validate tenantId
            if (!StringUtils.hasText(advocate.getTenantId())) {
                errorMap.put("INVALID_TENANT_ID",
                        "TenantId is mandatory");
            }
        }

        if (!errorMap.isEmpty()) {
            throw new CustomException(errorMap);
        }
    }
}