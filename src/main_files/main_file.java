package main_files;
import java.awt.image.BufferedImage;
import java.security.KeyPair;

import java.security.KeyPair;
import java.util.Scanner;



public class main_file {
    public static void main(String[] args) throws Exception {
        //getting the message
        System.out.println("enter the message");
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
        System.out.print("Enter a string: ");
        String str= sc.nextLine();

        //getting md5 encryption
        MD5 md5 = new MD5();
        String afterMd5=md5.getMd5(str);
        //getting md5 digital signature
        Digital_Signature ds= new Digital_Signature();

        KeyPair keyPair
                = ds.Generate_RSA_KeyPair();
        byte[] signature
                = ds.Create_Digital_Signature(
                afterMd5.getBytes(),
                keyPair.getPrivate());
        //rsa encryption
        RSAEncryption rsaEncryption=new RSAEncryption();

        String afterRsa=rsaEncryption.getRSA(str);

        String input=signature+afterRsa;


        //lsb encode
        LSB_encode lsb_encode= new LSB_encode();
        final String COVERIMAGEFILE = "C:\\Users\\pranesh\\OneDrive - National Institute of Technology\\Desktop\\coverimage.jpg";
        String contentOfMessageFile = input;
        int[] bits=lsb_encode.bit_Msg(contentOfMessageFile);
        System.out.println("msg in file "+contentOfMessageFile);
        for(int i=0;i<bits.length;i++)
            System.out.print(bits[i]);
        System.out.println();
        BufferedImage theImage=lsb_encode.readImageFile(COVERIMAGEFILE);
        lsb_encode.hideTheMessage(bits, theImage);
    }
}
