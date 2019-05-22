package org.hspconsortium.sandboxgateway.repository;

import org.hspconsortium.sandboxgateway.model.Sandbox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FhirEndpointResolutionRepository extends CrudRepository<Sandbox, Integer> {

    Sandbox findDistinctBySandboxId(String sandboxId);

}
