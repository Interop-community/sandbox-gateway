package org.hspconsortium.sandboxgateway.service;

import org.hspconsortium.sandboxgateway.model.ApiEndpointIndex;
import org.hspconsortium.sandboxgateway.model.Sandbox;
import org.hspconsortium.sandboxgateway.repository.FhirEndpointResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FhirEndpointResolutionServiceImpl implements FhirEndpointResolutionService {

    @Value("${api_url}")
    private String apiUrl;

    @Autowired
    private FhirEndpointResolutionRepository repository;

    @Qualifier("apiEndpointIndex")
    @Autowired
    private ApiEndpointIndex apiEndpointIndexObj;

    @Override
    public String getHost(String sandboxId) {
        Sandbox sandbox = repository.findDistinctBySandboxId(sandboxId);
        return getApiSchemaURL(sandbox.getApiEndpointIndex());
    }

    private String getApiSchemaURL(final String apiEndpointIndex) {
        System.out.println(this.apiEndpointIndexObj == null ? "apiEndpointIndex is null" : "apiEndpointIndex not null");
        if(apiEndpointIndex.equals(apiEndpointIndexObj.getPrev().getDstu2())) {
            return apiEndpointIndexObj.getPrev().getApiBaseURL_dstu2();
        }
        if(apiEndpointIndex.equals(apiEndpointIndexObj.getPrev().getStu3())) {
            return apiEndpointIndexObj.getPrev().getApiBaseURL_stu3();
        }
        if(apiEndpointIndex.equals(apiEndpointIndexObj.getPrev().getR4())) {
            return apiEndpointIndexObj.getPrev().getApiBaseURL_r4();
        }
        if(apiEndpointIndex.equals(apiEndpointIndexObj.getCurrent().getDstu2())) {
            return apiEndpointIndexObj.getCurrent().getApiBaseURL_dstu2();
        }
        if(apiEndpointIndex.equals(apiEndpointIndexObj.getCurrent().getStu3())) {
            return apiEndpointIndexObj.getCurrent().getApiBaseURL_stu3();
        }
        if(apiEndpointIndex.equals(apiEndpointIndexObj.getCurrent().getR4())) {
            return apiEndpointIndexObj.getCurrent().getApiBaseURL_r4();
        }
        if(apiEndpointIndex.equals(apiEndpointIndexObj.getCurrent().getR5())) {
            return apiEndpointIndexObj.getCurrent().getApiBaseURL_r5();
        }
        return "";
    }

    @Override
    public String getApiUrl() {
        return apiUrl;
    }
}
