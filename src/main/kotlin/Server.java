import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    static String generateKey(String str, String key)
    {
        int x = str.length();
        for ( int i = 0 ; ; i++)
        {
            if (x == i)
                i = 0 ;
            if (key.length() == str.length())
                break ;
            key+=(key.charAt(i));
        }
        return key;
    }

    static String cipherText(String str, String key)
    {
        String cipher_text= "" ;
        for ( int i = 0 ; i < str.length(); i++)
        {

            int x = (str.charAt(i) + key.charAt(i)) % 26 ;

            x += 'A' ;
            cipher_text+=( char )(x);
        }
        return cipher_text;
    }

    static String originalText(String cipher_text, String key)
    {
        String orig_text= "" ;
        for ( int i = 0 ; i < cipher_text.length() &&
                i < key.length(); i++)
        {

            int x = (cipher_text.charAt(i) -
                    key.charAt(i) + 26 ) % 26 ;

            x += 'A' ;
            orig_text+=( char )(x);
        }
        return orig_text;
    }

    static String LowerToUpper(String s)
    {
        StringBuffer str = new StringBuffer(s);
        for ( int i = 0 ; i < s.length(); i++)
        {
            if (Character.isLowerCase(s.charAt(i)))
            {
                str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
            }
        }
        s = str.toString();
        return s;
    }

    public static void main(String[] args) {
        try {
            try  {
                server = new ServerSocket(4004);
                System.out.println("Сервер запущен!");


                clientSocket = server.accept();
                System.out.println("Клиент подключен");
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String word = in.readLine();
                    System.out.println("Word - " + word);
                    String str = LowerToUpper(word);



                    out.write("Введите ключ: " + "\n");
                    out.flush();

                    String key_ = in.readLine();
                    System.out.println("Key - " + key_);
                    String keyword = LowerToUpper(key_);
                    String key = generateKey(str, keyword);
                    String cipher_text = cipherText(str, key);

                    out.write("Зашифрованное слово: " + cipher_text + "\n");
                    out.flush();


                    out.write("Введите ключ для расшифровки " + "\n");
                    out.flush();


                    key_ = in.readLine();
                    keyword = LowerToUpper(key_);
                    key = generateKey(str, keyword);

                    String orig = originalText(cipher_text, key);

                    out.write("Расшифрованное сообщение " + orig + "\n");
                    out.flush();

                } finally {
                    clientSocket.close();

                    in.close();
                    out.close();
                }


            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}