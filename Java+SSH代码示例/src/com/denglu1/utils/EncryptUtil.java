package com.denglu1.utils;

import java.util.Base64;

public class EncryptUtil {

	private static final String PRIVATEKEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC6UpD4AkVN/MS3" + 
			"raYs7UGtLuLfHJ58/YZi6DQVFDjCVZyPokwz+V5yQZjjYa6mOULg/3Q3h0B3ETL7" + 
			"Chpll/F2QrMrztVB1FUywyo5ny9eDQk1urn7rw/Z1u8MxfEmm+QpEcQ3ZPaP42tW" + 
			"xAtV8wQbtO7Ss2xwOpUEXcfc/cX+i4REcBW8hIC7GLLworqw/D7PoDkY7Cpym8fm" + 
			"nRXLpQ8tEAaN1KN79GQVtmRcxPcRpBJq0TolSvKrgegRRZNY5/xbvZfi1TQeogsU" + 
			"LGiQe4NVxMW30DSA6NLJH0e0WcMxfFwU2DzFTUKLVr5qL6Xbw1EHhhRoL7xkQCMS" + 
			"63PEp0ELAgMBAAECggEAUebJYrtcy5M4xaTHTyhbnkzl3T0vTbIDyKZZfoqNCJ+p" + 
			"Wln1NUOAiKjdE9Z0dwzkoRKpe18wryYYStF+GPpZYaqgJ7mbdHxplw7DGOEoaSdB" + 
			"zSRvbHrfdNpN6kd4YYhVGth8b/0phMZrAJDcisNwIt29vVCxcgPOTUUL/dBvoBvi" + 
			"mC3SFpfSFQ587HYNTNugIB1no7uKSfmsxU8xatKY3oM04Cwa91xlACLf9lLo2LTn" + 
			"fuQdmkDV/XEYxaogeiTF0MEkaDgwUpLrpcCcLf+TvWFBGYfGQb3Z5E0EVu2I0DQ/" + 
			"v5ohAiojjQ2j+agw2/0ZAcOSc7xTGUgmeouoooJYwQKBgQD1EJ1TOEGD/ZqPQTzO" + 
			"o+BTdsMmXbAHkXp7E4cISv4Q8ohgdRFD+BoKcvlIMbzv+OS/ggVztpSzHw8gDQuM" + 
			"KwJ7AIM3RNh1TQxxMHQQgWCzFU6QYM6so/AGx0E5RDk4S+Yfp1+duxi+tPN5MEzN" + 
			"xYhWNlESLZZQvsQMFmw2kG90vwKBgQDCou916XqTIE3+MZxu+cOUXoBxfG7Xi24E" + 
			"0WpzEv5nFBq07L+4NM4VazersN7xvLQ7WCoAmYXVNhSZ46Qc5THJZNWqikEPMs78" + 
			"170pON+CAdtih3uGf7wNdpvvjJ48AWjhU0r5pKigovpmyZiIADXRGMFgbl1b0HdU" + 
			"LAnElAXKtQKBgCgejTL3jbo3BVuYeYT2zR283pvkUJIcnemSO9P+uvM5UVxBLg9r" + 
			"iY/WXLsV8XFz1LcJaS0VYeilGAvONuq+i8xpPBILQkiJ0ZSiMw4UsCkBPvhh8NPQ" + 
			"jtX9Ps9KPPfbbRQXiY6/94AZDZb0ck5pnTVH9SO9tNMJN/0mmpagNK7vAoGBAJjn" + 
			"YZFTG11LqpnAJbG0+yWq2+LLq1QfkA2u6p5+VGdsDtMFjlPK//8pnLF/oJVt9nUM" + 
			"fOq9sLrf4m1GvV2J3IPneEeilxu2gY92eH9S05uRk+SipLLz1CsLe9NDt+dSAC/L" + 
			"vIHEDkkr3+UyCGcHK0AZ34i/Cw6fBa+VbRMGakplAoGASI7GJZNm98XNe4KHmGel" + 
			"DCHGgtswE0UV3eVgcdoNC8DrPVbkzUMkQUjwJbVc9WkgnDvr/tqqlalFjozqIbyQ" + 
			"R4cmZ8ToKar8Al9VcvEmb/47U/ra8gGH+JBVR6hCZqS47juyfxEKKoe/aIE8EkCS" + 
			"GlShr8rVpa9q4pCoicb/AA0=";

	/**
	 * @param string sText             AES加密的密文
	 * @param string sRasEncryptedKey  经过RAS加密后的密文
	 * @return string res              明文
	 */
	public static String rsa_aes_decrypt(String sText, String sRasEncryptedKey) {

		String key = null, res = null;
		try {
			key = RSACipher.decrypt(sRasEncryptedKey, PRIVATEKEY);
			Base64.Decoder decoder = Base64.getDecoder();
			res = AESCipher.decrypt(decoder.decode(key), sText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
