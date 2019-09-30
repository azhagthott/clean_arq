package dev.zecovery.commons.ticket.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.local.ScannedTicketLocal;
import dev.zecovery.commons.ticket.domain.model.ScannedTicket;

public class ScannedTicketLocalToDomainMapper extends Mapper<ScannedTicketLocal, ScannedTicket> {

    @Inject
    public ScannedTicketLocalToDomainMapper() {
    }

    @Override
    public ScannedTicket map(ScannedTicketLocal value) {
        ScannedTicket model = new ScannedTicket();
        model.scannedTicketId = value.scannedTicketId;
        model.scannedTicketVisitorId = value.scannedTicketVisitorId;
        model.scannedTicketPaymentTimestamp = value.scannedTicketPaymentTimestamp;
        model.scannedTicketType = value.scannedTicketType;
        model.scannedTicketParkId = value.scannedTicketParkId;
        model.scannedTicketTicketName = value.scannedTicketTicketName;
        model.scannedTicketTicketEntryDate = value.scannedTicketTicketEntryDate;
        model.scannedTicketTicketExitDate = value.scannedTicketTicketExitDate;
        model.scannedTicketVisitorName = value.scannedTicketVisitorName;
        model.scannedTicketVisitorNationality = value.scannedTicketVisitorNationality;
        model.scannedTicketVisitorAge = value.scannedTicketVisitorAge;
        model.scannedTicketTicketPrice = value.scannedTicketTicketPrice;
        return model;
    }

    @Override
    public ScannedTicketLocal reverseMap(ScannedTicket value) {
        ScannedTicketLocal local = new ScannedTicketLocal();
        local.scannedTicketId = value.scannedTicketId;
        local.scannedTicketVisitorId = value.scannedTicketVisitorId;
        local.scannedTicketPaymentTimestamp = value.scannedTicketPaymentTimestamp;
        local.scannedTicketType = value.scannedTicketType;
        local.scannedTicketParkId = value.scannedTicketParkId;
        local.scannedTicketTicketName = value.scannedTicketTicketName;
        local.scannedTicketTicketEntryDate = value.scannedTicketTicketEntryDate;
        local.scannedTicketTicketExitDate = value.scannedTicketTicketExitDate;
        local.scannedTicketVisitorName = value.scannedTicketVisitorName;
        local.scannedTicketVisitorNationality = value.scannedTicketVisitorNationality;
        local.scannedTicketVisitorAge = value.scannedTicketVisitorAge;
        local.scannedTicketTicketPrice = value.scannedTicketTicketPrice;
        return local;
    }
}
