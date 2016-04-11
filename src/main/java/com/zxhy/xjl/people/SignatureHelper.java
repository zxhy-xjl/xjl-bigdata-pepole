package com.zxhy.xjl.people;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.binary.Base64;

/**
* @author  yangzaixiong
* 创建时间             2016年4月6日下午3:07:12
*/
public class SignatureHelper {

	public static final String APIKEY_HEADER = "apikey";
	public static final String TIMESTAMP_HEADER = "timestamp";
	public static final String SIGNATURE_HEADER = "signature";
	public static final List<String> SIGNATURE_KEYWORDS = Arrays.asList(APIKEY_HEADER, TIMESTAMP_HEADER);

	private static final String ALGORITHM = "DSA";

	/** 用私钥进行进行签名 */
	public static String createSignature(String message, String privateKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		byte[] privateKeyBytes = Base64.decodeBase64(privateKey);
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		Signature sig = Signature.getInstance(ALGORITHM);
		sig.initSign(keyFactory.generatePrivate(privateKeySpec));
		sig.update(message.getBytes());
		return Base64.encodeBase64URLSafeString(sig.sign());
	}

	/** 用公钥来校验客户端的签名 */
	public static boolean validateSignature(String message, String signatureString, String publicKey)
			throws InvalidKeyException, Exception {
		Signature signature = Signature.getInstance(ALGORITHM);
		signature.initVerify(decodePublicKey(publicKey));
		signature.update(message.getBytes());
		try {
			return signature.verify(Base64.decodeBase64(signatureString));
		} catch (Exception e) {
			return false;
		}
	}

	private static PublicKey decodePublicKey(String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		byte[] publicKeyBytes = Base64.decodeBase64(publicKey);
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		return keyFactory.generatePublic(publicKeySpec);
	}

	/** 生成密钥对 */
	public synchronized static String[] GenerateKeyPair() throws NoSuchAlgorithmException {
		// Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
		keyGen.initialize(1024);
		KeyPair keypair = keyGen.genKeyPair();
		PrivateKey privateKey = keypair.getPrivate();
		PublicKey publicKey = keypair.getPublic();

		// Get the bytes of the public and private keys (these go in the
		// database with API Key)
		byte[] privateKeyEncoded = privateKey.getEncoded();
		byte[] publicKeyEncoded = publicKey.getEncoded();

		return new String[] { Base64.encodeBase64URLSafeString(privateKeyEncoded),
				Base64.encodeBase64URLSafeString(publicKeyEncoded) };
	}

}
