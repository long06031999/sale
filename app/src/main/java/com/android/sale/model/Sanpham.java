package com.android.sale.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    int Id;
    String Tensp;
    Integer Giasp;
    String Hinhanhsp;
    String Motasp;
    int IdLoaisp;

    public Sanpham(int id, String tensp, Integer giasp, String hinhanhsp, String motasp, int idLoaisp) {
        Id = id;
        Tensp = tensp;
        Giasp = giasp;
        Hinhanhsp = hinhanhsp;
        Motasp = motasp;
        IdLoaisp = idLoaisp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public Integer getGiasp() {
        return Giasp;
    }

    public void setGiasp(Integer giasp) {
        Giasp = giasp;
    }

    public String getHinhanhsp() {
        return Hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        Hinhanhsp = hinhanhsp;
    }

    public String getMotasp() {
        return Motasp;
    }

    public void setMotasp(String motasp) {
        Motasp = motasp;
    }

    public int getIdLoaisp() {
        return IdLoaisp;
    }

    public void setIdLoaisp(int idLoaisp) {
        IdLoaisp = idLoaisp;
    }
}
