package cn.xy.leetcode;

/**
 * @author XiangYu
 * @create2020-10-30-13:23
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 *
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *第一次算的外边周长，利用每行数组的长度只差来获得每一行被计算的宽
 *
 * 看了其他人的思路后，现在算总块数，然后减取贴合面，贴合面还出现了重复计算的问题
 *
 */
public class A0463IslandPerimeter {
    public static void main(String[] args) {

        int[][] grid ={{1},{0}};

        int[][] grid2 = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int[][] grid3 ={{1,1}};
        System.out.println(islandPerimeter(grid));

    }

    public static int islandPerimeter(int[][] grid) {

        //块的数量
        int blockNum = 0;
        //相邻的面的数量
        int planeNum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1){
                    blockNum ++;

                    //找到一块后，检测该块与其他块重合的面
                    //上侧
                    if(i != 0){
                        if(grid[i-1][j] == 1){
                            planeNum++;
                        }
                    }
                    //左侧
                    if(j != 0){
                        if(grid[i][j-1] == 1){
                            planeNum++;
                        }
                    }
                }

            }

        }
        return  4*blockNum - 2*planeNum;
    }

}
