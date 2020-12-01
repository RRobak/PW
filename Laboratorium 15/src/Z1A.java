import java.io.File;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Z1A extends RecursiveTask<Long> {
    private File file;
    public static long length;

    public Z1A(File file) {
        this.file = file;
    }

    @Override
    protected Long compute() {
        long rozmiar = 0;
        for (File file : Objects.requireNonNull(file.listFiles())) {
            if (file.isFile()) {
                length += file.length();
                rozmiar = length;
            } else {
                Z1A subtask = new Z1A(file);
                subtask.fork();
                subtask.join();
                rozmiar = length;
            }
        }
        return rozmiar;
    }

    public static void main(String[] args) {
        File file = new File("D:\\.Studia");
        ForkJoinPool forkJoin = new ForkJoinPool();
        long folderSize = forkJoin.invoke(new Z1A(file));
        System.out.println("Folder liczy: " + folderSize + "KB");
    }
}

