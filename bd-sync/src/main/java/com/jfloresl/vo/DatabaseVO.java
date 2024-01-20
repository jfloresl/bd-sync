package com.jfloresl.vo;

public class DatabaseVO {
    public String name;
    public String host;
    public String port;
    public String service;
    public String schema;
    public String user;
    public String pass;

    @Override
    public String toString() {
        return "DatabaseVO{" +
                "name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", service='" + service + '\'' +
                ", schema='" + schema + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
