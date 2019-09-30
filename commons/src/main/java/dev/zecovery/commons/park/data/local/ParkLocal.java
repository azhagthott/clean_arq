package dev.zecovery.commons.park.data.local;

import dev.zecovery.commons.service.data.local.ServiceLocal;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ParkLocal extends RealmObject {

    @PrimaryKey
    public int parkId;
    public String parkName;
    public RealmList<ServiceLocal> parkServiceList;
}
