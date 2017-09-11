/**
 * Created by john on 2017/9/9.
 */
import CryptoJS from 'crypto-js';

const AuthTokenKey = '1234567812345678'; // AES密钥
const AuthTokenIv = '1234567812345678'; //  AES向量

/*  AES加密 */
export function Encrypt(data) {
  const key = CryptoJS.enc.Latin1.parse(AuthTokenKey);
  const iv = CryptoJS.enc.Latin1.parse(AuthTokenIv);
  const ciphertext = CryptoJS.AES.encrypt(data, key,
    {
      iv,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.ZeroPadding,
    },
  );
  return ciphertext.toString();
}

/*  AES解密 */
export function Decrypt(data) {
  // const bytes = CryptoJS.AES.decrypt(data,AuthTokenKey,AuthTokenIv);
  // return bytes.toString(CryptoJS.enc.Utf8);
  const key = CryptoJS.enc.Latin1.parse(AuthTokenKey);
  const iv = CryptoJS.enc.Latin1.parse(AuthTokenIv);
  const decrypted = CryptoJS.AES.decrypt(data, key,
    {
      iv,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.ZeroPadding,
    });
  return decrypted.toString(CryptoJS.enc.Utf8);
}

