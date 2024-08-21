package quanlynhansu;

public class NhanVienThuong extends NhanVien {
	
	private TruongPhong truongPhongHienTai;

	public NhanVienThuong(int maSo, String hoTen, String soDienThoai, int soNgayLamViec, double luongMotNgay) {
		super(maSo, hoTen, soDienThoai, soNgayLamViec, luongMotNgay);
	}

	@Override
	public double tinhLuongThang() {
		return getLuongMotNgay() * getSoNgayLamViec();
	}

	public TruongPhong getTruongPhongHienTai() {
		return truongPhongHienTai;
	}

	public void setTruongPhongHienTai(TruongPhong truongPhongHienTai) {
		this.truongPhongHienTai = truongPhongHienTai;
	}
}
