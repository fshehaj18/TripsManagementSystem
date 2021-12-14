package com.example.service.utils;

public class ShowAllPathsUtil {

    /*private static void printPath(List<String> path)
    {
        int size = path.size();
        for(String v : path)
        {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private static boolean isNotVisited(String x, List<String> stops)
    {
        int size = stops.size();
        for(int i = 0; i < size; i++)
            if (stops.get(i).equals(x))
                return false;

        return true;
    }
    private static void findpaths(List<List<String> > g, int src, int dst, int v)
    {

        Queue<List<Integer> > queue = new LinkedList<>();

        List<Integer> path = new ArrayList<>();
        path.add(src);
        queue.offer(path);

        while (!queue.isEmpty())
        {
            path = queue.poll();
            int last = path.get(path.size() - 1);

            // If last vertex is the desired destination
            // then print the path
            if (last == dst)
            {
                printPath(path);
            }
            List<String> lastNode = g.get(last);
            for(int i = 0; i < lastNode.size(); i++)
            {
                if (isNotVisited(lastNode.get(i), path))
                {
                    List<Integer> newpath = new ArrayList<>(path);
                    newpath.add(lastNode.get(i));
                    queue.offer(newpath);
                }
            }
        }
    }*/
}
