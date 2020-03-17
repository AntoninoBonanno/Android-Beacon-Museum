package com.biusobonnanno.beacon_museum.models;

public class EddystoneUidData {
    private String namespaceId, instanceId;

    public EddystoneUidData(String namespaceId, String instanceId) {
        this.namespaceId = namespaceId;
        this.instanceId = instanceId;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public String getInstanceId() {
        return instanceId;
    }
}
