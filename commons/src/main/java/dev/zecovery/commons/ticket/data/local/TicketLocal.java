package dev.zecovery.commons.ticket.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TicketLocal extends RealmObject {

    @PrimaryKey
    public int ticketId;
    public int ticketPaymentTimestamp;
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

}
