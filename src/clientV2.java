import java.net.*;
import java.io.*;
public class clientV2
{
            public static void main(String args[])throws Exception
            {         
                        //port
                        int port=2001;
                        String fichier="grosFichier";

                        System.out.println("Etape 1 : port("+port+")");
                        //fichier

                        System.out.println("Etape 2 : fichier("+fichier+")");
                        File nomFichier = new File(fichier+".txt");

                        //Crée un fichier dont le nom est saisie s'il n'existe pas déjà
                        if (!nomFichier.exists())
                        {
                            nomFichier.createNewFile();
                            System.out.println("Le fichier ["+nomFichier+"] a ete cree");
                        }else{
                            System.out.println("Le fichier ["+nomFichier+"] existe deja");
                        }


                        System.out.println("Taille max du paquet ? (<65000) ");
                        int taillePaquet = Lire.i();;


                        System.out.println("Etape 2.1 : lecture fichier volumineux");
                        FileInputStream f=new FileInputStream(nomFichier);
                        DatagramSocket dsoc=new DatagramSocket(50000);


                        int i=1;
                        long tailleTotal=nomFichier.length();
                        int cpt = 0;
                        byte b[]=new byte[500000];
                        while(f.available()!=0)
                        {
                                    b[i]=(byte)f.read();
                                    i++;
                                    if( i == taillePaquet)
                                    {
                                        b[0] = (byte)cpt;
                                        dsoc.send(new DatagramPacket(b,i,InetAddress.getLocalHost(),port));
                                        System.out.println("Paquet envoye : N-"+cpt+", taille("+i+")");
                                        i=0;
                                        cpt++;
                                    }
                                    else if( tailleTotal == taillePaquet*cpt+i)
                                    {
                                        b[0]=(byte)cpt;
                                        dsoc.send(new DatagramPacket(b,i,InetAddress.getLocalHost(),port));
                                        System.out.println("Paquet envoye : N°"+cpt+",taille("+i+")");
                                        System.out.println("taille totale du fichier envoye ="+tailleTotal);
                                    }


                                    
                        }                     
                        f.close();




            }

}

