package digit.academy.tutorial;

import digit.academy.tutorial.models.*;
import digit.academy.tutorial.services.AdvocateService;
import digit.academy.tutorial.repository.AdvocateRepository;
import digit.academy.tutorial.enrichment.AdvocateEnrichment;
import digit.academy.tutorial.validators.AdvocateValidator;
import digit.academy.tutorial.util.ResponseInfoFactory;
import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.response.ResponseInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AdvocateServiceTest {

    @Mock
    private AdvocateValidator advocateValidator;
    @Mock
    private AdvocateEnrichment advocateEnrichment;
    @Mock
    private AdvocateRepository advocateRepository;
    @Mock
    private ResponseInfoFactory responseInfoFactory;
    @InjectMocks
    private AdvocateService advocateService;

    // ✅ Test 1: Create Advocate
    @Test
    public void testCreateAdvocate() {
        Advocate advocate = Advocate.builder()
                .tenantId("pb")
                .individualId("IND-2024-001")
                .barRegistrationNumber("BAR123456")
                .advocateType("ADVOCATE")
                .isActive(true)
                .build();

        RequestInfo requestInfo = new RequestInfo();
        AdvocateRequest request = AdvocateRequest.builder()
                .requestInfo(requestInfo)
                .advocates(List.of(advocate))
                .build();

        when(responseInfoFactory
                .createResponseInfoFromRequestInfo(any(), anyBoolean()))
                .thenReturn(new ResponseInfo());

        AdvocateResponse response = advocateService.create(request);

        assertNotNull(response);
        assertEquals(1, response.getAdvocates().size());
        verify(advocateValidator).validateCreate(request);
        verify(advocateEnrichment).enrichCreate(request);
        verify(advocateRepository).save(request);
    }

    // ✅ Test 2: Update Advocate
    @Test
    public void testUpdateAdvocate() {
        Advocate advocate = Advocate.builder()
                .id("d97fe2d6-0535-4f05-9494-d0995de6566d")
                .tenantId("pb")
                .individualId("IND-2024-001")
                .barRegistrationNumber("BAR123456")
                .isActive(false)
                .build();

        RequestInfo requestInfo = new RequestInfo();
        AdvocateRequest request = AdvocateRequest.builder()
                .requestInfo(requestInfo)
                .advocates(List.of(advocate))
                .build();

        when(responseInfoFactory
                .createResponseInfoFromRequestInfo(any(), anyBoolean()))
                .thenReturn(new ResponseInfo());

        AdvocateResponse response = advocateService.update(request);

        assertNotNull(response);
        assertEquals(1, response.getAdvocates().size());
        verify(advocateValidator).validateUpdate(request);
        verify(advocateEnrichment).enrichUpdate(request);
        verify(advocateRepository).update(request);
    }

    // ✅ Test 3: Search Advocate
    @Test
    public void testSearchAdvocate() {
        AdvocateSearchCriteria criteria = AdvocateSearchCriteria.builder()
                .tenantId("pb")
                .build();

        RequestInfo requestInfo = new RequestInfo();
        AdvocateSearchRequest request = AdvocateSearchRequest.builder()
                .requestInfo(requestInfo)
                .criteria(List.of(criteria))
                .build();

        List<Advocate> advocates = new ArrayList<>();
        advocates.add(Advocate.builder().tenantId("pb").build());

        when(advocateRepository.search(any())).thenReturn(advocates);
        when(responseInfoFactory
                .createResponseInfoFromRequestInfo(any(), anyBoolean()))
                .thenReturn(new ResponseInfo());

        AdvocateResponse response = advocateService.search(request);

        assertNotNull(response);
        assertEquals(1, response.getAdvocates().size());
        verify(advocateRepository).search(request.getCriteria());
    }
}