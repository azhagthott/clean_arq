package dev.zecovery.commons.ticket.domain.model;

import dev.zecovery.commons.visitor.domain.model.Visitor;

public class Ticket {

    public int ticketId;
    public int ticketPaymentTimestamp;
    public Visitor ticketVisitor;
}
