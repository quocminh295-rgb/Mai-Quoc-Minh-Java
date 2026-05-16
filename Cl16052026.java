public class Invoice {
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;

    // Constructor với logic kiểm tra giá trị không dương
    public Invoice(String partNumber, String partDescription, int quantity, double pricePerItem) {
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = (quantity > 0) ? quantity : 0;
        this.pricePerItem = (pricePerItem > 0.0) ? pricePerItem : 0.0;
    }

    // --- Getters và Setters ---
    public String getPartNumber() { return partNumber; }
    public void setPartNumber(String partNumber) { this.partNumber = partNumber; }

    public String getPartDescription() { return partDescription; }
    public void setPartDescription(String partDescription) { this.partDescription = partDescription; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = (quantity > 0) ? quantity : 0;
    }

    public double getPricePerItem() { return pricePerItem; }
    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = (pricePerItem > 0.0) ? pricePerItem : 0.0;
    }

    // Tính tổng tiền cho hóa đơn
    public double getInvoiceAmount() {
        return quantity * pricePerItem;
    }

    // Ghi đè toString để hiển thị dữ liệu đẹp mắt
    @Override
    public String toString() {
        return String.format("| %-10s | %-25s | %-8d | %-12.2f | %-15.2f |", 
                partNumber, partDescription, quantity, pricePerItem, getInvoiceAmount());
    }
}


import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceTest {
    private static ArrayList<Invoice> list = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ HÓA ĐƠN =====");
            System.out.println("1. Tạo 10 dữ liệu mẫu (Mock data)");
            System.out.println("2. Thêm hóa đơn mới (Check trùng mã)");
            System.out.println("3. Hiển thị toàn bộ danh sách");
            System.out.println("4. Sắp xếp (Mã MH tăng dần -> Số lượng)");
            System.out.println("5. Tìm kiếm theo Mã mặt hàng");
            System.out.println("6. Xóa hóa đơn theo Mã mặt hàng");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine(); // Xử lý trôi lệnh

            switch (choice) {
                case 1 -> generateData();
                case 2 -> addInvoice();
                case 3 -> printList();
                case 4 -> sortData();
                case 5 -> searchData();
                case 6 -> deleteData();
                case 0 -> System.out.println("Tạm biệt!");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void generateData() {
        list.add(new Invoice("MH05", "Chuột Logitech G102", 10, 400000));
        list.add(new Invoice("MH02", "Bàn phím Akko 3068", 5, 1200000));
        list.add(new Invoice("MH08", "Màn hình Dell UltraSharp", 2, 6500000));
        list.add(new Invoice("MH01", "Tai nghe Sony WH-1000XM4", 1, 5500000));
        list.add(new Invoice("MH10", "Cáp HDMI 2.1", 20, 150000));
        list.add(new Invoice("MH03", "Mainboard Asus ROG", 3, 4800000));
        list.add(new Invoice("MH07", "Nguồn Corsair 750W", 4, 2100000));
        list.add(new Invoice("MH04", "Vỏ máy tính MSI", 6, 1800000));
        list.add(new Invoice("MH09", "Loa Marshall Acton", 2, 4500000));
        list.add(new Invoice("MH02", "Bàn phím Akko (Lô cũ)", 3, 1100000));
        System.out.println("Đã thêm 10 dữ liệu mẫu.");
    }

    private static void addInvoice() {
        System.out.print("Nhập mã MH: ");
        String code = sc.nextLine();
        for (Invoice i : list) {
            if (i.getPartNumber().equalsIgnoreCase(code)) {
                System.out.println("Lỗi: Mã này đã tồn tại!");
                return;
            }
        }
        System.out.print("Mô tả: "); String desc = sc.nextLine();
        System.out.print("Số lượng: "); int q = sc.nextInt();
        System.out.print("Giá: "); double p = sc.nextDouble();
        list.add(new Invoice(code, desc, q, p));
        System.out.println("Thêm thành công.");
    }

    private static void printList() {
        if (list.isEmpty()) { System.out.println("Danh sách trống."); return; }
        System.out.println("-".repeat(80));
        System.out.printf("| %-10s | %-25s | %-8s | %-12s | %-15s |\n", "Mã MH", "Mô tả", "SL", "Giá", "Thành tiền");
        System.out.println("-".repeat(80));
        for (Invoice i : list) System.out.println(i);
        System.out.println("-".repeat(80));
    }

    private static void sortData() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Invoice a = list.get(j), b = list.get(j + 1);
                int cmp = a.getPartNumber().compareToIgnoreCase(b.getPartNumber());
                if (cmp > 0 || (cmp == 0 && a.getQuantity() > b.getQuantity())) {
                    list.set(j, b); list.set(j + 1, a);
                }
            }
        }
        System.out.println("Đã sắp xếp xong.");
    }

    private static void searchData() {
        System.out.print("Nhập mã cần tìm: ");
        String code = sc.nextLine();
        for (Invoice i : list) {
            if (i.getPartNumber().equalsIgnoreCase(code)) {
                System.out.println("Kết quả: " + i);
                return;
            }
        }
        System.out.println("Không tìm thấy.");
    }

    private static void deleteData() {
        System.out.print("Nhập mã cần xóa: ");
        String code = sc.nextLine();
        boolean removed = list.removeIf(i -> i.getPartNumber().equalsIgnoreCase(code));
        System.out.println(removed ? "Đã xóa." : "Mã không tồn tại.");
    }
}