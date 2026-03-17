package digit.academy.tutorial.repository;

import digit.academy.tutorial.models.Advocate;
import digit.academy.tutorial.models.AuditDetails;
import digit.academy.tutorial.models.Document;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class AdvocateRowMapper implements
        ResultSetExtractor<List<Advocate>> {

    @Override
    public List<Advocate> extractData(ResultSet rs)
            throws SQLException {

        Map<String, Advocate> advocateMap = new LinkedHashMap<>();

        while (rs.next()) {
            String id = rs.getString("id");

            // If advocate not already added, create it
            if (!advocateMap.containsKey(id)) {
                AuditDetails auditDetails = AuditDetails.builder()
                        .createdBy(rs.getString("createdby"))
                        .lastModifiedBy(rs.getString("lastmodifiedby"))
                        .createdTime(rs.getLong("createdtime"))
                        .lastModifiedTime(rs.getLong("lastmodifiedtime"))
                        .build();

                Advocate advocate = Advocate.builder()
                        .id(id)
                        .tenantId(rs.getString("tenantid"))
                        .applicationNumber(rs.getString("applicationnumber"))
                        .barRegistrationNumber(rs.getString("barregistrationnumber"))
                        .advocateType(rs.getString("advocatetype"))
                        .organisationID(rs.getString("organisationid"))
                        .individualId(rs.getString("individualid"))
                        .isActive(rs.getBoolean("isactive"))
                        .status(rs.getString("status"))
                        .auditDetails(auditDetails)
                        .documents(new ArrayList<>())
                        .build();

                advocateMap.put(id, advocate);
            }

            // Add document if exists
            String docId = rs.getString("doc.id");
            if (docId != null) {
                Document document = Document.builder()
                        .id(docId)
                        .documentType(rs.getString("documenttype"))
                        .fileStore(rs.getString("filestore"))
                        .documentUid(rs.getString("documentuid"))
                        .isActive(rs.getBoolean("doc.isactive"))
                        .build();

                advocateMap.get(id).getDocuments().add(document);
            }
        }

        return new ArrayList<>(advocateMap.values());
    }
}