import java.net.*;
import java.io.*;

public class client
{
            public static void main(String[] args)throws Exception
            {         
                        System.out.println("Etape 1 : Veuillez saisir le port (>1024)");
                        int port=Lire.i();
                        System.out.println("Etape 2 : Veuillez saisir le nom du fichier de destination");
                        String fichier=Lire.S();
                        File nomFichier = new File(fichier);


                        //Crée un fichier dont le nom est saisie dans le terminal s'il n'existe pas déjà
                        if (!nomFichier.exists())
                        {
                            nomFichier.createNewFile();
                            System.out.println("Le fichier ["+nomFichier+"] a été créé");
                        }else{
                            System.out.println("Le fichier ["+nomFichier+"] existe déjà");
                        }

                        byte b[]=new byte[135000];
                        FileInputStream f=new FileInputStream(nomFichier);
                        DatagramSocket dsoc=new DatagramSocket(50000);

                        System.out.println("Etape 2.1 : Que voulez-vous écrire dans le fichier (sans retour ligne)?");
                        String ecriture=Lire.S();
                        PrintWriter writer = new PrintWriter(nomFichier);
                        writer.print(ecriture);
                        writer.close();

                        int i=1;
                        while(f.available()!=0)
                        {
                                    b[i]=(byte)f.read();
                                    i++;
                        }                     
                        f.close();
                        
                        System.out.println("Etape 3 : Veuillez saisir si l'adresse est local ou non : 0 local, 1 autre ");
                        int bool=Lire.i();

                        if(bool==0){
                            dsoc.send(new DatagramPacket(b,i,InetAddress.getLocalHost(),port));
                            //dsoc.send(new DatagramPacket(b,i,InetAddress.getByName("172.31.18.107"),port));
                            //System.out.println("Address : 172.31.18.107");
                            System.out.println("InetAddress = ["+InetAddress.getLocalHost()+"]");
                        }else{
                            System.out.println("Etape 4 : Veuillez saisir l'adresse de destination");
                            String dest=Lire.S();

                            dsoc.send(new DatagramPacket(b,i,InetAddress.getByName(dest),port));
                            System.out.println("Adresse de destination = ["+InetAddress.getByName(dest)+"]");
                        }
                        
                        
            }

}

