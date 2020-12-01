    public final class Prostokat {
        private final int x;
        private final int y;
        private final int kat;
        private final int wysokosc;
        private final int szerokosc;

        public Prostokat(int x, int y, int kat, int wysokosc, int szerokosc) {
            this.x = x;
            this.y = y;
            this.kat = kat;
            this.wysokosc = wysokosc;
            this.szerokosc = szerokosc;
        }

        public Prostokat ChangeX(int x) {
            return new Prostokat(x, y, kat, wysokosc, szerokosc);
        }

        public Prostokat ChangeY(int x) {
            return new Prostokat(x, x, kat, wysokosc, szerokosc);
        }

        public Prostokat Zmienwysokosc(int tmp) {
            return new Prostokat(x, y, kat, tmp, szerokosc);
        }

        public Prostokat Zmienszerokosc(int tmp) {
            return new Prostokat(x, y, kat, wysokosc, tmp);
        }

        public Prostokat Obroc() {
            if (kat + 90 >= 360)
                return new Prostokat(x, y, 90 - (360 - kat), wysokosc, szerokosc);
            else
                return new Prostokat(x, y, kat + 90, wysokosc, szerokosc);
        }

        @Override
        public String toString() {
            return "Prostokat: X=" + x + ", y=" + y + ", a= " + wysokosc + ", b= " + szerokosc + ", kat= " + kat
                    + "";
        }
}
