package quanlynhansu;

import java.util.ArrayList;

public class TruongPhong extends NhanVien {
	
	private ArrayList<NhanVienThuong> danhSachNhanVienDQ;

	public TruongPhong(int maSo, String hoTen, String soDienThoai, int soNgayLamViec, double luongMotNgay) {
		super(maSo, hoTen, soDienThoai, soNgayLamViec, luongMotNgay);
		this.danhSachNhanVienDQ = new ArrayList<>();
	}

	public void themNhanVienDQ(NhanVienThuong nv) {
		danhSachNhanVienDQ.add(nv);
		nv.setTruongPhongHienTai(this);
	}

	public void xoaNhanVienDQ(NhanVienThuong nv) {
		danhSachNhanVienDQ.remove(nv);
		nv.setTruongPhongHienTai(null);
	}

	public ArrayList<NhanVienThuong> getDanhSachNhanVienDQ() {
		return danhSachNhanVienDQ;
	}

	@Override
	public double tinhLuongThang() {
		return getLuongMotNgay() * getSoNgayLamViec();
	}

}