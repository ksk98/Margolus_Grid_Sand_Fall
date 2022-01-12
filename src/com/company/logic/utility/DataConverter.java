package com.company.logic.utility;

import java.io.*;

public abstract class DataConverter {
    public static int[][] getMapDataFromFilePath(String path) throws IOException {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader bufReader = new BufferedReader(reader);

        String[] in = bufReader.readLine().split(" ");
        int[][] out = new int[Integer.parseInt(in[1])][Integer.parseInt(in[0])];

        StringBuilder data = new StringBuilder();
        String inString;
        while ((inString = bufReader.readLine()) != null)
            data.append(inString);

        String finalString = data.toString().replaceAll("\\s","");

        int ind = 0;
        for (int i = 0; i < out.length; i++) {
            for (int j = 0; j < out[i].length; j++) {
                char c = finalString.charAt(ind);
                int intC = Integer.parseInt("" + c);

                out[i][j] = intC;
                ind++;
            }
        }

        return out;
    }
}
