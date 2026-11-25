import java.io.*;
import java.util.*;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

abstract class SinhVien {
    protected String maSV;
    protected String maHe;
    protected int soTinChi;
    protected static final long DON_GIA = 450000L;

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
    }

    public String getMaSV() {
        return maSV;
    }

    public abstract long hocPhi();

    @Override
    public String toString() {
        return maSV + " " + maHe + " " + hocPhi();
    }
}

class SinhVienCQ extends SinhVien {
    public SinhVienCQ(String maSV, int soTinChi) {
        super(maSV, "CQ", soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * DON_GIA;
    }
}

class SinhVienCLC extends SinhVien {
    public SinhVienCLC(String maSV, int soTinChi) {
        super(maSV, "CLC", soTinChi);
    }

    @Override
    public long hocPhi() {
        return (soTinChi * DON_GIA * 3) / 2;
    }
}

class SinhVienLT extends SinhVien {
    public SinhVienLT(String maSV, int soTinChi) {
        super(maSV, "LT", soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * DON_GIA + 2000000L;
    }
}

class QuanLyHocPhi {
    private ArrayList<SinhVien> danhSach;

    public QuanLyHocPhi() {
        this.danhSach = new ArrayList<>();
    }

    public void themSinhVien(String maHe, String maSV, String soTinChiStr) throws DuLieuKhongHopLeException {
        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException("Ma he khong hop le");
        }

        int soTinChi;
        try {
            soTinChi = Integer.parseInt(soTinChiStr);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("So tin chi khong phai so nguyen");
        }

        if (soTinChi < 1 || soTinChi > 30) {
            throw new DuLieuKhongHopLeException("So tin chi ngoai khoang cho phep");
        }

        for (SinhVien sv : danhSach) {
            if (sv.getMaSV().equals(maSV)) {
                throw new DuLieuKhongHopLeException("Trung ma sinh vien");
            }
        }

        switch (maHe) {
            case "CQ":
                danhSach.add(new SinhVienCQ(maSV, soTinChi));
                break;
            case "CLC":
                danhSach.add(new SinhVienCLC(maSV, soTinChi));
                break;
            case "LT":
                danhSach.add(new SinhVienLT(maSV, soTinChi));
                break;
        }
    }

    public void sapXep() {
        danhSach.sort((sv1, sv2) -> {
            int cmp = Long.compare(sv2.hocPhi(), sv1.hocPhi());
            if (cmp != 0) {
                return cmp;
            }
            return sv1.getMaSV().compareTo(sv2.getMaSV());
        });
    }

    public long tinhTongHocPhi() {
        long tong = 0;
        for (SinhVien sv : danhSach) {
            tong += sv.hocPhi();
        }
        return tong;
    }

    public void inKetQua() {
        for (SinhVien sv : danhSach) {
            System.out.println(sv);
        }
        System.out.println("TONG " + tinhTongHocPhi());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;

        int n = Integer.parseInt(sc.nextLine().trim());
        QuanLyHocPhi ql = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split("\\s+");
            String maHe = parts[0];
            String maSV = parts[1];
            String soTinChiStr = parts[2];

            try {
                ql.themSinhVien(maHe, maSV, soTinChiStr);
            } catch (DuLieuKhongHopLeException e) {
                System.out.println("INVALID " + maSV);
            }
        }

        ql.sapXep();
        ql.inKetQua();

        sc.close();
    }
}