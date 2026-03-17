package digit.academy.tutorial.enrichment;

import digit.academy.tutorial.models.Advocate;
import digit.academy.tutorial.models.AdvocateRequest;
import digit.academy.tutorial.models.AuditDetails;
import digit.academy.tutorial.util.IdgenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class AdvocateEnrichment {

    @Autowired
    private IdgenUtil idgenUtil;

    public void enrichCreate(AdvocateRequest request) {
        String requesterId = request.getRequestInfo()
                .getUserInfo().getUuid();
        Long currentTime = System.currentTimeMillis();

        int index = 1;
        for (Advocate advocate : request.getAdvocates()) {
            // Generate unique ID
            advocate.setId(UUID.randomUUID().toString());

            // Generate dummy application number for local testing
            // In production this comes from IDGen service
            String year = String.valueOf(
                    java.time.Year.now().getValue());
            String seqNum = String.format("%03d", index);
            advocate.setApplicationNumber(
                    "ADVOC_" + seqNum + "_" + year);

            // Set active status
            advocate.setIsActive(true);

            // Set audit details
            AuditDetails auditDetails = AuditDetails.builder()
                    .createdBy(requesterId)
                    .lastModifiedBy(requesterId)
                    .createdTime(currentTime)
                    .lastModifiedTime(currentTime)
                    .build();
            advocate.setAuditDetails(auditDetails);

            // Enrich documents if any
            if (advocate.getDocuments() != null) {
                advocate.getDocuments().forEach(doc -> {
                    doc.setId(UUID.randomUUID().toString());
                    doc.setIsActive(true);
                });
            }
            index++;
        }
    }

    public void enrichUpdate(AdvocateRequest request) {
        String requesterId = request.getRequestInfo()
                .getUserInfo().getUuid();
        Long currentTime = System.currentTimeMillis();

        for (Advocate advocate : request.getAdvocates()) {
            // Update audit details
            advocate.getAuditDetails()
                    .setLastModifiedBy(requesterId);
            advocate.getAuditDetails()
                    .setLastModifiedTime(currentTime);

            // Enrich new documents if any
            if (advocate.getDocuments() != null) {
                advocate.getDocuments().forEach(doc -> {
                    if (doc.getId() == null) {
                        doc.setId(UUID.randomUUID().toString());
                        doc.setIsActive(true);
                    }
                });
            }
        }
    }
}