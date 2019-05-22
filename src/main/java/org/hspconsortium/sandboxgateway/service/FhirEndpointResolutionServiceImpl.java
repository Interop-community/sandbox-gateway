package org.hspconsortium.sandboxgateway.service;

import org.hspconsortium.sandboxgateway.model.ApiEndpointIndex;
import org.hspconsortium.sandboxgateway.model.Sandbox;
import org.hspconsortium.sandboxgateway.repository.FhirEndpointResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FhirEndpointResolutionServiceImpl implements FhirEndpointResolutionService {

    @Autowired
    private FhirEndpointResolutionRepository repository;

    @Autowired
    private ApiEndpointIndex apiEndpointIndexObj;

    public String getHost(String sandboxId) {
        Sandbox sandbox = repository.findDistinctBySandboxId(sandboxId);
        return getApiSchemaURL(sandbox.getApiEndpointIndex());
    }

    private String getApiSchemaURL(final String apiEndpointIndex) {

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
        return "";
    }
}
