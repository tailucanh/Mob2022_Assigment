package vn.edu.poly.ph26495_mob2022_ass.DTO;

import java.util.Date;

public class KhoanThuDTO {
    private int id_khoanThu;
    private String ten_khoanThu;
    private String ten_nguoiThu;
    private String  ngayThu;
    private int soTien;
    private String ghiChu;
    private int id_loaiThu;
    private String ten_loaiThu;

    public static final String TB_NAME = "tb_khoanThu";
    public static final String COL_ID = "id_khoanThu";
    public static final String COL_TEN_KHOAN_THU = "ten_khoanThu";
    public static final String COL_TEN_NGUOI_THU = "ten_nguoiThu";
    public static final String COL_NGAY_THU = "ngayThu";
    public static final String COL_SO_TIEN = "soTien";
    public static final String COL_GHI_CHU = "ghiChu";
    public static final String COL_ID_LOAI_THU = "id_loaiThu";


    public KhoanThuDTO() {
    }

    public KhoanThuDTO(int id_khoanThu, String ten_khoanThu, String ten_nguoiThu, String ngayThu, int soTien, String ghiChu, int id_loaiThu) {
        this.id_khoanThu = id_khoanThu;
        this.ten_khoanThu = ten_khoanThu;
        this.ten_nguoiThu = ten_nguoiThu;
        this.ngayThu = ngayThu;
        this.soTien = soTien;
        this.ghiChu = ghiChu;
        this.id_loaiThu = id_loaiThu;
    }

    public int getId_khoanThu() {
        return id_khoanThu;
    }

    public void setId_khoanThu(int id_khoanThu) {
        this.id_khoanThu = id_khoanThu;
    }

    public String getTen_khoanThu() {
        return ten_khoanThu;
    }

    public void setTen_khoanThu(String ten_khoanThu) {
        this.ten_khoanThu = ten_khoanThu;
    }

    public String getTen_nguoiThu() {
        return ten_nguoiThu;
    }

    public void setTen_nguoiThu(String ten_nguoiThu) {
        this.ten_nguoiThu = ten_nguoiThu;
    }

    public String getNgayThu() {
        return ngayThu;
    }

    public void setNgayThu(String ngayThu) {
        this.ngayThu = ngayThu;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getId_loaiThu() {
        return id_loaiThu;
    }

    public void setId_loaiThu(int id_loaiThu) {
        this.id_loaiThu = id_loaiThu;
    }

    public String getTen_loaiThu() {
        return ten_loaiThu;
    }

    public void setTen_loaiThu(String ten_loaiThu) {
        this.ten_loaiThu = ten_loaiThu;
    }
}
