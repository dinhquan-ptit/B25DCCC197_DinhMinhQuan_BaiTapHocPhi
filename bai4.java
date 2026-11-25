package baithuchanh.baitrenlop;
import java.util.ArrayList;
import java.util.Scanner;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    public long tinhGiam(long hocPhi) {
        return hocPhi * 50 / 100;
    }
}

class HoNgheo implements MienGiam {
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class HocBong implements MienGiam {
    public long tinhGiam(long hocPhi) {
        if (hocPhi < 3000000L) {
            return hocPhi;
        }
        return 3000000L;
    }
}

abstract class SinhVien {
    String maHe;
    String maSv;
    int tinChi;
    MienGiam mienGiam;

    SinhVien(String maHe, String maSv, int tinChi) {
        this.maHe = maHe;
        this.maSv = maSv;
        this.tinChi = tinChi;
        this.mienGiam = null;
    }

    abstract long hocPhi();

    long soTienGiam() {
        if (mienGiam == null) {
            return 0L;
        }
        return mienGiam.tinhGiam(hocPhi());
    }

    long phaiNop() {
        return hocPhi() - soTienGiam();
    }

    public String toString() {
        return maSv + " " + maHe + " " + hocPhi() + " " + soTienGiam() + " " + phaiNop();
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

public class bai4 {
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

        int m = sc.nextInt();
        ArrayList<String> notFound = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String ma = sc.next();
            String loai = sc.next();
            SinhVien svTim = null;
            for (SinhVien sv : ds) {
                if (sv.maSv.equals(ma)) {
                    svTim = sv;
                    break;
                }
            }
            if (svTim == null) {
                notFound.add("NOT FOUND " + ma);
            } else {
                if (loai.equals("CS")) {
                    svTim.mienGiam = new ConChinhSach();
                } else if (loai.equals("HN")) {
                    svTim.mienGiam = new HoNgheo();
                } else if (loai.equals("HB")) {
                    svTim.mienGiam = new HocBong();
                }
            }
        }

        for (String s : notFound) {
            System.out.println(s);
        }

        for (SinhVien sv : ds) {
            System.out.println(sv);
        }

        String[] cacHe = {"CQ", "CLC", "LT"};
        for (String he : cacHe) {
            int dem = 0;
            long tongNop = 0;
            for (SinhVien sv : ds) {
                if (sv.maHe.equals(he)) {
                    dem++;
                    tongNop += sv.phaiNop();
                }
            }
            if (dem > 0) {
                System.out.println(he + " " + dem + " " + tongNop);
            }
        }
    }
}
