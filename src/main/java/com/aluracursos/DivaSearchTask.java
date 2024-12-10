package com.aluracursos;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class DivaSearchTask extends RecursiveTask<Divisa> {
    private static final int THRESHOLD = 10;
    private LinkedList<Divisa> divisas;
    private String nombreDivisa;
    private int start, end;

    @Override
    protected Divisa compute() {
        if(end - start <= THRESHOLD){
            for(int i = start; i < end; i++){
                if(divisas.get(i).getNombre().equalsIgnoreCase(nombreDivisa)){
                    return divisas.get(i);
                }
            }
            return null;
        } else {
            int middle = (start + end) / 2;
            DivaSearchTask leftTask = new DivaSearchTask(divisas, nombreDivisa, start, middle);
            DivaSearchTask rightTask = new DivaSearchTask(divisas, nombreDivisa, middle, end);

            leftTask.fork();
            Divisa rightResult = rightTask.compute();
            Divisa leftResult = leftTask.join();

            return rightResult != null ? rightResult : leftResult;
        }
    }
}
