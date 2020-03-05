import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class serverv3
{
            public static void main(String args[])throws IOException
            {
                        byte[] b=new byte[65000];
                        DatagramSocket dsoc=new DatagramSocket(2000);
                        FileOutputStream f=new FileOutputStream("essai.txt");
                        String texte;
                        while(true)
                        {
                                    DatagramPacket dp=new DatagramPacket(b,b.length);
                                    dsoc.receive(dp);
                                    String s = (new String(dp.getData(), StandardCharsets.UTF_8)).substring(0,dp.getLength());

                                    System.out.println("Paquet n°"+dp.getData()[0]+" a bien été reçu");

                                    InetAddress adresseIP = dp.getAddress(); //adresse client
                                    int portDistant = dp.getPort();  // port client

                                    f.write(s.getBytes());
                                    System.out.println("taille du paquet :" +dp.getLength());
                                    // On envoie un accuse de reception

                                    String ac = "le paquet n°"+dp.getData()[0]+" a bien été reçu";
                                    byte acServer[] = ac.getBytes();
                                    dp = new DatagramPacket(acServer,acServer.length, adresseIP, portDistant);
                                    System.out.println("Reception du port " + dp.getPort() + " de la machine " + dp.getAddress().getHostName() );
                                    dsoc.send(dp);

                        }
            }
}

