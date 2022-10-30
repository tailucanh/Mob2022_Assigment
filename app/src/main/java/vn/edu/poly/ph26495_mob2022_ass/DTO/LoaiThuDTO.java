package vn.edu.poly.ph26495_mob2022_ass.DTO;

public class LoaiThuDTO {
    private int id_loaiThu;
    private String ten_loaiThu;

    public static final String TB_NAME = "tb_loaiThu";
    public static final String COL_ID = "id_loaiThu";
    public static final String COL_TEN_LOAI_THU = "ten_loaiThu";

    public LoaiThuDTO() {
    }

    public LoaiThuDTO(int id_loaiThu, String ten_loaiThu) {
        this.id_loaiThu = id_loaiThu;
        this.ten_loaiThu = ten_loaiThu;
    }

    public int getId_loaiThu() {
        return id_loaiThu;
    }

    public void setId_loaiThu(int id_loaiThu) {
        this.id_loaiThu = id_loaiThu;
    }

    public String getTen_Loaithu() {
        return ten_loaiThu;
    }

    public void setTen_Loaithu(String ten_Loaithu) {
        this.ten_loaiThu = ten_Loaithu;
    }
}
