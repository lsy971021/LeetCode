package lsy;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    public static void main(String[] args) {
        boolean[][] marked = new boolean[13][13];
        DFS dfs = new DFS(20);
        dfs.addCity(0,1);
        dfs.addCity(6,9);
        dfs.addCity(3,8);
        dfs.addCity(5,11);
        dfs.addCity(10,4);
        dfs.addCity(2,12);
        dfs.addCity(6,10);
        dfs.addCity(4,8);
        System.out.println(dfs.isConnect(marked,dfs.table, 3, 9));
    }
    /**
     * 某省调查城镇交通状况，得到现有城镇道路统计表，表中列出了每条道路直接连通的城镇。省政府“畅通工程”的目
     * 标是使全省任何两个城镇间都可以实现交通（但不一定有直接的道路相连，只要互相间接通过道路可达即可）。目
     * 前的道路状况，判断某两个城市是否想通。
     *
     *
     * =============在dfs类中=============
     */
    //城市总数
    int city;
    //邻接表
    boolean[][] table;
    //总想通数
    int connectCount;

    //构造函数
    public DFS(int city) {
        this.city = city;
        this.connectCount = 0;
        table = new boolean[city][city];
    }

    //添加修好的道路相连的城市
    public boolean addCity(int oldCity, int newCity) {
        if (!table[oldCity][newCity] || !table[newCity][oldCity]) {
            table[oldCity][newCity] = true;
            table[newCity][oldCity] = true;
            connectCount++;
            return true;
        }
        return false;
    }

    //判断两个城市是否修好道路
    public boolean connectWith(int one, int two) {
        return table[one][two] || table[two][one];
    }

    //获取与某个城市直接相邻的城市
    public List connectCity(int city) {
        boolean[] booleans = table[city];
        List<Integer> cities = new ArrayList<Integer>();
        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                cities.add(i);
            }
        }
        return cities;
    }

    //判断任意两个城市是否能到达
    boolean i;
    public boolean isConnect(boolean[][] marked,boolean[][] table, int oneCity, int target) {
        //获取当前城市对各个城市连通情况表
        boolean[] cityTo = table[oneCity];
        //判断是否和目标城市连通
        if(cityTo[target]){
            i = true;
            return true;
        }

        for (int i = 0; i < cityTo.length; i++) {
            if(marked[oneCity][i]||marked[i][oneCity]){
                continue;
            }
            marked[oneCity][i]=true;
            marked[i][oneCity]=true;
            //判断该城市是否连通
            if (cityTo[i]) {
                //以某个连通城市为主要城市判断有没有与目标城市连通的城市
                if(isConnect(marked,table, i, target)){
                    return true;
                };
            }
        }
        return i;
    }
}
