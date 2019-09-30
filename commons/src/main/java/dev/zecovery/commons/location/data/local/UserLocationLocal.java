package dev.zecovery.commons.location.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserLocationLocal extends RealmObject {

    @PrimaryKey
    public int userLocationId;
    public int userLocationParkId;
    public String userLocationParkName;
    public int userLocationServiceId;
    public String userLocationServiceName;
    public int userLocationControlId;
    public String userLocationControlName;
}
