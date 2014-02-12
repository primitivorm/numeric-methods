/*
 * Author: ISC Primitivo Roman Montero
 * Instituto Tecnologico Superior de Tepeaca
 * http://trixmontero.blogspot.com
 *
 **/

import java.io.*;
import javax.swing.*;

public class cramer {
    public static void main(String args[]){
        int tam=0;
        tam=Integer.parseInt(JOptionPane.showInputDialog(null,"Cual es el tamaño de la matriz"));

        int a[][]=new int[tam][tam]; //ecuacion
        int b[]=new int[tam]; //resultado de a
        int solucion[]=new int [tam];
        float cmr[]=new float[tam]; //temp
        int t=0;

        JOptionPane.showMessageDialog(null,"Inserte los valores de la matriz");
        insertarM(a); //llamada a procedimiento
        JOptionPane.showMessageDialog(null,"Inserte los valores del resultado");
        insertarV(b);
        cmr=cramer(a,b);
        JOptionPane.showMessageDialog(null,"Los valores para las X's son:");
        mostrarX(cmr);
    }

    //Declaracion de procedimiento para matriz
    public static void insertarM(int a[][]){
        for(int i=0; i<a.length; i++){
            for (int j=0; j<a[i].length;j++){
                a[i][j]=Integer.parseInt(JOptionPane.showInputDialog(null,"Inserte un valor entero de la posicion: [ "+i+" , "+j+" ]"));
            }
        }
    }

    public static void mostrarM(int a[][]){
        for(int i=0; i<a.length; i++){
            for (int j=0; j<a[i].length;j++){
                JOptionPane.showMessageDialog(null,"El resultado de la matriz en la posicion  [ "+i+" , "+j+ " ] es: "+a[i][j]);
            }
        }
    }
    //Declaracion de procedimientos para vector
    public static void insertarV(int a[]){
        for(int i=0; i<a.length; i++){
                a[i]=Integer.parseInt(JOptionPane.showInputDialog(null,"Inserte un valor entero del vector en la posicion: [ "+i+" ]" ));
        }
    }

    public static void mostrarV(int a[]){
        for(int i=0; i<a.length; i++){
            JOptionPane.showMessageDialog(null,"El resultado de la matriz en la posicion  [ "+i+" ] es: "+a[i]);
        }

    }

    public static int determinante(int a[][]){
        int c[][]=new int[a.length+(a.length-1)][a.length];
        int det = 0;
        //almacena los resultados parciales
        int par[]=new int[(a.length)*2];

        for(int i=0;i<a.length;i++){
            for(int j=0; j<a[i].length; j++){
                    c[i][j]=a[i][j];
            }
        }
        int k=0;
        for(int i=a.length;i<c.length;i++){
            for(int j=0; j<a.length; j++){
                    c[i][j]=a[k][j];
            }
            k++;
        }

        //calcula la suma de los productos y la inserta en par
        k=0;
        int temp=1;
        int inc=1;

        for (int i=0; i< a.length;i++){
            for(int j=0; j<a[i].length;j++){
                temp=temp*c[k][j];
                k++;
            }
            k=inc;
            par[i]=temp;
            temp=1;
            inc++;
        }

        //calcula la resta de los productos y la inserta en par
        k=a.length-1;
        temp=1;
        inc=a.length-1;
        int l=(par.length)/2;

        for (int i=0; i< a.length;i++){
            for(int j=0; j<a[i].length;j++){
                temp=temp*c[k][j];
                k--; //k=k-1;
            }
            par[l]=-temp;
            temp=1;
            inc++;
            k=inc;
            l++;
        }
        det=suma(par);
        return det;
    }

    //sustituye los valores de b en a en la posicion pos
    public static int [][] sustituye(int a[][], int b[], int pos){
        int c[][] =new int[a.length][a.length];

        for(int i=0;i<a.length;i++){
            for(int j=0; j<a[i].length; j++){
                if(j==pos){
                    c[i][j]=b[i];
                }
                else{
                    c[i][j]=a[i][j];
                }
            }
        }
        return c;
    }

    public static int suma(int a[]){
        int result=0;
        for(int i=0; i<a.length; i++){
            result=result+a[i];
        }

        return result;
    }

    ///funcion cramer
    public static float[] cramer(int a[][], int b[]){
        float Rcramer[]=new float[b.length];
        int det=determinante(a);
        if(det==0){
            JOptionPane.showMessageDialog(null,"No tiene solucion");
            return Rcramer;
        }

        int detTemp;
        int c[][]=new int[a.length][a.length];

        for(int i=0; i<a.length; i++){
            c=sustituye(a,b,i);
            detTemp=determinante(c);
            Rcramer[i]=(float)detTemp/(float)det;
        }
        return Rcramer;
    }

    //muestra los resultados de X
    public static void mostrarX(float a[]){
        for(int i=0; i<a.length; i++){
            JOptionPane.showMessageDialog(null,"El resultado de X_"+i+" es: "+a[i]);
        }
    }
}
