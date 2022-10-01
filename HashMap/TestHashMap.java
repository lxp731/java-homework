package HashMap;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: TinyTerm
 * @description:
 * @author: Liuxp
 * @create: 2022-07-09 11:18
 **/

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        int x = 0;
        String str = null;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            x = sc.nextInt();
            str = sc.nextLine();
            hashMap.put(x, str);
        }
        System.out.println(hashMap);
    }
}
