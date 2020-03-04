import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class server
{
            public static void main(String args[])throws IOException
            {
                        byte b[]=new byte[66000];
                        DatagramSocket dsoc=new DatagramSocket(2000);
                        FileOutputStream f=new FileOutputStream("essai.txt");
                        while(true)
                        {
                                    DatagramPacket dp=new DatagramPacket(b,b.length);
                                    dsoc.receive(dp);
                                    String s = (new String(dp.getData(), StandardCharsets.UTF_8)).substring(0,dp.getLength());
                                    System.out.println(s); 
                                    f.write(s.getBytes());
                                    System.out.println(dp.getLength());                              

                        }
            }
}

