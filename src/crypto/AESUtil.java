package crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.security.MessageDigest;

import java.io.File;

public class AESUtil {

private static SecretKeySpec getKey(
String password
)throws Exception{

MessageDigest sha=
MessageDigest.getInstance(
"SHA-256"
);

byte[] key=
sha.digest(
password.getBytes("UTF-8")
);

byte[] finalKey=
new byte[16];

System.arraycopy(
key,
0,
finalKey,
0,
16
);

return new SecretKeySpec(
finalKey,
"AES"
);

}



public static String encrypt(
String input,
String password
)throws Exception{

File file=
new File(input);

String output=

file.getParent()

+

File.separator

+

"encrypted_"

+

file.getName();



Cipher cipher=
Cipher.getInstance(
"AES"
);

cipher.init(

Cipher.ENCRYPT_MODE,

getKey(password)

);



byte[] data=

Files.readAllBytes(

Paths.get(input)

);

byte[] enc=

cipher.doFinal(
data
);

Files.write(

Paths.get(output),

enc

);

return output;

}



public static String decrypt(
String input,
String password
)throws Exception{

File file=
new File(input);

String output=

file.getParent()

+

File.separator

+

"decrypted_"

+

file.getName();



Cipher cipher=
Cipher.getInstance(
"AES"
);

cipher.init(

Cipher.DECRYPT_MODE,

getKey(password)

);



byte[] data=

Files.readAllBytes(

Paths.get(input)

);

byte[] dec=

cipher.doFinal(
data
);

Files.write(

Paths.get(output),

dec

);

return output;

}

}