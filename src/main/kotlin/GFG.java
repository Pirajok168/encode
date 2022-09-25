
class GFG
{

     String generateKey(String str, String key)
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

     String cipherText(String str, String key)
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

     String originalText(String cipher_text, String key)
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

     String LowerToUpper(String s)
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



   /* public static void main(String[] args)
    {
        String Str = "Hello" ;
        String Keyword = "noth" ;
        String str = LowerToUpper(Str);
        String keyword = LowerToUpper(Keyword);
        String key = generateKey(str, keyword);
        String cipher_text = cipherText(str, key);
        System.out.println( "Ciphertext : "
                + cipher_text + " " );
        System.out.println( "Original/Decrypted Text : "
                + originalText(cipher_text, key));
    }*/
}
