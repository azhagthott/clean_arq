package dev.zecovery.commons.service.data.entity;

import com.google.gson.JsonObject;

import java.util.List;

public class ServiceEntity {
    public int serv_id;
    public String serv_nombre;
    public List<JsonObject> serv_lugar_control;
}
