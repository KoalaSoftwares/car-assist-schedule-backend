import java.util.Date;

import com.una.models.ServiceType;

public class Schedule {
    private String id;
    private Date date;
    private String price;
    private ServiceType serviceType;
    private String clientName;
    private String clientCar;


    public Schedule() {
    }

    public Schedule(String id, Date date, String price, ServiceType serviceType, String clientName, String clientCar) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.serviceType = serviceType;
        this.clientName = clientName;
        this.clientCar = clientCar;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCar() {
        return this.clientCar;
    }

    public void setClientCar(String clientCar) {
        this.clientCar = clientCar;
    }
}