package com.una.carassistschedulebackend.entidades;

import java.math.BigDecimal;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "schedule")
public class Schedule {
    private String id;
    private Date date;
    private BigDecimal price;
    private String assistanceType;
    private String clientName;
    private String clientCar;
    private String status;


    public Schedule() {
        super();
    }

    public Schedule(Date date, BigDecimal price, String assistanceType, String clientName, String clientCar, String status) {
        super();
        this.date = date;
        this.price = price;
        this.assistanceType = assistanceType;
        this.clientName = clientName;
        this.clientCar = clientCar;
        this.status = status;
    }

    @DynamoDBHashKey(attributeName = "scheduleId")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "date")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @DynamoDBAttribute(attributeName = "serviceType")
    public String getServiceType() {
        return this.assistanceType;
    }

    public void setServiceType(String serviceType) {
        this.assistanceType = serviceType;
    }

    @DynamoDBAttribute(attributeName = "clientName")
    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @DynamoDBAttribute(attributeName = "clientCar")
    public String getClientCar() {
        return this.clientCar;
    }

    public void setClientCar(String clientCar) {
        this.clientCar = clientCar;
    }

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}