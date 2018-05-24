package com.czh.test.innerclass;
/**  
* <p>Title: Car.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年5月11日  
* @version 1.0  
*/
class Car {
    Car() {
        System.out.println("new Car()");
        new Tyre();
    }
    class Tyre { // 我会被覆盖吗
        Tyre() { System.out.println("new Tyre()"); }
    }
}
