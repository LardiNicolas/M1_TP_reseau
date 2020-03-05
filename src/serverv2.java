import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class serverv2
{
            public static void main(String args[])throws IOException
            {
                        byte[] b=new byte[65000];
                        int longueurAccuse = b.length;
                        DatagramSocket dsoc=new DatagramSocket(2001);
                        FileOutputStream f=new FileOutputStream("essai.txt");
                        String texte;
                        while(true)
                        {
                                    DatagramPacket dp=new DatagramPacket(b,b.length);//L'ERREUR EST LA
                                    dsoc.receive(dp);
                                    String s = (new String(dp.getData(), StandardCharsets.UTF_8)).substring(0,dp.getLength());
                                    System.out.println("Paquet n°"+dp.getData()[0]+" a bien été reçu");
                                    //System.out.println(s);
                                    f.write(s.getBytes());
                                    System.out.println("taille du paquet :" +dp.getLength());


                        }
            }
}

