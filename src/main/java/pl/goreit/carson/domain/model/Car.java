package pl.goreit.carson.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Car {

    @Id
    private String id;

    private String owner;

    private Integer No;

    private String make;
    private String model;
    private Integer year;
    private Integer course;
    private List<Route> routeList;

    private CarStatus carStatus;

    public Car(String owner, Integer No, String make, String model, Integer year, Integer course, List<Route> routeList) {
        this.owner = owner;
        this.No = No;
        this.make = make;
        this.model = model;
        this.year = year;
        this.course = course;
        this.routeList = routeList;
        this.carStatus = CarStatus.ACTIVE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getNo() {
        return No;
    }

    public void setNo(Integer no) {
        No = no;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public void markForSell() {
        this.carStatus = CarStatus.FOR_SELL;
    }

    public void addRoute(Route route) {
        List<Route> routeList = this.getRouteList();
        routeList.add(route);

        course+= route.getDistance();
    }

    public enum CarStatus {
        ACTIVE, FOR_SELL, SOLD
    }
}
