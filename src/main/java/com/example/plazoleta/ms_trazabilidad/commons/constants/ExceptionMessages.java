package com.example.plazoleta.ms_trazabilidad.commons.constants;

public class ExceptionMessages {

    public static final String ORDER_MUST_BE_READY = "Order must be in READY state to be delivered";
    public static final String INVALID_PIN = "Invalid security pin";
    public static final String CLIENT_NOT_OWNER = "Client is not the owner of the order";
    public static final String ORDER_MUST_BE_PREPARING = "Order must be in PREPARING state to be delivered";
    public static final String ORDER_READY = "Your order is ready: PIN: ";
    public static final String ORDER_CANNOT_BE_ASSIGNES = "Order cannot be assigned to the employee";
    public static final Object INVALID_TOKEN_ERROR = "Invalid token: ";

    private ExceptionMessages() {
        throw new UnsupportedOperationException(UTILITY_CLASS_INSTANTIATION);
    }

    public static final String UTILITY_CLASS_INSTANTIATION = "Utility class cannot be instantiated";
    public static final String ORDER_NOT_FOUND="Order not found in traceability";
    public static final String ORDER_CAN_NOT_BE_CANCELLED="Order can not be cancelled";
}
