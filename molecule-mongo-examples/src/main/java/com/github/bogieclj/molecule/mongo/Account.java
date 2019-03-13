package com.github.bogieclj.molecule.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.List;
import java.util.Objects;

public class Account {

    @MongoObjectId
    private String _id;

    private String name;
    private String type;
    private String email;
    private boolean active;

    @JsonProperty("subscribed_services")
    private List<String> subscribedServices;



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getSubscribedServices() {
        return subscribedServices;
    }

    public void setSubscribedServices(List<String> subscribedServices) {
        this.subscribedServices = subscribedServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(get_id(), account.get_id());
    }

    @Override
    public int hashCode() {

        return Objects.hash(get_id());
    }

    @Override
    public String toString() {
        return "Account{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", subscribedServices=" + subscribedServices +
                '}';
    }
}
