package pack_001;

public class Cl13052026 {
	public static int timUSCLN(int a, int b) {
	    
	    a = Math.abs(a);
	    b = Math.abs(b);
	    
	    
	    if (a == 0) return b;
	    if (b == 0) return a;

	    while (b != 0) {
	        int soDu = a % b;
	        a = b;
	        b = soDu;
	    }
	    return a;
	}
	
	public static boolean laSoHoanThien(int n) {
	    // Các số nhỏ hơn hoặc bằng 1 không được coi là số hoàn thiện
	    if (n <= 1) return false;

	    int tongCacUoc = 0;
	    // Duyệt đến n/2 vì không có ước số nào (ngoại trừ chính nó) lớn hơn n/2
	    for (int i = 1; i <= n / 2; i++) {
	        if (n % i == 0) {
	            tongCacUoc += i;
	        }
	    }

	    // So sánh tổng tìm được với số ban đầu
	    return tongCacUoc == n;
	}
	
	public static boolean kiemTraChuSoToanChan(int n) {
	    // Trường hợp đặc biệt nếu số là 0
	    if (n == 0) return true;

	    // Chuyển sang số dương nếu n âm (tăng tính tổng quát)
	    n = Math.abs(n);

	    while (n > 0) {
	        int chuSoCuoi = n % 10;
	        // Kiểm tra xem chữ số cuối có lẻ không
	        if (chuSoCuoi % 2 != 0) {
	            // Nếu có bất kỳ chữ số nào lẻ, trả về false ngay lập tức
	            return false;
	        }
	        // Loại bỏ chữ số cuối đã kiểm tra
	        n = n / 10;
	    }

	    // Nếu đã kiểm tra hết các chữ số mà không gặp số lẻ nào
	    return true;
	}
	
	public static double tinhTongChuoi(double x, int n) {
	    double tong = 0;
	    // Số hạng đầu tiên tương ứng với i = 0 là: x^(2*0 + 1) / (2*0 + 1)! = x/1! = x
	    double soHangHienTai = x;

	    for (int i = 0; i <= n; i++) {
	        // Cộng số hạng hiện tại vào tổng
	        tong += soHangHienTai;

	        // Tính số hạng tiếp theo cho vòng lặp kế tiếp
	        // Mẫu số tiếp theo tăng từ (2i+1)! lên (2i+3)!
	        // Nghĩa là nhân thêm (2i+2) và (2i+3)
	        double mauSoPhu = (2 * i + 2) * (2 * i + 3);
	        soHangHienTai = soHangHienTai * (x * x) / mauSoPhu;
	    }

	    return tong;
	}
	
	
	
	
	//BT2
	public static void NegativeNumberInStringsManual(String str) {
	    for (int i = 0; i < str.length() - 1; i++) {
	        
	        if (str.charAt(i) == '-' && Character.isDigit(str.charAt(i + 1))) {
	            StringBuilder negativeNum = new StringBuilder("-");
	            
	            // Duyệt tiếp để lấy toàn bộ các chữ số đi kèm sau dấu '-'
	            int j = i + 1;
	            while (j < str.length() && Character.isDigit(str.charAt(j))) {
	                negativeNum.append(str.charAt(j));
	                j++;
	            }
	            
	            System.out.print(negativeNum.toString() + " ");
	            
	            
	            i = j - 1;
	        }
	    }
	}
	
	
//BT3
	public abstract class Hinh {
	    protected String mau; // Thuộc tính màu sắc kiểu String

	    // Phương thức khởi tạo đối tượng
	    public Hinh(String mau) {
	        this.mau = mau;
	    }

	    // Các phương thức trừu tượng để các lớp con ghi đè (override)
	    public abstract double TinhDienTich(); // Tính diện tích
	    public abstract double TinhChuVi();    // Tính chu vi

	    // Phương thức trả về thông tin đối tượng dưới dạng String
	    public String LayThongTin() {
	        return "Màu sắc: " + mau;
	    }
	}
	
	public class HinhChuNhat extends Hinh {
	    protected double chieudai;  // Chiều dài kiểu double
	    protected double chieurong; // Chiều rộng kiểu double

	    public HinhChuNhat(String mau, double chieudai, double chieurong) {
	        super(mau);
	        this.chieudai = chieudai;
	        this.chieurong = chieurong;
	    }

	    @Override
	    public double TinhDienTich() {
	        return chieudai * chieurong;
	    }

	    @Override
	    public double TinhChuVi() {
	        return 2 * (chieudai + chieurong);
	    }

	    @Override
	    public String LayThongTin() {
	        return super.LayThongTin() + ", Hình Chữ Nhật [Dài: " + chieudai + ", Rộng: " + chieurong + "]";
	    }
	}
	public class HinhVuong extends HinhChuNhat {
	    public HinhVuong(String mau, double canh) {
	        // Gọi constructor của lớp cha (HinhChuNhat) với dài = rộng = canh
	        super(mau, canh, canh);
	    }

	    @Override
	    public String LayThongTin() {
	        return "Màu sắc: " + mau + ", Hình Vuông [Cạnh: " + chieudai + "]";
	    }
	}
	public class HinhTron extends Hinh {
	    private double bankinh;

	    public HinhTron(String mau, double bankinh) {
	        super(mau);
	        this.bankinh = bankinh;
	    }

	    @Override
	    public double TinhDienTich() {
	        return Math.PI * bankinh * bankinh;
	    }

	    @Override
	    public double TinhChuVi() {
	        return 2 * Math.PI * bankinh;
	    }

	    @Override
	    public String LayThongTin() {
	        return super.LayThongTin() + ", Hình Tròn [Bán kính: " + bankinh + "]";
	    }
	}
	
	public class Main {
	    public static void main(String[] args) {
	        Hinh hcn = new HinhChuNhat("Đỏ", 5, 3);
	        Hinh hv = new HinhVuong("Xanh", 4);
	        Hinh ht = new HinhTron("Vàng", 2.5);

	        inThongTin(hcn);
	        inThongTin(hv);
	        inThongTin(ht);
	    }

	    public static void inThongTin(Hinh h) {
	        System.out.println(h.LayThongTin());
	        System.out.println("- Diện tích: " + h.TinhDienTich());
	        System.out.println("- Chu vi: " + h.TinhChuVi());
	        System.out.println("---------------------------");
	    }
	}
	
	
	//BT3 tt
	public class HinhTron extends Hinh {
	    private double bankinh; // Thuộc tính bổ sung theo yêu cầu

	    // Phương thức khởi tạo đúng với tham số trong image_55845b.png
	    public HinhTron(String mau, double bankinh) {
	        super(mau);
	        this.bankinh = bankinh;
	    }

	    @Override
	    public double TinhDienTich() {
	        return Math.PI * bankinh * bankinh;
	    }

	    @Override
	    public double TinhChuVi() {
	        return 2 * Math.PI * bankinh;
	    }

	    @Override
	    public String LayThongTin() {
	        return super.LayThongTin() + ", Loại: Hình Tròn, Bán kính: " + bankinh;
	    }
	}
	public class HinhVuong extends HinhChuNhat {
	    // Kế thừa từ HinhChuNhat và sử dụng tham số 'bankinh' làm cạnh
	    public HinhVuong(String mau, double bankinh) {
	        super(mau, bankinh, bankinh);
	    }

	    @Override
	    public String LayThongTin() {
	        // Ghi đè để hiển thị đúng tên loại hình
	        return "Màu sắc: " + mau + ", Loại: Hình Vuông, Cạnh: " + chieudai;
	    }
	}
	public class KiemTraHinh {
	    public static void main(String[] args) {
	        // 1. Tạo 3 đối tượng theo đúng yêu cầu tham số trong ảnh
	        Hinh hcn = new HinhChuNhat("Đỏ", 10.0, 5.0);
	        Hinh ht = new HinhTron("Xanh dương", 4.0);
	        Hinh hv = new HinhVuong("Vàng", 6.0);

	        // 2. Lưu các đối tượng vào mảng để duyệt (tận dụng tính Đa hình)
	        Hinh[] danhSachHinh = {hcn, ht, hv};

	        System.out.println("===== KẾT QUẢ KIỂM TRA HÌNH HỌC =====");
	        
	        // 3. Tính toán và hiển thị thông tin ra console
	        for (Hinh h : danhSachHinh) {
	            System.out.println(h.LayThongTin());
	            System.out.printf("- Chu vi: %.2f\n", h.TinhChuVi());
	            System.out.printf("- Diện tích: %.2f\n", h.TinhDienTich());
	            System.out.println("------------------------------------");
	        }
	    }
	}
}





















