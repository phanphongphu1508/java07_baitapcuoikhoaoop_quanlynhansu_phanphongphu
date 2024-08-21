package quanlynhansu;

import java.util.Scanner;

public class Application {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Scanner banPhim = new Scanner(System.in);
		CongTy congTy = null;
		while (true) {
			menu();
			System.out.print("Lựa chọn: ");
			int luaChon = banPhim.nextInt();
			banPhim.nextLine();
			switch (luaChon) {
			case 1:			// Nhập thông tin công ty
				System.out.print("Nhập tên công ty: ");
				String tenCongTy = banPhim.nextLine();
				System.out.print("Nhập mã số thuế: ");
				String maSoThue = banPhim.nextLine();
				System.out.print("Nhập doanh thu tháng: ");
				double doanhThuThang = banPhim.nextDouble();
				banPhim.nextLine();
				congTy = new CongTy(tenCongTy, maSoThue, doanhThuThang);

				// Giám đốc
				GiamDoc giamDoc = new GiamDoc(1, "Phan Phong Phú", "0333979979", 30, 300, 5);
				GiamDoc giamDoc1 = new GiamDoc(2, "Phương Ngọc Vân", "0868393393", 28, 300, 9);
				congTy.themNhanVien(giamDoc);
				congTy.themNhanVien(giamDoc1);

				// Trưởng phòng
				TruongPhong truongPhong = new TruongPhong(4, "Phan Phú Quý", "0983995555", 30, 200);
				TruongPhong truongPhong1 = new TruongPhong(3, "Bùi Văn Thiện", "0964500123", 20, 200);
				congTy.themNhanVien(truongPhong);
				congTy.themNhanVien(truongPhong1);

				// Nhân viên
				NhanVienThuong nhanVien = new NhanVienThuong(5, "Đặng Hoàng Phúc", "0969368868", 20, 100);
				NhanVienThuong nhanVien1 = new NhanVienThuong(6, "Huỳnh Trí Cường", "0988131618", 22, 100);
				NhanVienThuong nhanVien2 = new NhanVienThuong(7, "Hàng Minh Đạt", "0986016886", 28, 100);
				NhanVienThuong nhanVien3 = new NhanVienThuong(8, "Lê Hữu Đạt", "0912345680", 25, 100);
				congTy.themNhanVien(nhanVien);
				congTy.themNhanVien(nhanVien1);
				congTy.themNhanVien(nhanVien2);
				congTy.themNhanVien(nhanVien3);

				// Nhân viên dưới quyền
				truongPhong.themNhanVienDQ(nhanVien);
				truongPhong.themNhanVienDQ(nhanVien1);

				System.out.println("Thông tin công ty và dữ liệu nhân viên đã được nhập thành công.");
				break;
			case 2:			// Phân bổ nhân viên vào trưởng phòng
				if (congTy != null) {
					System.out.print("Nhập mã trưởng phòng: ");
					int maSoTP = banPhim.nextInt();
					banPhim.nextLine();
					TruongPhong tp1 = (TruongPhong) congTy.timNhanVien(maSoTP);

					if (tp1 != null) {
						System.out.print("Nhập mã nhân viên: ");
						int maSoNV = banPhim.nextInt();
						banPhim.nextLine();
						NhanVien nv = congTy.timNhanVien(maSoNV);

						if (nv != null && nv instanceof NhanVienThuong) {
							NhanVienThuong nvThuong = (NhanVienThuong) nv;
							if (nvThuong.getTruongPhongHienTai() == null) {
								tp1.themNhanVienDQ(nvThuong);
								nvThuong.setTruongPhongHienTai(tp1);
								System.out.println("Nhân viên đã được phân bổ vào trưởng phòng.");
							} else {
								System.out.println("Nhân viên đã được phân bổ vào trưởng phòng khác.");
							}
						} else {
							System.out.println("Không tìm thấy nhân viên với mã số này.");
						}
					} else {
						System.out.println("Không tìm thấy trưởng phòng với mã số này.");
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 3:			// Thêm, xóa nhân sự
				NhanVien nv = null;
				boolean kiemTra = true;
				if (congTy != null) {
					while (kiemTra) {
						System.out.println("\n----------MENU----------");
						System.out.println("1. Thêm nhân sự");
						System.out.println("2. Xóa nhân sự");
						System.out.println("0. Thoát");
						System.out.print("Lựa chọn: ");
						luaChon = banPhim.nextInt();
						switch (luaChon) {
						case 0:
							kiemTra = false;
							break;
						case 1:
							System.out.println("\n----------MENU----------");
							System.out.println("1. Thêm giám đốc");
							System.out.println("2. Thêm Trưởng phòng");
							System.out.println("3. Thêm nhân viên thường");
							System.out.print("Lựa chọn: ");
							luaChon = banPhim.nextInt();
							
							switch (luaChon) {
							case 1:
								double coPhan;
								System.out.print("Nhập mã nhân viên: ");
								int maSoGiamDoc = banPhim.nextInt();
								banPhim.nextLine();

								System.out.print("Nhập họ tên: ");
								String hoTenGiamDoc = banPhim.nextLine();
								
								System.out.print("Nhập số điện thoại: ");
								String soDienThoaiGiamDoc = banPhim.nextLine();
								
								System.out.print("Nhập số ngày làm việc: ");
								int soNgayLamViecGiamDoc = banPhim.nextInt();
								banPhim.nextLine();
								
								do {
									System.out.print("Nhập cổ phần (%): ");
									coPhan = banPhim.nextDouble();
									if (coPhan <= 0 || coPhan > 100) {
										System.out.println("Cổ phần phải lớn hơn 0 và nhỏ hơn 100.");
									}
								} while (coPhan <= 0 || coPhan > 100);

								nv = new GiamDoc(maSoGiamDoc, hoTenGiamDoc, soDienThoaiGiamDoc, soNgayLamViecGiamDoc, 300, coPhan);
								congTy.themNhanVien(nv);
								System.out.println("Nhân sự đã được thêm.");
								break;
							case 2:
								System.out.print("Nhập mã nhân viên: ");
								int maSoTruongPhong = banPhim.nextInt();
								banPhim.nextLine();
							
								System.out.print("Nhập họ tên: ");
								String hoTenTruongPhong = banPhim.nextLine();
								
								System.out.print("Nhập số điện thoại: ");
								String soDienThoaiTruongPhong = banPhim.nextLine();
								
								System.out.print("Nhập số ngày làm việc: ");
								int soNgayLamViecTruongPhong = banPhim.nextInt();
								banPhim.nextLine();
								
								nv = new TruongPhong(maSoTruongPhong, hoTenTruongPhong, soDienThoaiTruongPhong, soNgayLamViecTruongPhong, 200);
								congTy.themNhanVien(nv);
								System.out.println("Nhân sự đã được thêm.");
								break;
							case 3:
								System.out.print("Nhập mã nhân viên: ");
								int maSoNV = banPhim.nextInt();
								banPhim.nextLine();
								
								System.out.print("Nhập họ tên: ");
								String hoTenNV = banPhim.nextLine();
								
								System.out.print("Nhập số điện thoại: ");
								String soDienThoaiNV = banPhim.nextLine();
								
								System.out.print("Nhập số ngày làm việc: ");
								int soNgayLamViecNV = banPhim.nextInt();
								banPhim.nextLine();
								
								nv = new NhanVienThuong(maSoNV, hoTenNV, soDienThoaiNV, soNgayLamViecNV, 100);
								congTy.themNhanVien(nv);
								System.out.println("Nhân sự đã được thêm.");
								break;
							}
							break;
						case 2:
							System.out.print("Nhập mã nhân sự cần xóa: ");
							int maSoNV = banPhim.nextInt();
							banPhim.nextLine();
							congTy.xoaNhanVien(maSoNV);
							banPhim.nextLine();
							congTy.xoaNhanVienKhoiTruongPhong(maSoNV);
							break;
						default:
							System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
							break;
						}
					}
					break;
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 4:			// Xuất thông tin toàn bộ người trong công ty
				congTy.xuatThongTinCongTy();
				if (congTy != null) {
					congTy.xuatThongTin();
					System.out.println("\nThông tin chi tiết về các nhân viên thuộc trưởng phòng:");
					for (NhanVien nhanVienThuocTruongPhong : congTy.getDanhSachNV()) {
						if (nhanVienThuocTruongPhong instanceof NhanVienThuong) {
							NhanVienThuong nvThuong = (NhanVienThuong) nhanVienThuocTruongPhong;
							TruongPhong truongPhong3 = nvThuong.getTruongPhongHienTai();
							if (truongPhong3 != null) {
								System.out.printf("Nhân viên - Mã số: %d - Tên: %s thuộc trưởng phòng - Mã số: %d - Tên: %s.\n",
										nvThuong.getMaSo(), nvThuong.getHoTen(), truongPhong3.getMaSo(), truongPhong3.getHoTen());
							} else {
								System.out.printf("Nhân viên - Mã số: %d - Tên: %s chưa được phân bổ vào trưởng phòng nào.\n",
										nvThuong.getMaSo(), nvThuong.getHoTen());
							}
						}
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 5:			// Tính và xuất tổng lương cho toàn bộ nhân viên công ty
				congTy.xuatThongTinCongTy();
				if (congTy != null) {
					
					double tongLuong = congTy.tinhTongLuongThang();
					System.out.println("Tổng lương toàn nhân viên công ty: " + tongLuong);
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 6:			// Tìm nhân viên thường có lương cao nhất
				if (congTy != null) {
					NhanVienThuong nvLuongCaoNhat = congTy.timNhanVienThuongLuongCaoNhat();
					if (nvLuongCaoNhat != null) {
						System.out.printf(
								"Nhân viên có lương cao nhất:\n" + "Mã số: %d\n" + "Họ tên: %s\n"
										+ "Số điện thoại: %s\n" + "Số ngày làm việc: %d\n" + "Lương một ngày: %.2f\n"
										+ "Lương tháng: %.2f\n",
								nvLuongCaoNhat.getMaSo(), nvLuongCaoNhat.getHoTen(), nvLuongCaoNhat.getSoDienThoai(),
								nvLuongCaoNhat.getSoNgayLamViec(), nvLuongCaoNhat.getLuongMotNgay(),
								nvLuongCaoNhat.tinhLuongThang());
					} else {
						System.out.println("Không có nhân viên thường trong công ty.");
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 7:			// Tìm trưởng phòng có nhiều nhân viên nhất
				if (congTy != null) {
					TruongPhong tpMax = congTy.timTruongPhongCoNhieuNhanVienNhat();
					if (tpMax != null) {
						System.out.println("Trưởng phòng có nhiều nhân viên nhất: " + tpMax.getHoTen());
						System.out.println("Số lượng nhân viên dưới quyền: " + tpMax.getDanhSachNhanVienDQ().size());
					} else {
						System.out.println("Công ty không có trưởng phòng nào.");
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 8:			// Sắp xếp nhân viên theo tên ABC
				congTy.xuatThongTinCongTy();
				if (congTy != null) {
					congTy.sapXepNhanVienTheoTenCuoi();
					System.out.println("Tên nhân viên được sắp xếp theo thứ tự abc.");
					congTy.xuatThongTin();
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 9:			// Sắp xếp nhân viên theo mức lương giảm dần
				congTy.xuatThongTinCongTy();
				if (congTy != null) {
					congTy.sapXepNhanVienTheoLuongGiamDan();
					System.out.println("Nhân viên đã được sắp xếp theo thứ tự lương giảm dần.");
					congTy.xuatThongTin();

				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 10:		// Tìm giám đốc có cổ phần nhiều nhất
				if (congTy != null) {
					GiamDoc gdMax = congTy.timGiamDocCoCoPhanNhieuNhat();
					if (gdMax != null) {
						System.out.println("Giám đốc có cổ phần nhiều nhất: " + gdMax);
						System.out.println("Số cổ phần: " + gdMax.getCoPhan() + "%");
					} else {
						System.out.println("Công ty không có giám đốc nào.");
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 11:		// Tính và xất thu nập của giám đốc
				if (congTy != null) {
					
					congTy.tinhVaXuatThuNhapGiamDoc();
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;
			case 0:			// Dừng chương trình
				System.out.println("Đã dừng chương trình quản lý nhân sự.");
				banPhim.close();
				return;
			default:
				System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
			}
		}
	}

	public static void menu() {
		System.out.println("\n------------------------- MENU -------------------------");
		System.out.println("1. Nhập thông tin công ty");
		System.out.println("2. Phân bổ nhân viên vào trưởng phòng");
		System.out.println("3. Thêm, xóa nhân sự");
		System.out.println("4. Xuất thông tin toàn bộ người trong công ty");
		System.out.println("5. Tính và xuất tổng lương cho toàn công ty");
		System.out.println("6. Tìm nhân viên thường có lương cao nhất");
		System.out.println("7. Tìm trưởng phòng có số lượng nhân viên dưới quyền nhiều nhất");
		System.out.println("8. Sắp xếp nhân viên toàn công ty theo thứ tự abc");
		System.out.println("9. Sắp xếp nhân viên toàn công ty theo thứ tự lương giảm dần");
		System.out.println("10. Tìm giám đốc có số lượng cổ phần nhiều nhất");
		System.out.println("11. Tính và xuất tổng thu nhập của từng giám đốc");
		System.out.println("0. Thoát");
	}
}