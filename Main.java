import java.io.*;
import java.util.*;

class SinhVien {
    private String maSV;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSV, String hoTen, String ngaySinh) {
        this.maSV = maSV.trim();
        this.hoTen = chuanHoaHoTen(hoTen);
        this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
    }

    private String chuanHoaHoTen(String s) {
        s = s.trim().toLowerCase();

        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0)))
              .append(w.substring(1))
              .append(" ");
        }
        return sb.toString().trim();
    }

    private String chuanHoaNgaySinh(String s) {
        String[] parts = s.trim().split("/");
        int ngay = Integer.parseInt(parts[0]);
        int thang = Integer.parseInt(parts[1]);
        String nam = parts[2];
        return String.format("%02d/%02d/%s", ngay, thang, nam);
    }

    @Override
    public String toString() {
        return maSV + " " + hoTen + " " + ngaySinh;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = Integer.parseInt(sc.nextLine().trim());
        List<SinhVien> danhSach = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String maSV = sc.nextLine();
            String hoTen = sc.nextLine();
            String ngaySinh = sc.nextLine();
            danhSach.add(new SinhVien(maSV, hoTen, ngaySinh));
        }

        for (SinhVien sv : danhSach) {
            System.out.println(sv);
        }
        
        sc.close();
    }
}
