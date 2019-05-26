#PAAA 用户指南

### (1) 建立模板程序的程序依赖图

1. 将模板源程序打开为文件
2. 新建一个CDGBuilder类的对象
3. 调用builder对象的buildFrom方法, 参数为模板源程序文件
````java
File tmpFile = new File("filePath");
CDGBuilder builder = new CDGBuilder();
CDG cdgTmp = builder.buildFrom(tmpFile);
````

### (2) 对答案源程序评分

1. 将答案源程序打开为文件或字符串
3. 调用Grader类的静态方法grade
4. grade函数接受两个参数: 
    - (1)答案源程序文件或字符串; 
    - (2)模板程序的程序依赖图
5. grade函数的返回值为答案程序得分的浮点数值
    
````java
File ansFile = new File("filePath");
double score = Grader.grade(ansFile, cdgTmp);
````