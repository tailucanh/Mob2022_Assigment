package vn.edu.poly.ph26495_mob2022_ass.DTO;

public class KhoanChiDTO {
    private int id_khoanChi;
    private String ten_khoanChi;
    private String ten_nguoiChi;
    private String  ngayChi;
    private int soTien;
    private String ghiChu;
    private int id_loaiChi;
    private String ten_loaiChi;

    public static final String TB_NAME = "tb_khoanChi";
    public static final String COL_ID = "id_khoanChi";
    public static final String COL_TEN_KHOAN_CHI = "ten_khoanChi";
    public static final String COL_TEN_NGUOI_CHI = "ten_nguoiChi";
    public static final String COL_NGAY_CHI = "ngayChi";
    public static final String COL_SO_TIEN = "soTien";
    public static final String COL_GHI_CHU = "ghiChu";
    public static final String COL_ID_LOAI_CHI = "id_loaiChi";


    public KhoanChiDTO() {
    }

    public KhoanChiDTO(int id_khoanChi, String ten_khoanChi, String ten_nguoiChi, String ngayChi, int soTien, String ghiChu, int id_loaiChi) {
        this.id_khoanChi = id_khoanChi;
        this.ten_khoanChi = ten_khoanChi;
        this.ten_nguoiChi = ten_nguoiChi;
        this.ngayChi = ngayChi;
        this.soTien = soTien;
        this.ghiChu = ghiChu;
        this.id_loaiChi = id_loaiChi;
    }

    public int getId_khoanChi() {
        return id_khoanChi;
    }

    public void setId_khoanChi(int id_khoanChi) {
        this.id_khoanChi = id_khoanChi;
    }

    public String getTen_khoanChi() {
        return ten_khoanChi;
    }

    public void setTen_khoanChi(String ten_khoanChi) {
        this.ten_khoanChi = ten_khoanChi;
    }

    public String getTen_nguoiChi() {
        return ten_nguoiChi;
    }

    public void setTen_nguoiChi(String ten_nguoiChi) {
        this.ten_nguoiChi = ten_nguoiChi;
    }

    public String getNgayChi() {
        return ngayChi;
    }

    public void setNgayChi(String ngayChi) {
        this.ngayChi = ngayChi;
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

    public int getId_loaiChi() {
        return id_loaiChi;
    }

    public void setId_loaiChi(int id_loaiChi) {
        this.id_loaiChi = id_loaiChi;
    }

    public String getTen_loaiChi() {
        return ten_loaiChi;
    }

    public void setTen_loaiChi(String ten_loaiChi) {
        this.ten_loaiChi = ten_loaiChi;
    }
}
