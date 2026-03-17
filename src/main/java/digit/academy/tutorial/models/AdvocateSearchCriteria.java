package digit.academy.tutorial.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvocateSearchCriteria {
    private String id;
    private String barRegistrationNumber;
    private String applicationNumber;
    private String individualId;
    private String tenantId;
}