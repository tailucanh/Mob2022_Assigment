package vn.edu.poly.ph26495_mob2022_ass.DTO;

public class LoaiChiDTO {
    private int id_loaiChi;
    private String ten_LoaiChi;

    public static final String TB_NAME = "tb_loaiChi";
    public static final String COL_ID = "id_loaiChi";
    public static final String COL_TEN_LOAI_CHI = "ten_LoaiChi";

    public LoaiChiDTO() {
    }

    public LoaiChiDTO(int id_loaiChi, String ten_LoaiChi) {
        this.id_loaiChi = id_loaiChi;
        this.ten_LoaiChi = ten_LoaiChi;
    }

    public int getId_loaiChi() {
        return id_loaiChi;
    }

    public void setId_loaiChi(int id_loaiChi) {
        this.id_loaiChi = id_loaiChi;
    }

    public String getTen_LoaiChi() {
        return ten_LoaiChi;
    }

    public void setTen_LoaiChi(String ten_LoaiChi) {
        this.ten_LoaiChi = ten_LoaiChi;
    }
}
