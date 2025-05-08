package com.example.plazoleta.ms_trazabilidad.domain.model;

import java.util.List;
import java.util.Objects;

public class OrderTraceability {

    private String id;
    private String assignedEmployeeId;
    private String clientId;
    private String orderId;
    private String securityPin;
    private Long orderDurationInMilliseconds;
    private List<OrderTrackingModel> orderTrack;

    public OrderTraceability(String id, String assignedEmployeeId,
                             String clientId, String orderId,
                             String securityPin,
                             Long orderDurationInMilliseconds, List<OrderTrackingModel> orderTrack) {
        this.id = id;
        this.assignedEmployeeId = assignedEmployeeId;
        this.clientId = clientId;
        this.orderId = orderId;
        this.securityPin = securityPin;
        this.orderDurationInMilliseconds = orderDurationInMilliseconds;
        this.orderTrack = orderTrack;
    }
    public OrderTraceability(String id, String assignedEmployeeId
                             , String orderId,
                             String securityPin,
                             Long orderDurationInMilliseconds, List<OrderTrackingModel> orderTrack) {
        this.id = id;
        this.assignedEmployeeId = assignedEmployeeId;
        this.orderId = orderId;
        this.securityPin = securityPin;
        this.orderDurationInMilliseconds = orderDurationInMilliseconds;
        this.orderTrack = orderTrack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(String assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getOrderDurationInMilliseconds() {
        return orderDurationInMilliseconds;
    }

    public void setOrderDurationInMilliseconds(Long orderDurationInMilliseconds) {
        this.orderDurationInMilliseconds = orderDurationInMilliseconds;
    }

    public String getSecurityPin() {
        return securityPin;
    }

    public void setSecurityPin(String securityPin) {
        this.securityPin = securityPin;
    }

    public List<OrderTrackingModel> getOrderTrack() {
        return orderTrack;
    }

    public void setOrderTrack(List<OrderTrackingModel> orderTrack) {
        this.orderTrack = orderTrack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderTraceability that = (OrderTraceability) o;
        return Objects.equals(id, that.id) && Objects.equals(assignedEmployeeId, that.assignedEmployeeId) && Objects.equals(clientId, that.clientId) && Objects.equals(orderId, that.orderId) && Objects.equals(orderTrack, that.orderTrack);
    }

    @Override
    public String toString() {
        return "OrderTraceability{" +
                "id='" + id + '\'' +
                ", assignedEmployeeId=" + assignedEmployeeId +
                ", clientId=" + clientId +
                ", orderId=" + orderId +
                ", securityPin='" + securityPin + '\'' +
                ", orderDurationInMilliseconds=" + orderDurationInMilliseconds +
                ", orderTrack=" + orderTrack +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignedEmployeeId, clientId, orderId, orderTrack);
    }
}
