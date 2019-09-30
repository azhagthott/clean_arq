package dev.zecovery.commons.control.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.control.data.local.ControlLocal;
import dev.zecovery.commons.control.domain.model.Control;

public class ControlLocalToDomainMapper extends Mapper<ControlLocal, Control> {

    @Inject
    public ControlLocalToDomainMapper() {
    }

    @Override
    public Control map(ControlLocal value) {
        Control model = new Control();
        model.controlId = value.controlId;
        model.controlName = value.controlName;
        model.controlServiceId = value.controlServiceId;
        model.controlParkId = value.controlParkId;
        return model;
    }

    @Override
    public ControlLocal reverseMap(Control value) {
        ControlLocal local = new ControlLocal();
        local.controlId = value.controlId;
        local.controlName = value.controlName;
        local.controlServiceId = value.controlServiceId;
        local.controlParkId = value.controlParkId;
        return local;
    }
}
