package digit.academy.tutorial.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
    private String id;
    private String documentType;
    private String fileStore;
    private String documentUid;
    private Boolean isActive;
    private AuditDetails auditDetails;
}
