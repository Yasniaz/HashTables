package com.company;

import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {
    Node[] data;
    Integer[] keys;
    int max;

    public class Node {

        Integer code;
        String area;
        int population;

        public Node(Integer s, String s1, Integer valueOf) {
             code = s;
             area = s1;
             population = valueOf;
        }
    }
    public Zip(String file) {
        data = new Node[10000];
        keys = new Integer[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[i] = new Node(code, row[1], Integer.valueOf(row[2]));
                keys[i++] = code;
                //reduced time when it performs code.equals takes linear time since it compares the entire string and compares charachter one at a time
                //while with integers it compares numbers which is simpler
                //file has numbers in a strange format given with spaces so if we data[i].code == code
                //data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }
            max = i-1; ; //99999 direct access to indices and thus gain constant time constant number of elements array larger
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public Node lookUp(String s){ //linear

        for (int i = 0; i <= max; i++) {
            if(data[i].code.equals(s)){
                return data[i];
            }
        }
        return null;
    }

    public Node lookupLinear(String s){
        String s1 = removeSpaces(s);
        return data[Integer.parseInt(s1)];
        //We observe that we see around less than a microsecond of time to search for
        //a zipcode in the data, using this method, and we consistently get close to the same
        //time which indicates that this function has a constant time complexity.
        //Unlike doing a linear search or a binary search through an array.
    }

    private String removeSpaces(String s){
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i) != ' '){
                s1.append(s.charAt(i));
            }
        }
        return s1.toString();
    }

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];
        for (int i = 0; i < max; i++) {
            Integer index = keys[i] % mod; //multiple keys can have the same index
            cols[data[index]]++; //no collision is best case scenario 10 % 100 = 10 same number of elements less or equal to mod, exceeding collisions
            data[index]++;
        }
        System.out.print(mod);
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

 }