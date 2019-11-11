package tpsi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tpsi {

   
     public static int   pgcd(int a, int b)
{
  int r;
  while (b != 0)
    {
      r = a%b;
      a = b;
      b = r;
    }
  return a;
}
     public static String crypt(String ch,int a,int b) {
       String str = "";
       if(pgcd(26,a)==1){
       for (int i= 0; i < ch.length(); i++) {
           char get = ch.charAt(i);
         // if(Character.isUpperCase(get)){
         if(get >= 'A' && get <='Z'){
              // get = (char) ((a * (int)(get-'A') + b) % 26 + 'A');
                get =  (char) (((a*(get-'A')+b)% 26) + 'A');
             
          }
            
         /* if(Character.isLowerCase(get)){
                 get=Character.toUpperCase(get);*/
          if(get >= 'a' && get <='z'){ 
                     // get = (char) ((a * (int)(get + 'a') + b) % 26 + 'a');
                      get =  (char) (((a*(get-'a')+b)% 26) + 'a');
                  // get=Character.toLowerCase(get);
               }
           
           str +=get;
       }
       }
       else{
           System.out.println("erreur du clé");
           };
       return str;
   }
////////////////////////////////////////////////////////////////////////
 /* static int inverse(int n, int a) {

        int inv = -1;
        int r0 = n,
                u0 = 1,
                v0 = 0,
                r1 = a,
                u1 = 0,
                v1 = 1,
                r2 = 0,
                u2, v2;

        if (pgcd(a, n) == 1) {

            while (r2 != 1) {
                r2 = r0 - (r0 / r1) * r1;
                u2 = u0 - (r0 / r1) * u1;
                v2 = v0 - (r0 / r1) * v1;
                r0 = r1;
                u0 = u1;
                v0 = v1;
                r1 = r2;
                u1 = u2;
                v1 = v2;
            }

            while (u0 < 0)
                u1 = u1 + 26;

            inv = u1;
        }
        return inv;
    }*/
     static int inverse(int a, int n) {

        int inv = -1;
        int r0 = n,
                u0 = 1,
                v0 = 0,
                r1 = a,
                u1 = 0,
                v1 = 1,
                r2 = 0,
                u2, v2;

        if (pgcd(a, n) == 1) {

            while (r2 != 1) {
                r2 = r0 - (r0 / r1) * r1;
                u2 = u0 - (r0 / r1) * u1;
                v2 = v0 - (r0 / r1) * v1;
                r0 = r1;
                u0 = u1;
                v0 = v1;
                r1 = r2;
                u1 = u2;
                v1 = v2;
            }

            while (u1 < 0)
                u1 = u1 + 26;

            inv = u1;
        }
        return inv;
    }
  
/////////////////////////////////////////////////////////////////////////////////
  
     public static String decrypt(String msg,int a,int b){
        String Msgcrypt="";

            int k =inverse(26,a);
            b = -b * k;
            while(b<0) b=b+26;
            for (int i = 0; i < msg.length(); i++) {
                if(msg.charAt(i) >= 'A' && msg.charAt(i) <='Z'){
                    Msgcrypt+=  (char) (((k*(msg.charAt(i)-'A')+b)%26) + 'A');
                }
                else if(msg.charAt(i) >= 'a' && msg.charAt(i) <='z'){

                    Msgcrypt+=  (char) (((k*(msg.charAt(i)-'a')+b)% 26) + 'a');

                }

            }

        return Msgcrypt;
    }
    ///////////////////////////////////////////////////////////////
    public static String[] brutforce(String msg){
        String [] result=new String[312];
        int j=0;
    for(int a=0;a<=25;a++){
        if(pgcd(a,26)==1){
          
            for(int b=0;b<=25;b++){
               
                  result[j]=decrypt(msg,a,b);
                  j++;
               
                }
          
            }
           
    }
    
   return result;
    }
   
    public static int[] frequency(String msg){
        int[] freq = new int[26];
        char[] msgChar = msg.toUpperCase().toCharArray();
        for(char c:msgChar){
            freq[c - 'A']++;
        }
        
        return freq;
    }
  
 static int recur(int[][] mat, int i, int[] temp, int k, String matTri, int in) {

        if (mat[i][matTri.codePointAt(k) - 97] > temp[matTri.codePointAt(k) - 97]) {

            in = i;

        } else if (mat[i][matTri.codePointAt(k) - 97] == temp[matTri.codePointAt(k) - 97]) {

            in = recur(mat, i, temp, ++k, matTri, in);
        }

        return in;
    }

static void tri (String[] results){
        String dict="esanirulodcpmqvgfbhxyjzkw";
        int[][] tab = new int[results.length][26];
        int[] tmp;
        String[] result = new String[312];
        int k = 0;
        int m =  0;
        int indice = 0;
        for(int i=0;i<results.length;i++)
            tab[i] = frequency(results[i]);
            tmp = tab[0];
        
        for(int i=0;i<tab.length;i++){
        
           indice =  recur(tab,i,tmp,k,dict,indice);
           tmp = tab[indice];
        }
        result[m] = results[indice];
        
        
         
        
        
        }
  public static int[] indicemax(int[]tab){
       int max;
       int[] tmp=new int[tab.length];
       max = tab[0];
       int k = 0;
       
    for(int i=0;i<tab.length;i++){
        if(max<tab[i]){
            max=tab[i];
        } 
    }
    for(int i=0;i<tab.length;i++)
        if(max==tab[i]){
            tmp[k] = i;
            k++;
        }
    
    int[] indice = new int[k];
    
    for(int i=0;i<k;i++)
        indice[i] = tmp[i];
    return indice;
    }


        
        
        //System.out.println("Taille liste"+resultsList.size());
   static int[] supmax(int[] tab,int j){
       for(int i=0;i<tab.length;i++){
            if (tab[i]==j){
                tab[i]=0;
            }
      }
       return tab;
       
   }
    
  public static int[][] findkey(String msg){
        int  [] tab;
        int [] franc={7,1,3,3,12,1,1,1,6,0,0,4,2,6,5,2,0,6,6,5,4,1,0,0,0,0};
        tab=frequency(msg);
        int[] y1=indicemax(tab);
        tab = supmax(tab,tab[y1[0]]);
        int[] y2=indicemax(tab);
        int[][] key = new int[1000][2];
        int[] x1=indicemax(franc);
        franc = supmax(franc,franc[x1[0]]);
        int[] x2=indicemax(franc);
        int y3;
        int x3;
        int k =0;
        int q = 0;
        x3 = x1[0] - x2[0];
        while(x3<0) x3+=26;
        for(int l=0;l<2;l++){
            
            for(int i=0;i<y1.length;i++){
                for(int j=0;j<y2.length;j++){
                    y3 = y1[i] - y2[j];
                    while(y3<0) y3+=26;

                    if(pgcd(26,x3) ==1){
                        int inv= inversmodulaire(26,x3);
                        key[k][0] = (y3 *inv) %26;
                        key[k][1] = y1[i] - key[k][0]*x1[0];
                        while(key[k][1]<0) key[k][1]+=26;
                        k++;
                    }else if( y3 % (pgcd(26,x3))==0){
                        int a0=(inversmodulaire(26,x3)-26) * (y3/pgcd(26,x3));
                        int a=0;
                        while(a<26){
                            a = 26/pgcd(26,x3)*q+a0;
                            q++;
                            if((pgcd(26,a) == 1) && a>0 && a<26){
                                key[k][0] = a;
                                key[k][1] = y1[i] - a*x1[0];
                                while(key[k][1]<0) key[k][1]+=26;
                                k++;
                            }
                        }
                    }
                    q=0;
                }
            }
            tab = supmax(tab,tab[y2[0]]);
            y2=indicemax(tab);
        }
        int[][]res=new int[k][2];
        for(int i=0;i<k;i++){
            res[i][0]=key[i][0];
            res[i][1]=key[i][1];}
        return res;
    }
static int inversmodulaire(int n,int a){
   
        int r0 = n,
                u0 = 0,
                v0 = 1,
                r1 = a,
                u1 = 1,
                v1 = 0,
                r2 = -1,
                u2, v2;

       

            while (r2 != 0) {
                r2 = r0 - (r0 / r1) * r1;
                u2 = u0 - (r0 / r1) * u1;
                v2 = v0 - (r0 / r1) * v1;
                r0 = r1;
                u0 = u1;
                v0 = v1;
                r1 = r2;
                u1 = u2;
                v1 = v2;
            
        }
            while (u0 < 0)
                u0 = u0 + 26;
            
            return u0;
  }
  

public static void main(String[ ] args) {
    
    Scanner sc = new Scanner(System.in);
        
        System.out.println(" ============================");
        System.out.println("  TP SECURITE D'INFORMATION");
        System.out.println(" ============================\n\n");
        
        System.out.println(" ============================");
        System.out.println("      MAKE YOUR CHOICE");
        System.out.println(" ============================");
        
        System.out.println("|----------------------------|");
        System.out.println("| c: Cryptage d'affine       |");
        System.out.println("| i: Inverse mofulaire       |");
        System.out.println("| d: Deryptage d'affine      |");
        System.out.println("| b: Brute force sans tri    |");
        System.out.println("| b: Brute force avec tri    |");
        System.out.println("| f: Fréquence des lettres   |");
        System.out.println("| k: Calcule des clés        |");
        System.out.println("| q: Quitter                 |");
        System.out.println("|----------------------------|");
        
        String str = sc.nextLine();
        char choice = str.charAt(0);
        
        switch (choice){
        
            case 'c' : 
                System.out.println("\n******* CRYPTAGE AFFINE *********");
                System.out.println("insérer un message clair");
                String msg = sc.nextLine();
                System.out.print("a= ");
                int a = sc.nextInt();
                System.out.print("b= ");
                int b = sc.nextInt();
                System.out.println("MESSAGE CRYPTE: "+crypt(msg, a, b));
                break;
                
            case 'i':
                System.out.println("\n******* INVERSE MODULAIRE*********");
                System.out.println("insérer un numero");
                int nbr = sc.nextInt();
                System.out.println("inverse Modulaire de "+nbr+" = "+inverse(26, nbr));
                break;
            case 'd' :
                System.out.println("\n******* DECRYPTAGE AFFINE *********");
                System.out.println("insérer un message crypté");
                String msgCh = sc.nextLine();
                System.out.print("a= ");
                int ac = sc.nextInt();
                System.out.print("b= ");
                int bc = sc.nextInt();
                System.out.println("MESSAGE CLAIRE: "+decrypt(msgCh, ac, bc));
                break;
                
                
            case 'b':
                System.out.println("\n******* BRUTE FORCE SANS TRI*********");
                System.out.println("insérer un message");
                String msgbr = sc.nextLine();
                String[] bfResults = brutforce(msgbr);
                bfResults = null;
                
                for(String s:bfResults){
                    System.out.println(s);
                }
                break;
                
            case 't':
                System.out.println("\n******* BRUTE FORCE AVEC TRI*********");
                System.out.println("insérer un message");
                String msgbrt = sc.nextLine();
                String[] bfResultsT = brutforce(msgbrt);    
                tri(bfResultsT);
                break;
                
            case 'f':
                System.out.println("\n******* FREQUENCE DES LETTRES *********");
                System.out.println("insérer un message");
                String msgf = sc.nextLine();
                int[] freq = frequency(msgf);
                for(int i=0; i<freq.length;i++){
                    System.out.println("["+i+"]"+" => "+freq[i]);
                }
                break;
                
            case 'k':
                System.out.println("\n******* CALCULE DES CLES *********");
                System.out.println("insérer un message");
                String msgfk = sc.nextLine();
                int[][] fk = findkey(msgfk);
                
                for(int i=0;i<fk.length;i++){
                    System.out.println("a: "+fk[i][0]+", b: "+fk[0][1]+"... msg "+decrypt(msgfk, fk[i][0], fk[i][1]));
                }
                break;
                
            default:
                System.out.println("Quitter ...");
        }
}
   
}



  


 
   

