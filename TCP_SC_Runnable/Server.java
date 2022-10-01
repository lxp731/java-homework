package TCP_SC_Runnable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-01 17:02
 **/

public class Server {
    private static ExecutorService pool = new ThreadPoolExecutor(
            3,
            5,
            6,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());
    //核心线程数量，临时线程数量，空闲时间，单位（秒），任务队列（存2个任务），线程工厂，拒绝执行处理任务

    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动成功！");
        ServerSocket serverSocket = new ServerSocket(8081);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getRemoteSocketAddress() + "上线了！");
            //把ServerRunnable做成任务交给线程池
            Runnable target = new ServerRunnable(socket);
            pool.execute(target);
        }
    }
}
