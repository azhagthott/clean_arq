package dev.zecovery.commons.control.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.control.data.entity.ControlEntity;
import dev.zecovery.commons.control.domain.model.Control;

public class ControlEntityToDomainMapper extends Mapper<ControlEntity, Control> {

    @Inject
    public ControlEntityToDomainMapper() {
    }

    @Override
    public Control map(ControlEntity value) {
        Control model = new Control();
        model.controlId = value.cont_id;
        model.controlName = value.cont_nombre;
        return model;
    }

    @Override
    public ControlEntity reverseMap(Control value) {
        return null;
    }
}
