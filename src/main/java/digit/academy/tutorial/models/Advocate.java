package digit.academy.tutorial.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Advocate {
    private String id;
    private String tenantId;
    private String applicationNumber;
    private String barRegistrationNumber;
    private String advocateType;
    private String organisationID;
    private String individualId;
    private Boolean isActive;
    private String status;
    private Workflow workflow;
    private List<Document> documents;
    private AuditDetails auditDetails;
    private Object additionalDetails;
}