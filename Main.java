import java.io.*;
import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class MienGiamCS implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return (hocPhi * 50) / 100;
    }
}

class MienGiamHN implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return (hocPhi * 70) / 100;
    }
}

class MienGiamHB implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3000000L);
    }
}

abstract class SinhVien {
    protected String maSV;
    protected String maHe;
    protected int soTinChi;
    protected MienGiam mienGiam;
    protected static final long DON_GIA = 450000L;

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMaHe() {
        return maHe;
    }

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public abstract long hocPhi();

    public long soTienGiam() {
        if (mienGiam == null) {
            return 0L;
        }
        return mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - soTienGiam();
    }

    @Override
    public String toString() {
        return maSV + " " + maHe + " " + hocPhi() + " " + soTienGiam() + " " + phaiNop();
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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        Map<String, SinhVien> danhSach = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            switch (maHe) {
                case "CQ":
                    danhSach.put(maSV, new SinhVienCQ(maSV, soTinChi));
                    break;
                case "CLC":
                    danhSach.put(maSV, new SinhVienCLC(maSV, soTinChi));
                    break;
                case "LT":
                    danhSach.put(maSV, new SinhVienLT(maSV, soTinChi));
                    break;
            }
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            String maSV = sc.next();
            String maCS = sc.next();

            SinhVien sv = danhSach.get(maSV);
            if (sv == null) {
                System.out.println("NOT FOUND " + maSV);
            } else {
                switch (maCS) {
                    case "CS":
                        sv.setMienGiam(new MienGiamCS());
                        break;
                    case "HN":
                        sv.setMienGiam(new MienGiamHN());
                        break;
                    case "HB":
                        sv.setMienGiam(new MienGiamHB());
                        break;
                }
            }
        }

        for (SinhVien sv : danhSach.values()) {
            System.out.println(sv);
        }

        String[] thuTuHe = {"CQ", "CLC", "LT"};
        for (String he : thuTuHe) {
            int dem = 0;
            long tongPhaiNop = 0;
            for (SinhVien sv : danhSach.values()) {
                if (sv.getMaHe().equals(he)) {
                    dem++;
                    tongPhaiNop += sv.phaiNop();
                }
            }
            if (dem > 0) {
                System.out.println(he + " " + dem + " " + tongPhaiNop);
            }
        }

        sc.close();
    }
}