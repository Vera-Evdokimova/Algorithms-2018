package lesson5;

import kotlin.NotImplementedError;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     * <p>
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ:
     * <p>
     * G    H
     * |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     * <p>
     * Дан граф без циклов (получатель), например
     * <p>
     * G -- H -- J
     * |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     * <p>
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     * <p>
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     * <p>
     * В данном случае ответ (A, E, F, D, G, J)
     * <p>
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */

    /**
     * Трудоемкость: так как мы запоминаем значения на предыдущих шагах ->
     * Трудоемкость O(v + e), где v - количество вершин, e - количесво рёбер.
     * Ресурсоемкость: так как в storage мы хваним соответствующее каждой вершине независимое множество других вершин ->
     * Ресурсоемкость O(v^2).
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        Graph.Vertex root = graph.getVertices().iterator().next();
        Map<Graph.Vertex, Set<Graph.Vertex>> storage = new HashMap<>();
        return independentSubset(storage, root, null, graph);
    }

    private static Set<Graph.Vertex> independentSubset(Map<Graph.Vertex,
            Set<Graph.Vertex>> storage, Graph.Vertex root, Graph.Vertex parent, Graph graph) {
        Set<Graph.Vertex> result = storage.get(root);
        if (result != null) return result;

        Set<Graph.Vertex> childrenSet = new HashSet<>();
        Set<Graph.Vertex> grandchildrenSet = new HashSet<>();

        for (Graph.Vertex neighbour : graph.getNeighbors(root)) {
            if (neighbour != parent) {
                childrenSet.addAll(independentSubset(storage, neighbour, root, graph));
                for (Graph.Vertex grandneighbour : graph.getNeighbors(neighbour)) {
                    if (grandneighbour != root) {
                        grandchildrenSet.addAll(independentSubset(storage, grandneighbour, neighbour, graph));
                    }
                }
            }
        }
        if (childrenSet.size() > grandchildrenSet.size() + 1) {
            storage.put(root, childrenSet);
            return childrenSet;
        } else {
            grandchildrenSet.add(root);
            storage.put(root, grandchildrenSet);
            return grandchildrenSet;
        }
    }


    /**
     * Наидлиннейший простой путь.
     * Сложная
     * <p>
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */

    /**
     * Трудоемкость: так как алгоритм перебирает все возможные простые пути ->
     * Трудоемкость O(n!), где n - количество вершин
     * Ресурсоемкость: так как используется стек который хранит в себе все перебираемые пути ->
     * Ресурсоемкость O(n!)
     */
    public static Path longestSimplePath(Graph graph) {
        Deque<Path> deque = new ArrayDeque<>();
        Path longestPath = null;
        int length = -1;
        for (Graph.Vertex vertex : graph.getVertices()) {
            deque.push(new Path(vertex));
        }
        while (!deque.isEmpty()) {
            Path path = deque.pop();
            if (path.getLength() > length) {
                longestPath = path;
                length = path.getLength();
                if (path.getVertices().size() == graph.getVertices().size()) {
                    break;
                }
            }
            List<Graph.Vertex> vertices = path.getVertices();
            for (Graph.Vertex neighbour : graph.getNeighbors(vertices.get(vertices.size() - 1))) {
                if (!path.contains(neighbour)) {
                    deque.push((new Path(path, graph, neighbour)));
                }
            }
        }
        return longestPath;
    }
}
