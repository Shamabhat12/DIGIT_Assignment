package digit.academy.tutorial.util;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.response.ResponseInfo;
import org.springframework.stereotype.Component;

@Component
public class ResponseInfoFactory {

    public ResponseInfo createResponseInfoFromRequestInfo(
            RequestInfo requestInfo, Boolean success) {

        String status = success ? "successful" : "failed";

        return ResponseInfo.builder()
                .apiId(requestInfo.getApiId())
                .ver(requestInfo.getVer())
                .ts(requestInfo.getTs())
                .resMsgId(requestInfo.getMsgId())
                .msgId(requestInfo.getMsgId())
                .status(status)
                .build();
    }
}