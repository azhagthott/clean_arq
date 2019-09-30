package dev.zecovery.commons.visitor.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.visitor.data.local.VisitorLocal;
import dev.zecovery.commons.visitor.domain.model.Visitor;

public class VisitorLocalToDoaminMapper extends Mapper<VisitorLocal, Visitor> {

    @Inject
    public VisitorLocalToDoaminMapper() {
    }

    @Override
    public Visitor map(VisitorLocal value) {

        Visitor model = new Visitor();
        model.visitorId = value.visitorId;
        model.visitorIdType = value.visitorIdType;
        model.visitorName = value.visitorName;
        model.visitorLastName = value.visitorLastName;
        model.visitorSurName = value.visitorSurName;
        model.visitorGender = value.visitorGender;
        model.visitorBirthDate = value.visitorBirthDate;
        model.visitorEmail = value.visitorEmail;
        model.visitorPhoneNumber = value.visitorPhoneNumber;
        model.visitorDisability = value.visitorDisability;
        model.visitorNationality = value.visitorNationality;
        model.visitorNationality = value.visitorNationality;
        model.visitorRegion = value.visitorRegion;
        model.visitorTicketId = value.visitorTicketId;
        return model;
    }

    @Override
    public VisitorLocal reverseMap(Visitor value) {
        VisitorLocal local = new VisitorLocal();
        local.visitorId = value.visitorId;
        local.visitorIdType = value.visitorIdType;
        local.visitorName = value.visitorName;
        local.visitorLastName = value.visitorLastName;
        local.visitorSurName = value.visitorSurName;
        local.visitorGender = value.visitorGender;
        local.visitorBirthDate = value.visitorBirthDate;
        local.visitorEmail = value.visitorEmail;
        local.visitorPhoneNumber = value.visitorPhoneNumber;
        local.visitorDisability = value.visitorDisability;
        local.visitorNationality = value.visitorNationality;
        local.visitorNationality = value.visitorNationality;
        local.visitorRegion = value.visitorRegion;
        local.visitorTicketId = value.visitorTicketId;
        return local;
    }
}
