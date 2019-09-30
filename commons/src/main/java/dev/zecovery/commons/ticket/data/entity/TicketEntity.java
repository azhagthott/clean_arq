package dev.zecovery.commons.ticket.data.entity;

import java.util.List;

import dev.zecovery.commons.reservation.data.entity.ReservationEntity;
import dev.zecovery.commons.visitor.domain.model.Visitor;

public class TicketEntity {

    public int tick_id;
    public int tick_fecha_pago;
    public Visitor visitante;
    public List<ReservationEntity> reservas;
}
