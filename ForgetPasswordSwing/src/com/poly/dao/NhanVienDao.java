/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.dao;


import com.poly.bean.NhanVien;
import com.poly.connect.JDBC;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author tungt
 */
public class NhanVienDao extends EntityDao<NhanVien, String> {

    String insert = "insert into nhanvien values (?,?,?,?,?,?,?,?,?,?,?,?)";
    String updata = "update nhanvien set tenNV = ?, matKhau = ?, sdt = ?, vaiTro = ?"
            + ",cccd = ?, email = ?, gioiTinh = ?, trangThai = ?, ngaySinh = ?, diaChi = ?, hinhAnh = ? where maNV = ?";
    String delete = "delete nhanvien where maNV = ?";
    String selectAll = "select * from nhanvien";
    String select_byID = "select * from nhanvien where maNV = ?";
    String select_byEmail = "select * from nhanvien where email = ?";
    String updateMatKhau = "update nhanvien set matKhau = ? where maNV = ?";

    @Override
    public void insert(NhanVien entity) {
        JDBC.update(insert, entity.getMaNV(), entity.getTenNV(), entity.getMatKhau(), entity.getSdt(),
                entity.isVaiTro(), entity.getCccd(), entity.getEmail(), entity.isGioiTinh(), entity.isTrangThai(),
                entity.getNgaySinh(), entity.getDiaChi(), entity.getHinhAnh());
    }

    @Override
    public void update(NhanVien entity) {
        JDBC.update(updata, entity.getTenNV(), entity.getMatKhau(), entity.getSdt(),
                entity.isVaiTro(), entity.getCccd(), entity.getEmail(), entity.isGioiTinh(), entity.isTrangThai(),
                entity.getNgaySinh(), entity.getDiaChi(), entity.getHinhAnh(), entity.getMaNV());
    }

    public boolean capNhatMatKhau(String matKhau, String maNV) {
        JDBC.update(updateMatKhau, matKhau, maNV);
        return true;
    }

    @Override
    public void delete(String key) {
        JDBC.update(delete, key);
    }

    @Override
    public List<NhanVien> selectAll() {
        return select_by_sql(selectAll);
    }

    @Override
    public NhanVien select_byID(String key) {
        List<NhanVien> list = this.select_by_sql(select_byID, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhanVien select_byEmail(String key) {
        List<NhanVien> list = this.select_by_sql(select_byEmail, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> select_by_sql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet r = JDBC.query(sql, args);
            while (r.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(r.getString("maNV"));
                nv.setTenNV(r.getString("tenNV"));
                nv.setMatKhau(r.getString("matKhau"));
                nv.setSdt(r.getString("sdt"));
                nv.setVaiTro(r.getBoolean("vaiTro"));
                nv.setCccd(r.getString("cccd"));
                nv.setEmail(r.getString("email"));
                nv.setGioiTinh(r.getBoolean("gioiTinh"));
                nv.setTrangThai(r.getBoolean("trangThai"));
                nv.setNgaySinh(r.getDate("ngaySinh"));
                nv.setDiaChi(r.getString("diaChi"));
                nv.setHinhAnh(r.getString("hinhAnh"));
                list.add(nv);

            }

            r.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<NhanVien> searchNV(String key) {
        String select_search = "select * from nhanvien where tenNV like ?";
        return select_by_sql(select_search, "%" + key + "%");
    }

}
