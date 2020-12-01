public class z1 {

    public static void main(String[] args)
    {
        Prostokat p = new Prostokat (0,0,50,0,0);
        System.out.println(p);
        p = p.Zmienszerokosc(30);
        System.out.println(p);
        p = p.Zmienwysokosc(50);
        System.out.println(p);
        p = p.Obroc();
        System.out.println(p);
        p = p.ChangeX(10);
        System.out.println(p);
        p = p.ChangeY(10);
        System.out.println(p);
    }

}