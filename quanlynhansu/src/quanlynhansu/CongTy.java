package quanlynhansu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CongTy {
	private String tenCongTy;
	private String maSoThue;
	private double doanhThuThang;
	private ArrayList<NhanVien> danhSachNV;

	public CongTy(String tenCongTy, String maSoThue, double doanhThuThang) {
		this.tenCongTy = tenCongTy;
		this.maSoThue = maSoThue;
		this.doanhThuThang = doanhThuThang;
		this.danhSachNV = new ArrayList<>();
	}

	public void themNhanVien(NhanVien nv) {
		danhSachNV.add(nv);
	}

	public void xoaNhanVien(int maSo) {
		NhanVien nhanVien = timNhanVien(maSo);
		if (nhanVien != null) {
			if (nhanVien instanceof TruongPhong) {
				TruongPhong truongPhong = (TruongPhong) nhanVien;
				for (NhanVienThuong nvThuong : truongPhong.getDanhSachNhanVienDQ()) {
					nvThuong.setTruongPhongHienTai(null);
				}
			}
			danhSachNV.remove(nhanVien);
			System.out.println("Nhân sự đã được xóa");
		} else {
			System.out.println("Không tìm thấy nhân sự với mã số này.");
		}
	}

	public void xoaNhanVienKhoiTruongPhong(int maSoNV) {
		NhanVien nv = timNhanVien(maSoNV);
		if (nv instanceof NhanVienThuong) {
			NhanVienThuong nvThuong = (NhanVienThuong) nv;
			TruongPhong tp = nvThuong.getTruongPhongHienTai();

			if (tp != null) {
				tp.xoaNhanVienDQ(nvThuong);
				System.out.println("Nhân viên đã được xóa khỏi danh sách nshân viên dưới quyền của trưởng phòng.");
			} else {
				System.out.println("Nhân viên không có trưởng phòng.");
			}
		}
	}

	public NhanVien timNhanVien(int maSo) {
		for (NhanVien nv : danhSachNV) {
			if (nv.getMaSo() == maSo) {
				return nv;
			}
		}
		return null;
	}

	public void xuatThongTin() {
		for (NhanVien nv : danhSachNV) {
			if (nv instanceof GiamDoc) {
				GiamDoc gd = (GiamDoc) nv;
				System.out.printf(
						"Giám đốc - Mã số: %d - Họ tên: %s - Số điện thoại: %s - Lương một ngày: %.2f - Số ngày làm việc: %s - Cổ phần: %.2f%%, Lương tháng: %.2f\n",
						gd.getMaSo(), gd.getHoTen(), gd.getSoDienThoai(), gd.getLuongMotNgay(), gd.getSoNgayLamViec(),
						gd.getCoPhan(), gd.tinhLuongThang());
			} else if (nv instanceof TruongPhong) {
				TruongPhong tp = (TruongPhong) nv;
				System.out.printf(
						"Trưởng phòng - Mã số: %d - Họ tên: %s - Số điện thoại: %s - Lương một ngày: %.2f - Số ngày làm việc: %s - Lương tháng: %.2f\n",
						tp.getMaSo(), tp.getHoTen(), tp.getSoDienThoai(), tp.getLuongMotNgay(), tp.getSoNgayLamViec(),
						tp.tinhLuongThang());
			} else {
				System.out.printf(
						"Nhân viên - Mã số: %d - Họ tên: %s - Số điện thoại: %s - Lương một ngày: %.2f - Số ngày làm việc: %s - Lương tháng: %.2f\n",
						nv.getMaSo(), nv.getHoTen(), nv.getSoDienThoai(), nv.getLuongMotNgay(), nv.getSoNgayLamViec(),
						nv.tinhLuongThang());
			}
		}
	}

	public double tinhTongLuongThang() {
		double tongLuong = 0;
		for (NhanVien nv : danhSachNV) {
			tongLuong += nv.tinhLuongThang();
		}
		return tongLuong;
	}

	public NhanVienThuong timNhanVienThuongLuongCaoNhat() {
		NhanVienThuong nvLuongCaoNhat = null;
		for (NhanVien nv : danhSachNV) {
			if (nv instanceof NhanVienThuong) {
				if (nvLuongCaoNhat == null || nv.tinhLuongThang() > nvLuongCaoNhat.tinhLuongThang()) {
					nvLuongCaoNhat = (NhanVienThuong) nv;
				}
			}
		}
		return nvLuongCaoNhat;
	}

	public TruongPhong timTruongPhongCoNhieuNhanVienNhat() {
		TruongPhong tpMax = null;
		int maxSoNhanVien = 0;

		for (NhanVien nv : danhSachNV) {
			if (nv instanceof TruongPhong) {
				TruongPhong tp = (TruongPhong) nv;
				int soNhanVien = tp.getDanhSachNhanVienDQ().size();
				if (soNhanVien > maxSoNhanVien) {
					maxSoNhanVien = soNhanVien;
					tpMax = tp;
				}
			}
		}
		return tpMax;
	}

	public void sapXepNhanVienTheoTenCuoi() {
		Collections.sort(danhSachNV, new Comparator<NhanVien>() {
			@Override
			public int compare(NhanVien nv1, NhanVien nv2) {
				char lastChar1 = nv1.getHoTen().charAt(nv1.getHoTen().length() - 1);
				char lastChar2 = nv2.getHoTen().charAt(nv2.getHoTen().length() - 1);
				return Character.compare(lastChar1, lastChar2);
			}
		});
	}

	public void sapXepNhanVienTheoLuongGiamDan() {
		Collections.sort(danhSachNV, new Comparator<NhanVien>() {
			@Override
			public int compare(NhanVien nv1, NhanVien nv2) {
				return Double.compare(nv2.tinhLuongThang(), nv1.tinhLuongThang());
			}
		});
	}

	public GiamDoc timGiamDocCoCoPhanNhieuNhat() {
		GiamDoc gdMax = null;
		for (NhanVien nv : danhSachNV) {
			if (nv instanceof GiamDoc) {
				GiamDoc gd = (GiamDoc) nv;
				if (gdMax == null || gd.getCoPhan() > gdMax.getCoPhan()) {
					gdMax = gd;
				}
			}
		}
		return gdMax;
	}

	public void tinhVaXuatThuNhapGiamDoc() {
		double tongLuongThang = tinhTongLuongThang();
		double loiNhuanCongTy = doanhThuThang - tongLuongThang;

		for (NhanVien nv : danhSachNV) {
			if (nv instanceof GiamDoc) {
				GiamDoc gd = (GiamDoc) nv;
				double thuNhap = gd.tinhThuNhap(loiNhuanCongTy);
				System.out.printf("Giám đốc: %s, Tổng thu nhập: %.2f\n", gd.getHoTen(), thuNhap);
			}
		}
	}

	public ArrayList<NhanVien> getDanhSachNV() {
		return danhSachNV;
	}

	public void setDanhSachNV(ArrayList<NhanVien> danhSachNV) {
		this.danhSachNV = danhSachNV;
	}

	public void xuatThongTinCongTy() {
		System.out.println("\nCông ty " + this.tenCongTy);
		System.out.println("Mã số thuế: " + this.maSoThue);
		System.out.println("Doanh thu tháng: " + this.doanhThuThang);
	}
}