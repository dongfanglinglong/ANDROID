package com.dongfang.encrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.dongfang.utils.DFException;

/**
 * Usage: 加密
 * 
 * <pre>
 * String crypto = AESEncrypt.encrypt(masterpassword, cleartext)
 * ...
 * String cleartext = AESEncrypt.decrypt(masterpassword, crypto)
 * </pre>
 * 
 * @author dongfang
 */
public class AESEncrypt {

	public static String encrypt(String seed, String cleartext) {
		try {
			byte[] rawKey = getRawKey(seed.getBytes());
			byte[] result = encrypt(rawKey, cleartext.getBytes());
			return toHex(result);
		} catch (DFException e) {
			return cleartext;
		}
	}

	private static byte[] getRawKey(byte[] seed) throws DFException {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// //Android4.22需要用这个才可以，而且String的hashCode不能作为seed，原因未知
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
			sr.setSeed(seed);
			kgen.init(128, sr); // 192 and 256 bits may not be available
			SecretKey skey = kgen.generateKey();
			byte[] raw = skey.getEncoded();
			return raw;
		} catch (NoSuchAlgorithmException e) {
			throw new DFException(e.getMessage(), DFException.GETRAWKEY_NO_SUCH_ALGORITHM_EXCEPTION);
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			throw new DFException(e.getMessage(), DFException.ENCRYPT_No_Such_Provider_Exception);
		}
	}

	private static byte[] encrypt(byte[] raw, byte[] clear) throws DFException {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(clear);
			return encrypted;
		} catch (NoSuchPaddingException e) {
			throw new DFException(e.getMessage(), DFException.ENCRYPT_NO_SUCH_PADDING_EXCEPTION);
		} catch (NoSuchAlgorithmException e) {
			throw new DFException(e.getMessage(), DFException.ENCRYPT_NO_SUCH_ALGORITHM_EXCEPTION);
		} catch (InvalidKeyException e) {
			throw new DFException(e.getMessage(), DFException.ENCRYPT_INVALID_KEY_EXCEPTION);
		} catch (IllegalBlockSizeException e) {
			throw new DFException(e.getMessage(), DFException.ENCRYPT_ILLEGAL_BLOCKSIZE_EXCEPTION);
		} catch (BadPaddingException e) {
			throw new DFException(e.getMessage(), DFException.ENCRYPT_BAD_PADDING_EXCEPTION);
		}
	}

	public static String decrypt(String seed, String encrypted) {
		try {
			byte[] rawKey = getRawKey(seed.getBytes());
			byte[] enc = toByte(encrypted);
			byte[] result = decrypt(rawKey, enc);
			return new String(result);
		} catch (DFException e) {
			return encrypted;
		}
	}

	private static byte[] decrypt(byte[] raw, byte[] encrypted) throws DFException {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher;
		byte[] decrypted = null;
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			decrypted = cipher.doFinal(encrypted);
			return decrypted;
		} catch (NoSuchAlgorithmException e) {
			throw new DFException(e.getMessage(), DFException.DECRYPT_NO_SUCH_ALGORITHM_EXCEPTION);
		} catch (NoSuchPaddingException e) {
			throw new DFException(e.getMessage(), DFException.DECRYPT_NO_SUCH_PADDING_EXCEPTION);
		} catch (InvalidKeyException e) {
			throw new DFException(e.getMessage(), DFException.DECRYPT_INVALID_KEY_EXCEPTION);
		} catch (IllegalBlockSizeException e) {
			throw new DFException(e.getMessage(), DFException.DECRYPT_ILLEGAL_BLOCKSIZE_EXCEPTION);
		} catch (BadPaddingException e) {
			throw new DFException(e.getMessage(), DFException.DECRYPT_BAD_PADDING_EXCEPTION);
		}
	}

	// private static String toHex(String txt) {
	// return toHex(txt.getBytes());
	// }
	//
	// private static String fromHex(String hex) {
	// return new String(toByte(hex));
	// }

	private static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		return result;
	}

	private static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private static final String HEX = "0123456789ABCDEFGHIJKLNMOPQRSTUVWXYZ~!@";

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

}
