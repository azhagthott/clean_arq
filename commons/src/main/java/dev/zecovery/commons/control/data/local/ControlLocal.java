package dev.zecovery.commons.control.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ControlLocal extends RealmObject {

    @PrimaryKey
    public int controlLocalId;
    public int controlId;
    public String controlName;
    public int controlServiceId;
    public int controlParkId;
}
