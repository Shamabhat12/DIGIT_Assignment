package digit.academy.tutorial.repository;

import digit.academy.tutorial.models.AdvocateSearchCriteria;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.List;

@Component
public class AdvocateQueryBuilder {

    private static final String BASE_QUERY = "SELECT * FROM eg_advocate adv " +
            "LEFT JOIN eg_advocate_document doc " +
            "ON adv.id = doc.advocateid " +
            "WHERE adv.isactive = true ";

    public String getAdvocateSearchQuery(
            List<AdvocateSearchCriteria> criteriaList) {

        StringBuilder query = new StringBuilder(BASE_QUERY);

        if (criteriaList == null || criteriaList.isEmpty()) {
            return query.toString();
        }

        AdvocateSearchCriteria criteria = criteriaList.get(0);

        if (StringUtils.hasText(criteria.getId())) {
            query.append("AND adv.id = '")
                    .append(criteria.getId()).append("' ");
        }

        if (StringUtils.hasText(criteria.getApplicationNumber())) {
            query.append("AND adv.applicationnumber = '")
                    .append(criteria.getApplicationNumber())
                    .append("' ");
        }

        if (StringUtils.hasText(criteria.getBarRegistrationNumber())) {
            query.append("AND adv.barregistrationnumber = '")
                    .append(criteria.getBarRegistrationNumber())
                    .append("' ");
        }

        if (StringUtils.hasText(criteria.getIndividualId())) {
            query.append("AND adv.individualid = '")
                    .append(criteria.getIndividualId())
                    .append("' ");
        }

        if (StringUtils.hasText(criteria.getTenantId())) {
            query.append("AND adv.tenantid = '")
                    .append(criteria.getTenantId())
                    .append("' ");
        }

        return query.toString();
    }
}