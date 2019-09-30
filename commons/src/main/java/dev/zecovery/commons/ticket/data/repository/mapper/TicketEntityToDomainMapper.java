package dev.zecovery.commons.ticket.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.entity.TicketEntity;
import dev.zecovery.commons.ticket.domain.model.Ticket;

public class TicketEntityToDomainMapper extends Mapper<TicketEntity, Ticket> {

    @Inject
    public TicketEntityToDomainMapper() {
    }

    @Override
    public Ticket map(TicketEntity value) {
        Ticket model = new Ticket();
        model.ticketId = value.tick_id;
        model.ticketPaymentTimestamp = value.tick_fecha_pago;
        model.ticketVisitor = value.visitante;
        return model;
    }

    @Override
    public TicketEntity reverseMap(Ticket value) {
        return null;
    }
}
