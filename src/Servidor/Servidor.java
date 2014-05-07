/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ROBERTOVA
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        ServerSocket socket = new ServerSocket(6786);

        while (true) {

            Socket conexion = socket.accept();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            String comando = entrada.readLine();
            System.out.println(comando);
            String[] cachos = comando.split("::");
            
            if (cachos[0].equals("GET")) {
                String fileName = cachos[1];
                
                File file = new File(fileName);
                if (file.exists()) {
                    int numOfBytes = (int) file.length();
                    FileInputStream inFile = new FileInputStream(fileName);
                    byte[] fileInBytes = new byte[numOfBytes];
                    inFile.read(fileInBytes);
                    salida.write(fileInBytes, 0, numOfBytes);
                    salida.close();
                    System.out.println("Arquivo enviado");
                } else {
                    System.out.println("Non se autopou o arquivo");
                }
            }
        }
    }
    
}
