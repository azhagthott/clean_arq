package dev.zecovery.commons.ticket.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ScannedTicketLocal extends RealmObject {

    @PrimaryKey
    public int scannedTicketId;
    public String scannedTicketPaymentTimestamp;
    public String scannedTicketVisitorId;
    public String scannedTicketType;
    public int scannedTicketParkId;
    public String scannedTicketTicketName;
    public String scannedTicketTicketEntryDate;
    public String scannedTicketTicketExitDate;
    public String scannedTicketVisitorName;
    public String scannedTicketVisitorNationality;
    public String scannedTicketVisitorAge;
    public String scannedTicketTicketPrice;
}
