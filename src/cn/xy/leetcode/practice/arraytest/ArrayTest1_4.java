package cn.xy.leetcode.practice.arraytest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author XiangYu
 * @create2020-12-14-17:12 删除排序数组中的重复项 II
 * 宠物、 狗和猫的类如下：
 * public class Pet { private String type;
 * public Pet(String type) { this.type = type; }
 * public String getPetType() { return this.type; }
 * } p
 * ublic class Dog extends Pet { public Dog() { super("dog"); } }
 * public class Cat extends Pet { public Cat() { super("cat"); } }
 * 实现一种狗猫队列的结构， 要求如下： 用户可以调用add方法将cat类或dog类的
 * 实例放入队列中； 用户可以调用pollAll方法， 将队列中所有的实例按照进队列
 * 的先后顺序依次弹出； 用户可以调用pollDog方法， 将队列中dog类的实例按照
 * 进队列的先后顺序依次弹出； 用户可以调用pollCat方法， 将队列中cat类的实
 * 例按照进队列的先后顺序依次弹出； 用户可以调用isEmpty方法， 检查队列中是
 * 否还有dog或cat的实例； 用户可以调用isDogEmpty方法， 检查队列中是否有dog
 * 类的实例； 用户可以调用isCatEmpty方法， 检查队列中是否有cat类的实例。
 */
public class ArrayTest1_4 {

    public class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }


        public String getPetTYpe() {
            return this.type;
        }

    }

    public class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }




    public class PetStack{
        private final Stack<EnterPet> dogStack = new Stack<>();

        private final Stack<EnterPet>  catStack = new Stack<>();

        private int index = 0;

        public void add(Pet pet){
            if(pet instanceof Dog ){
                dogStack.push(new EnterPet(pet,index++));
            }else if(pet instanceof Cat){
                catStack.push(new EnterPet(pet,index++));
            }
        }


        public Pet pollAll(){
            ArrayList<Pet> pets = new ArrayList<>();
            if(!dogStack.isEmpty() && !catStack.isEmpty()){
                if (dogStack.peek().index >= catStack.peek().index) {
                    return  dogStack.pop().getPet();
                } else {
                  return  catStack.pop().getPet();
                }
            }else if(dogStack.isEmpty()){
                return  catStack.pop().getPet();

            } else if (catStack.isEmpty()){
                return dogStack.pop().getPet();
            }else {
                throw new RuntimeException("err, queue is empty!");
            }
        }








    }




    private class EnterPet{
        private  Pet  pet;
        private  int index;

        public  EnterPet(Pet pet,int index){
            this.pet = pet;
            this.index = index;
        }


        public Pet getPet() {
            return pet;
        }

        public int getIndex() {
            return index;
        }
    }

}
