import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class CodeToAscii {
    
    static int [] key;
    
    public static void carregarKey(){
        String keyStr = "magic";
        key = new int[keyStr.length()];
        for (int i = 0; i < keyStr.length(); i++) {
            char character = keyStr.charAt(i); // This gives the character 'a'
            int ascii = (int) character;
            key[i] = ascii;
        }
    }

    public static void lerArquivo() throws FileNotFoundException{

        FileInputStream fis = new FileInputStream("asci.txt");
        Scanner scan = new Scanner(fis);
        while(scan.hasNext()){
            String linha = scan.nextLine().trim();
            if(!linha.isEmpty()){
                String [] data = linha.split(" +");
                for (int i = 0; i < data.length; i++) {
                    int code = Integer.valueOf(data[i]);
                    int newCode = code + key[i];
                    System.out.printf("%s", (char)newCode);
//                    if(code >= 33){
//                        System.out.printf(" %s ", (char)code);
//                    }else{
//                        System.out.print(" . ");
//                    }
                }
            }
//            System.out.println();
        }
    }
    
    public static void conectar() throws UnknownHostException, IOException{
        Socket socket = new Socket("104.219.54.49", 9447);
        Scanner scan = new Scanner(socket.getInputStream());
        String key = "";
        int v = 0;
        int i = 0;
        while(scan.hasNext()){
            if(i >= 5){
                i = 0;
            }
            String text = scan.next();
            int code = 0;
            int newCode = 0;
            try {
                code = Integer.valueOf(text);
                newCode = code + CodeToAscii.key[i];
                
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }
//            if((code >= 48 && code <= 57) || (code >= 65 && code <= 90) || (code >= 97 && code <= 122)){
//            if((code >= 33 && code <= 126)){
                System.out.printf("%s", (char)newCode);
                key += String.format("%s", (char)newCode);
//            }else{
////                System.out.print(" . ");
//            }
            if("ASCII".equals(text)){
                break;
            }
            v++;
            if(v % 25 == 0){
                System.out.println("");
            }
        }

        
      System.out.println("\n\n\ntrying key: " + key + "\n\n\n");
      
      OutputStream os = socket.getOutputStream();
      os.write(key.getBytes());
      os.flush();
      
      while(scan.hasNext()){
          String line = scan.nextLine();
          System.out.println(line);
      }
      
//      System.out.println("fim");
      System.out.println("");
      System.out.println("----------------");
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        carregarKey();
//        lerArquivo();
        conectar();
        conectar();
        conectar();
        conectar();
        conectar();
        conectar();
        conectar();
//
//        int v = 67;
//
//        System.out.printf("%s", (char)v);

    }

}
