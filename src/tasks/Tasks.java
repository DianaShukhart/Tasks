package tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

public class Tasks {  // РАБОТА С XML-ФАЙЛАМИ

    public static void main(String[] args) {
        final int r = 4;
        final int c = 5; //4 строки и 5 столбцов
        int m[][] = new int[r][c];
        int k;

        // Вывод данных о задании на экран
        System.out.println("Если минимальный элемент стоит во втором столбце, то заменить элементы этого столбца нулями");

        try {
            // Определяем текущий каталог с именем файла
            String FileName = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                    + System.getProperty("file.separator") + "properties.xml";//путь к файлу
            Properties p = new Properties(); // Переменная для хранения xml-данных
            File f = new File(FileName); // Переменная для доступа к файлам

            //пусто файл xml
            //  String FileName = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
            //       + System.getProperty("file.separator") + "prop.xml";//путь к файлу
            //  Properties p = new Properties(); // Переменная для хранения xml-данных
            // File f = new File(FileName);
            
            if (f.exists() == false) { // Если файл НЕ существует, то
                f.createNewFile(); // Создаем пустой файл для дальнейшего сохранения в него данных xml
                // Создаем СОУЧАЙНЫЕ данные для xml
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        k = (int) Math.round(Math.random() * 9);
                        p.put("m" + i + j, String.valueOf(k));
                    }
                }
                // Сохранение обработанных данных массива в XML-файл
                p.storeToXML(new FileOutputStream(FileName), new Date().toString());
            } else { // Если файл существует, то
                // Загружаем xml-данные из файла в переменную p для сохранения существующих значений
                p.loadFromXML(new FileInputStream(FileName));
            }
        

            System.out.println("Матрица:");

            // Считывание данных из XML-файла
            for (int i = 0; i < r; i++) {//цикл в цикле для строк и стобцов
                for (int j = 0; j < c; j++) {
                    k = Integer.valueOf(p.getProperty("m" + i + j, "0"));
                    m[i][j] = k;
                    System.out.print(k + " ");
                }
                System.out.println("");
            }

            // Реализация алгоритма варианта задания 
            int min = m[0][0];
            boolean key = false;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) //  цикл
                {

                    if (m[i][j] < min) {
                        min = m[i][j];//

                        if (j == 1) {
                            key = true;
                        } else {
                            key = false;
                        }
                    }
                }
            }
            if (key == true) {
                for (int i = 0; i < r; i++) {
                    m[i][1] = 0;
                }
            }

            System.out.println("Минимальный элемент: "+min);

            // Сохранение обработанных данных массива в XML-файл
            p.storeToXML(new FileOutputStream(FileName), new Date().toString());

            // Считывание обработанных данных из XML-файла
            System.out.println("Новая матрица:");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    System.out.print(m[i][j] + " ");
                }
                System.out.println("");
            }

        } catch (Exception e) {
            System.err.println("Error working with XML-file!"); // Вывести сообщение об ошибке            
        }
    }
}
