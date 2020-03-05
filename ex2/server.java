import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class server
{
            public static void main(String args[])throws IOException
            {
                        byte[] b=new byte[65000];
                        int longueurAccuse = b.length;
                        DatagramSocket dsoc=new DatagramSocket(2000);
                        FileOutputStream f=new FileOutputStream("essai.txt");
                        String texte;
                        while(true)
                        {
                                    DatagramPacket dp=new DatagramPacket(b,b.length);//L'ERREUR EST LA
                                    dsoc.receive(dp);
                                    String s = (new String(dp.getData(), StandardCharsets.UTF_8)).substring(0,dp.getLength());
                                    InetAddress adresseIP = dp.getAddress();
                                    int portDistant = dp.getPort();
                                    //System.out.println(s);
                                    f.write(s.getBytes());
                                    System.out.println(dp.getLength());
                                    // On envoie un accuse de reception
                                    texte = new String(b);
                                    texte = texte.substring(0,dp.getLength());
                                    dp = new DatagramPacket(b,longueurAccuse, adresseIP, portDistant);
                                    System.out.println("Reception du port " + dp.getPort() + " de la machine " + dp.getAddress().getHostName() );
                                    dsoc.send(dp);

                        }
            }
}

