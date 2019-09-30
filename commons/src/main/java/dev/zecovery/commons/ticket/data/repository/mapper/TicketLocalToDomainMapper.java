package dev.zecovery.commons.ticket.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.local.TicketLocal;
import dev.zecovery.commons.ticket.domain.model.Ticket;
import dev.zecovery.commons.visitor.domain.model.Visitor;

public class TicketLocalToDomainMapper extends Mapper<TicketLocal, Ticket> {

    @Inject
    public TicketLocalToDomainMapper() {
    }

    @Override
    public Ticket map(TicketLocal value) {
        Ticket model = new Ticket();
        Visitor visitor = new Visitor();

        model.ticketId = value.ticketId;
        model.ticketPaymentTimestamp = value.ticketPaymentTimestamp;
        visitor.visitorId = value.visitorId;
        visitor.visitorId = value.visitorId;
        visitor.visitorIdType = value.visitorIdType;
        visitor.visitorName = value.visitorName;
        visitor.visitorLastName = value.visitorLastName;
        visitor.visitorSurName = value.visitorSurName;
        visitor.visitorGender = value.visitorGender;
        visitor.visitorBirthDate = value.visitorBirthDate;
        visitor.visitorEmail = value.visitorEmail;
        visitor.visitorPhoneNumber = value.visitorPhoneNumber;
        visitor.visitorDisability = value.visitorDisability;
        visitor.visitorNationality = value.visitorNationality;
        visitor.visitorRegion = value.visitorRegion;
        model.ticketVisitor = visitor;
        return model;
    }

    @Override
    public TicketLocal reverseMap(Ticket value) {
        TicketLocal local = new TicketLocal();
        local.ticketId = value.ticketId;
        local.ticketPaymentTimestamp = value.ticketPaymentTimestamp;
        local.visitorId = value.ticketVisitor.visitorId;
        local.visitorIdType = value.ticketVisitor.visitorIdType;
        local.visitorName = value.ticketVisitor.visitorName;
        local.visitorLastName = value.ticketVisitor.visitorLastName;
        local.visitorSurName = value.ticketVisitor.visitorSurName;
        local.visitorGender = value.ticketVisitor.visitorGender;
        local.visitorBirthDate = value.ticketVisitor.visitorBirthDate;
        local.visitorEmail = value.ticketVisitor.visitorEmail;
        local.visitorPhoneNumber = value.ticketVisitor.visitorPhoneNumber;
        local.visitorDisability = value.ticketVisitor.visitorDisability;
        local.visitorNationality = value.ticketVisitor.visitorNationality;
        local.visitorRegion = value.ticketVisitor.visitorRegion;
        return local;
    }
}
