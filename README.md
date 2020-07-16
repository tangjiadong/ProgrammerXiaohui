# 漫画算法小灰的算法之旅

## 第1章 算法概述

### 衡量算法好坏的重要标准

#### 1.时间复杂度

##### 基本操作执行次数

设T(n)为程序基本操作执行次数的函数(也可以认为是程序的相对执行时间函数),n为输入规模。

最常见的4种执行方法：

1. T(n)=3n=O(n),执行的次数是**线性**的

```java
    void eat1(int n){
        for(int i=0; i<n; i++){;
            System.out.println("等待一天");
            System.out.println("等待一天");
            System.out.println("吃一寸面包");
        }
    }
```

2. T(n)=5logn=O(logn)，执行次数是用**对数**计算的

```java
    void eat2(int n){
        for(int i=n; i>1; i/=2){
            System.out.println("等待一天");
            System.out.println("等待一天");
            System.out.println("等待一天");
            System.out.println("等待一天");
            System.out.println("吃一半面包");
        }
    }
```

3. T(n)=2=O(1)，执行次数是常量

```java
    void eat3(int n){
        System.out.println("等待一天");
        System.out.println("吃一个鸡腿");
    }
```

4. T(n)=0.5n²+0.5n=O(n²)，执行次数是多项式计算的

```java
    void eat4(int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                System.out.println("等待一天");
            }
            System.out.println("吃一寸面包");
        }
    }
```

##### 渐进时间复杂度

有了基本操作执行次数的函数T(n)，为了解决时间分析的难题，有了**渐进时间复杂度** 

T(n)=O(f(n))，O为算法的渐进时间复杂度，称为大O表示法。

渐进时间复杂度推导出时间复杂度原则：

* 如果运行时间为常数量级，则用1表示
* 只保留时间函数中的最高阶项
* 如果最高阶项存在，则省去最高阶项前面的系数

#### 2.空间复杂度

案例：n个整数，其中两个整数是重复的，要求找出两个重复的整数。

解决方案：（散列表）开辟一定的内存空间来存储有用的数据信息

* 最朴素的方法就是双重循环：遍历整数，回顾前面的数字，发现有没有重复

  时间复杂度O(n²)

* 利用中间数据：当遍历整个数列时，就把该整数存储起来，就像放到字典中一样。当遍历下一个整数时，不必再慢慢向前回溯比较，而直接去“字典”中查找，看看有没有对应的整数即可。

  “字典“左侧的key代表整数的值，右侧的value代表整数出现的次数

最常见的4种情形

1. 常量空间：O(1)

```java
    void fun1(int n){
        int var = 3;
        //do something
    }
```

2. 线性空间：O(n)

```java
    void fun2(int n){
        int[] array = new int[n];
        //do something
    }
```

3. 二维空间：O(n²)

```java
    void fun3(int n){
        int[][] matrix = new int[n][n];
        //do something
    }
```

4. 递归空间：O(n)

   计算机在执行的时候，会专门分配一块内存，用来存储”方法调用栈"

   ”方法调用栈"包含：

   * 进栈：把调用的方法和参数信息压入栈中
   * 出栈：把调用的方法和参数从栈中弹出

```java
    void fun4(int n){
        if(n<=0){
            return;
        }
        fun4(n-1);
        //do something
    }
```

## 第2章 数据结构基础

### 数组

高效查找算法二分查找，就是利用了数组的这个优势

#### 1.中间插入

```java
public class MyArray {

    private int[] array;
    private int size;

    public MyArray(int capacity){
        this.array = new int[capacity];
        size = 0;
    }

    /**
     * 数组插入元素
     * @param index  插入的位置
     * @param element  插入的元素
     */
    public void insert(int index, int element) throws Exception {
        //判断访问下标是否超出范围
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException("超出数组实际元素范围！");
        }
        //如果实际元素达到数组容量上线，数组扩容
        if(size >= array.length){
            resize();
        }
        //从右向左循环，逐个元素向右挪一位。
        for(int i=size-1; i>=index; i--){
            array[i+1] = array[i];
        }
        //腾出的位置放入新元素
        array[index] = element;
        size++;
    }

    /**
     * 数组扩容
     */
    public void resize(){
        int[] arrayNew = new int[array.length*2];
        //从旧数组拷贝到新数组
        System.arraycopy(array, 0, arrayNew, 0, array.length);
        array = arrayNew;
    }

    /**
     * 数组删除元素
     * @param index  删除的位置
     */
    public int delete(int index) throws Exception {
        //判断访问下标是否超出范围
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("超出数组实际元素范围！");
        }
        int deletedElement = array[index];
        //从左向右循环，逐个元素向左挪一位。
        for(int i=index; i<size-1; i++){
            array[i] = array[i+1];
        }
        size--;
        return deletedElement;
    }

    /**
     * 输出数组
     */
    public void output(){
        for(int i=0; i<size; i++){
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        MyArray myArray = new MyArray(4);
        myArray.insert(0,3);
        myArray.insert(1,7);
        myArray.insert(2,9);
        myArray.insert(3,5);
        myArray.insert(1,6);
        myArray.insert(5,8);
        myArray.delete(3);
        myArray.output();
    }
}
```

#### 2.超范围插入

数组扩容，创建一个新的数组，长度是旧数组的两倍，再把旧数组中的元素统统复制过去

```java
		/**
     * 数组扩容
     */
    public void resize(){
        int[] arrayNew = new int[array.length*2];
        //从旧数组拷贝到新数组
        System.arraycopy(array, 0, arrayNew, 0, array.length);
        array = arrayNew;
    }
```

#### 3.删除元素

```java
    /**
     * 数组删除元素
     */
    public int delete(int index) throws Exception {
        //判断访问下标是否超出范围
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出数组实际元素范围");
        }
        int deleteElement = array[index];
        //从左向右,将元素逐个向左移1位
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return deleteElement;
    }
```

### 链表

#### 链表与数组区别

链表随机存储

​	链表采用见缝插针的方式，链表的每一个节点分布在内存不同位置，依靠next指针关联起来。

数组顺序存储

​	数组在内存中占用了连续完整的存储空间

#### 分类

* 单向链表：存放数据的变量data，指向下一节点的指针next
* 双向链表：除了拥有data和next指针，还拥有指向前置节点的prev指针

#### 基本操作

##### 1.查找节点

链表不像数组那样可以通过下标快速进行定位，只能从头结点开始向后一个一个节点逐一查找

##### 2.更新节点

如果不考虑查找节点的过程，链表的更新过程会像数组那样简单，直接把旧数据替换成新数据

##### 3.插入元素

* 尾部插入

* 头部插入

  第1步，把新节点的next指针指向原先的头结点；

  第2步，把新节点变为链表的头结点；

* 中间插入 

  第1步，新节点的next指针，指向插入位置的节点；

  第2步，插入位置前置节点的next指针，指向新节点；

##### 4.删除元素

* 尾部删除

  把倒数第2个节点的next指针指向空即可

* 头部删除

  把链表的头结点设为原先头结点的next指针即可

* 中间删除

  把要删除节点的前置节点的next指针，指向要删除元素的下一个节点即可

#### 代码实现

