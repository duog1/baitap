package com.example.jsp_nop.model;

public class SinhVien {
    private Long id;
    private String tenSinhVien;
    private String lopSinhVien;
    private String queQuanSinhVien;
    public SinhVien(Long id, String tenSinhVien, String lopSinhVien, String queQuanSinhVien) {
        this.id = id;
        this.tenSinhVien = tenSinhVien;
        this.lopSinhVien = lopSinhVien;
        this.queQuanSinhVien = queQuanSinhVien;
    }
    public SinhVien(){}

    public SinhVien(String tenSinhVien, String lopSinhVien, String queQuanSinhVien) {
        this.tenSinhVien = tenSinhVien;
        this.lopSinhVien = lopSinhVien;
        this.queQuanSinhVien = queQuanSinhVien;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getLopSinhVien() {
        return lopSinhVien;
    }

    public void setLopSinhVien(String lopSinhVien) {
        this.lopSinhVien = lopSinhVien;
    }

    public String getQueQuanSinhVien() {
        return queQuanSinhVien;
    }

    public void setQueQuanSinhVien(String queQuanSinhVien) {
        this.queQuanSinhVien = queQuanSinhVien;
    }
}
