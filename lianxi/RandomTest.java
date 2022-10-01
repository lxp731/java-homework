package lianxi;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RandomTest {

    public static void main(String[] args) throws IOException {

        File file = new File("D:\\Java\\betty.txt");
        ArrayList<String> arrayList = new ArrayList<String>();

        if(file.length()>0) {
            FileReader fileReader = new FileReader(file);

            int size = (int) file.length();
            char[] chars = new char[size];

            int length = 0;

            while (fileReader.ready()) {

                length = length + fileReader.read(chars, 0, size - length);
            }
            fileReader.close();
            String[] old = (new String(chars)).split(",");

            for (int i = 0; i < old.length; i++
                    ) {

                arrayList.add(old[i]);
            }
        }
        int num;

        int j = 0;


        while (j < 10) {

            num = (int) (Math.random() * 67 + 1);
            if (!arrayList.contains(String.valueOf(num))) {
                arrayList.add(String.valueOf(num));
                j++;
                System.out.println(num);
            }



        }

        //FileWriter fileWriter = new FileWriter(file);
        //TODO 如果想追加写
        FileWriter fileWriter = new FileWriter(file);

        if(!file.exists()){

            file.createNewFile();
        }

        for (String s: arrayList
             ) {
            fileWriter.write(s);
            fileWriter.write(",");
        }

        fileWriter.close();

    }

}
