/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appendingtoserilizedfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antonio
 */
public class TestAppendingObject {

    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        File f = new File("students.ser");
        Scanner scn = new Scanner(System.in);
        Scanner scm = new Scanner(System.in);
        String name = "";
        double mark;

        try {
            if (!f.exists()) {
                oos = new ObjectOutputStream(new FileOutputStream(f));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(f, true)) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }
            do {
                System.out.println("name: ");
                name = scn.nextLine();
                if (!name.isEmpty()) {
                    System.out.println("mark: ");
                    mark = scm.nextDouble();
                    oos.writeObject(new Student(name, mark));
                }
            } while (!name.isEmpty());
            oos.close();
            ois = new ObjectInputStream(new FileInputStream("students.ser"));
            while (true) {
                Student s = (Student) ois.readObject();
                System.out.println(s);
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.getMessage();
        }
    }
}
