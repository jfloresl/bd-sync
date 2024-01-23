package com.bdsync.vo;

public class DatabaseVO {
    public String name;
    public String host;
    public String port;
    public String service;
    public String schema;
    public String user;
    public String pass;

    public DatabaseVO(String name, String host, String port, String service, String schema, String user, String pass) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.service = service;
        this.schema = schema;
        this.user = user;
        this.pass = pass;
    }

    public DatabaseVO() {

    }

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
