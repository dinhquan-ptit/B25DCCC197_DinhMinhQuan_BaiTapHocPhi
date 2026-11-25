package baithuchanh.baitrenlop;
import java.util.ArrayList;
import java.util.Scanner;

class DuLieuKhongHopLeException extends Exception {
    DuLieuKhongHopLeException(String msg) {
        super(msg);
    }
}

abstract class SinhVien {
    String maHe;
    String maSv;
    int tinChi;

    SinhVien(String maHe, String maSv, int tinChi) {
        this.maHe = maHe;
        this.maSv = maSv;
        this.tinChi = tinChi;
    }

    abstract long hocPhi();

    public String toString() {
        return maSv + " " + maHe + " " + hocPhi();
    }
}

class ChinhQuy extends SinhVien {
    ChinhQuy(String maHe, String maSv, int tinChi) {
        super(maHe, maSv, tinChi);
    }

    long hocPhi() {
        return (long) tinChi * 450000L;
    }
}

class ChatLuongCao extends SinhVien {
    ChatLuongCao(String maHe, String maSv, int tinChi) {
        super(maHe, maSv, tinChi);
    }

    long hocPhi() {
        return (long) (tinChi * 450000L * 1.5);
    }
}

class LienThong extends SinhVien {
    LienThong(String maHe, String maSv, int tinChi) {
        super(maHe, maSv, tinChi);
    }

    long hocPhi() {
        return (long) tinChi * 450000L + 2000000L;
    }
}

class QuanLyHocPhi {
    ArrayList<SinhVien> ds = new ArrayList<>();

    boolean daTonTai(String maSv) {
        for (SinhVien sv : ds) {
            if (sv.maSv.equals(maSv)) {
                return true;
            }
        }
        return false;
    }

    SinhVien taoSinhVien(String he, String ma, String tcStr) throws DuLieuKhongHopLeException {
        if (!he.equals("CQ") && !he.equals("CLC") && !he.equals("LT")) {
            throw new DuLieuKhongHopLeException("Ma he sai");
        }

        int tc;
        try {
            tc = Integer.parseInt(tcStr);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("Tin chi khong phai so nguyen");
        }

        if (tc < 1 || tc > 30) {
            throw new DuLieuKhongHopLeException("Tin chi ngoai [1, 30]");
        }

        if (daTonTai(ma)) {
            throw new DuLieuKhongHopLeException("Trung ma sinh vien");
        }

        if (he.equals("CQ")) {
            return new ChinhQuy(he, ma, tc);
        } else if (he.equals("CLC")) {
            return new ChatLuongCao(he, ma, tc);
        } else {
            return new LienThong(he, ma, tc);
        }
    }

    void them(String he, String ma, String tcStr) throws DuLieuKhongHopLeException {
        ds.add(taoSinhVien(he, ma, tcStr));
    }

    void sapXep() {
        for (int i = 0; i < ds.size() - 1; i++) {
            for (int j = i + 1; j < ds.size(); j++) {
                SinhVien a = ds.get(i);
                SinhVien b = ds.get(j);
                long hpA = a.hocPhi();
                long hpB = b.hocPhi();
                if (hpB > hpA || (hpB == hpA && a.maSv.compareTo(b.maSv) > 0)) {
                    ds.set(i, b);
                    ds.set(j, a);
                }
            }
        }
    }

    long tongHocPhi() {
        long tong = 0;
        for (SinhVien sv : ds) {
            tong += sv.hocPhi();
        }
        return tong;
    }
}

public class bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        QuanLyHocPhi ql = new QuanLyHocPhi();
        ArrayList<String> loi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String he = p[0];
            String ma = p[1];
            String tcStr = p[2];
            try {
                ql.them(he, ma, tcStr);
            } catch (DuLieuKhongHopLeException e) {
                loi.add("INVALID " + ma);
            }
        }

        for (String s : loi) {
            System.out.println(s);
        }

        ql.sapXep();
        for (SinhVien sv : ql.ds) {
            System.out.println(sv);
        }

        System.out.println("TONG " + ql.tongHocPhi());
    }
}
