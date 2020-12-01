import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class zad2 {
    public static Map<Integer, String> issueMap = new HashMap<Integer, String>();

    public static void main(String[] args) {
        BlockingQueue<Issue> issueQueue = new ArrayBlockingQueue<>(10);
        Homie mieszkaniec = new Homie(issueQueue, issueMap);
        ServiceGuy serwisant = new ServiceGuy(issueQueue, issueMap);

        SetUpMap();

        new Thread(mieszkaniec).start();
        new Thread(serwisant).start();
        System.out.println("Mieszkaniec i serwisant uruchomieni");
    }

    public static void SetUpMap() {
        issueMap.put(0, "Awaria windy");
        issueMap.put(1, "Awaria pralki");
        issueMap.put(2, "Awaria zmywarki");
        issueMap.put(3, "Awaria ogrzewania");
    }
}

class Issue {
    private String msg;

    public Issue(String str) {
        this.msg = str;
    }

    public String GetIssue() {
        return msg;
    }
}

class Homie implements Runnable {

    private BlockingQueue<Issue> qq;
    private Map<Integer, String> issueMap;

    public Homie(BlockingQueue<Issue> givenQueue, Map<Integer, String> issueMap) {
        this.qq = givenQueue;
        this.issueMap = issueMap;
    }

    @Override
    public void run() {
        Random gen = new Random();
        for (int i = 0; i < 10; i++) {
            Issue msg = new Issue("" + i);
            try {
                qq.put(msg);
                System.out.println("Wykryto AWARIĘ!! ->" + issueMap.get(gen.nextInt(4)));
                Thread.sleep(i * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ServiceGuy implements Runnable {

    private BlockingQueue<Issue> qq;
    private Map<Integer, String> issueMap;

    public ServiceGuy(BlockingQueue<Issue> givenQueue, Map<Integer, String> issueMap) {
        this.qq = givenQueue;
        this.issueMap = issueMap;
    }

    @Override
    public void run() {
        Issue iName;
        int mulitplier;
        try {
            while ((iName = qq.take()).GetIssue() != "exit") {
                mulitplier = GetKey(iName.GetIssue());
                if (mulitplier != -1) {
                    Thread.sleep(100 * mulitplier);
                    System.out.println("Awaria naprawiona : " + iName.GetIssue());
                } else {
                    System.out.println("Brak awarii " + iName.GetIssue());
                }

            }
        } catch (

        InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Integer GetKey(String value) {
        for (Integer key : issueMap.keySet()) {
            if (!issueMap.equals(issueMap.get(key))) {
                return key;
            } else {
                return -1;
            }
        }
        return null;
    }

}
