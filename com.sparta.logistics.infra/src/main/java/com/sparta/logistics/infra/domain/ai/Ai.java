package com.sparta.logistics.infra.domain.ai;

public class Ai {

    private Long orderId;
    private String userName;
    private String productName;
    private int productQuantity;
    private String request;
    private String originHub;
    private String destinationHub;
    private String companyAddress;
    private String deliveryPersonName;
    private String snsAccount;

    public Ai(
            Long orderId, String userName, String productName, int productQuantity,
            String request, String originHub, String destinationHub, String companyAddress, String deliveryPersonName,
            String snsAccount) {
        this.orderId = orderId;
        this.userName = userName;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.request = request;
        this.originHub = originHub;
        this.destinationHub = destinationHub;
        this.companyAddress = companyAddress;
        this.deliveryPersonName = deliveryPersonName;
        this.snsAccount = snsAccount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getRequest() {
        return request;
    }

    public String getOriginHub() {
        return originHub;
    }

    public String getDestinationHub() {
        return destinationHub;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getDeliveryPersonName() {
        return deliveryPersonName;
    }

    public String getSnsAccount() {
        return snsAccount;
    }
}
