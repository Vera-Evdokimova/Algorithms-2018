package lesson6;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        throw new NotImplementedError();
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */

    /**
     * Трудоемкость: чтение файла , заполнение матрицы, хранящей поле, подсчет пути до каждой ячейки ->
     * Трудоемкость O(m*n), где m - количество строк, а n - количество символов в каждой строке
     * Ресурсоемкость: так как для хванения поля используется матрица размера m*n ->  Ресурсоемкость O(m*n)
     */
    public static int shortestPathOnField(String inputName) throws IOException {
        List<List<Integer>> matrix = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputName))) {
            String string = bufferedReader.readLine();
            while (string != null) {
                List<Integer> list = new ArrayList<>();
                for (String num : string.split(" ")) {
                    list.add(Integer.parseInt(num));
                }
                matrix.add(list);
                string = bufferedReader.readLine();
            }
        }
        int size1 = matrix.size();
        int size2 = matrix.get(0).size();

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                matrix.get(i).set(j, matrix.get(i).get(j) + minFromNeighbours(matrix, i, j));
            }
        }
        return matrix.get(size1 - 1).get(size2 - 1);
    }

    private static int minFromNeighbours(List<List<Integer>> field, int i, int j) {
        if (i == 0 && j == 0) return 0;
        else if (i == 0) return field.get(i).get(j - 1);
        else if (j == 0) return field.get(i - 1).get(j);
        else return Math.min(Math.min(field.get(i - 1).get(j - 1),
                    field.get(i).get(j - 1)),
                    field.get(i - 1).get(j));
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
