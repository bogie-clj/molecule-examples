package com.github.bogieclj.molecule.sql.example1;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AnotherTestClass {

    // Read multi-line input from console in Java by using two Scanners
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            List<String> tokens = new ArrayList<>();
            Scanner lineScanner = new Scanner(scanner.nextLine());

            while (lineScanner.hasNext()) {
                tokens.add(lineScanner.next());
            }

            lineScanner.close();
            System.out.println(tokens);
        }

        scanner.close();
    }

}


