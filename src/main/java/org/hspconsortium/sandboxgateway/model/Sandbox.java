package org.hspconsortium.sandboxgateway.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Sandbox {

    @Id
    private Integer id;
    @Column
    private String sandboxId;
    @Column
    private String apiEndpointIndex;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSandboxId() {
        return sandboxId;
    }

    public void setSandboxId(String sandboxId) {
        this.sandboxId = sandboxId;
    }

    public String getApiEndpointIndex() {
        return apiEndpointIndex;
    }

    public void setApiEndpointIndex(String apiEndpointIndex) {
        this.apiEndpointIndex = apiEndpointIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sandbox sandbox = (Sandbox) o;
        return Objects.equals(id, sandbox.id) &&
                Objects.equals(sandboxId, sandbox.sandboxId) &&
                Objects.equals(apiEndpointIndex, sandbox.apiEndpointIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sandboxId, apiEndpointIndex);
    }
}
