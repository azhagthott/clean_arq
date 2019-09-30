package dev.zecovery.commons.qrobject;

import java.util.List;

public class QrObject {

    public String qrUrl; // URL

    public String qrType; //1 = entrance, 2 = reservation, 3 = exit
    public String qrIdTicket;
    public String qrTicketName; // Ticket name ex: Adulto+Mayor+Nacional
    public String qrVisitorId; // Visitor Id
    public String qrTicketEntryDate; // Entry date
    public String qrTicketExitDate; // Exit date
    public String qrParkId; // Park Id
    public String qrVisitorPassport;
    public String qrVisitorName;
    public String qrVisitorNationality;
    public String qrVisitorAge;
    public String qrTicketPrice;
    public List<QrObjectReservation> qrObjectReservations;
}