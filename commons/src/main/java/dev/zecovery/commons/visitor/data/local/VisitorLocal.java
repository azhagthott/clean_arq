package dev.zecovery.commons.visitor.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VisitorLocal extends RealmObject {

    @PrimaryKey
    public String visitorId;
    public int visitorIdType;
    public String visitorName;
    public String visitorLastName;
    public String visitorSurName;
    public String visitorGender;
    public int visitorBirthDate;
    public String visitorEmail;
    public String visitorPhoneNumber;
    public int visitorDisability;
    public String visitorNationality;
    public String visitorRegion;
    public int visitorTicketId;
}
