package dev.zecovery.commons.service.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ServiceLocal extends RealmObject {

    @PrimaryKey
    public int serviceId;
    public String serviceName;
    public int serviceParkId;
    public String serviceControlList;
}
