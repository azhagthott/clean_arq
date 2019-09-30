package dev.zecovery.commons;

public class App {

    public static final int DATA_BASE_SCHEMA_VERSION = 1;

    public static final String SCAN = "SCAN";
    public static final String SCAN_ENTRANCE = "SCAN_ENTRANCE";
    public static final String SCAN_RESERVATION = "SCAN_RESERVATION";
    public static final String SCAN_EXIT = "SCAN_EXIT";

    public static final String TICKET_ENTRY_EXIT = "1";
    public static final String TICKET_RESERVATION = "2";
    public static final String INVALID_TICKET_TYPE = "INVALID_TICKET_TYPE";

    public static final int TIME_OUT = 60;
    public static final int READ_TIME_OUT = 60;
    public static final int WRITE_TIME_OUT = 60;
    public static final int RETRIES_COUNT = 3;

    /*
     * Preference key, user location
     */
    public static final String LOCATION_PREFERENCE = "LOCATION_PREFERENCE";
    public static final String LOCATION_PREFERENCE_PARK = "PARK";
    public static final String LOCATION_PREFERENCE_SERVICE = "SERVICE";
    public static final String LOCATION_PREFERENCE_CONTROL = "CONTROL";

    public static final String PARK_ID = "PARK_ID";
    public static final String PARK_NAME = "PARK_NAME";
    public static final String SERVICE_ID = "SERVICE_ID";
    public static final String SERVICE_NAME = "SERVICE_NAME";
    public static final String CONTROL_ID = "CONTROL_ID";
    public static final String CONTROL_NAME = "CONTROL_NAME";


    /*
     * Object names in JSON
     */
    // TICKET
    public static final String DELETE = "borrar";
    public static final String NEW = "nuevo";
    public static final String TICKET = "ticket";
    public static final String TICKET_ID = "tick_id";
    public static final String TICKET_PAYMENT_TIMESTAMP = "tick_fecha_pago";
    public static final String TICKET_VISITOR = "visitante";
    // VISITOR
    public static final String VISITOR_ID_TYPE = "visi_tipo_identificador";
    public static final String VISITOR_ID = "visi_identificador";
    public static final String VISITOR_NAME = "visi_nombre";
    public static final String VISITOR_LAST_NAME = "visi_paterno";
    public static final String VISITOR_SURNAME = "visi_materno";
    public static final String VISITOR_GENDER = "visi_genero";
    public static final String VISITOR_BIRTHDAY = "visi_fecha_nacimiento";
    public static final String VISITOR_EMAIL = "visi_email";
    public static final String VISITOR_PHONE_NUMBER = "visi_fono";
    public static final String VISITOR_DISABILITY = "visi_discapacidad";
    public static final String VISITOR_NATIONALITY = "visi_nacionalidad";
    public static final String VISITOR_REGION = "visi_region";

}
