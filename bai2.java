package baithuchanh.baitrenlop;
import java.util.ArrayList;
import java.util.Scanner;

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

public class bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<SinhVien> ds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            int tc = sc.nextInt();
            if (he.equals("CQ")) {
                ds.add(new ChinhQuy(he, ma, tc));
            } else if (he.equals("CLC")) {
                ds.add(new ChatLuongCao(he, ma, tc));
            } else if (he.equals("LT")) {
                ds.add(new LienThong(he, ma, tc));
            }
        }

        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
    }
}
