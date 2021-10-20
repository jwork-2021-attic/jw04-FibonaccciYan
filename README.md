# 任务二

`maze`分支实现了玩家走迷宫，既可以通过键盘的上下左右箭头键控制玩家移动，也可以长按空格键来使用自动寻路功能。

迷宫生成算法来自https://github.com/oppenheimj/maze-generator，由于将迷宫出口设置为地图右下角，为了保证该点一定可到达做了一点小的修改。

自动寻路使用A*算法，并且实现了算法寻路过程的可视化，其中绿色部分表示正在寻路的节点(`openList`)，蓝色部分表示已探查的节点(`closeList`)，当探查到终点时停止寻路，玩家开始移动并到达终点，路径用红色箭头标记。

代码运行视频请见https://www.bilibili.com/video/BV1vR4y1J7WB?share_source=copy_web。
